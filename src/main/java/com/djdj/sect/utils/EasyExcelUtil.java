package com.djdj.sect.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lichanghao
 * @date 2024/5/8
 * @Desc
 */
@Slf4j
public class EasyExcelUtil {
    private EasyExcelUtil() {}

    /**
     * 按照模板，写入excel文件，数据在内存中操作，只适用于数据量不会太大的
     * @param outputStream 输出目标流
     * @param templatePath 模板文件流
     * @param fixedData 固定列数据
     * @param datas 列表数据
     * @desc 适用于少量数据
     */
    public static void writeWithTemplate(OutputStream outputStream, InputStream templatePath, Map<String,Object> fixedData, List<?> datas){
        ExcelWriter excelWriter = EasyExcelFactory
                .write(outputStream)
//                .withTemplate(ResourceUtils.getFile("classpath:templates/template.xlsx"))
                .withTemplate(templatePath)
                .build();
        WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();
        //FillConfig设置为true,即内存中处理，适用于少量数据
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(datas,fillConfig,writeSheet);
        excelWriter.fill(fixedData,writeSheet);
        excelWriter.finish();
        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("Failed to write");
        }
    }


    /**
     * 写入excel文件
     * @param outputStream
     * @param sheetName
     * @param clazz header
     * @param datas
     */
    public static void write(OutputStream outputStream, String sheetName, Class<?> clazz, List<?> datas){
        //重置默认表头样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = resetHeaderCellStyleStrategy();
        EasyExcelFactory.write(outputStream,clazz).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(datas);
    }


    /**
     * 写入excel文件，支持自定义样式
     * @param outputStream
     * @param writeHandler 自定义样式
     * @param sheetName
     * @param clazz
     * @param datas
     */
    public static void writeWithCellStyler(OutputStream outputStream, WriteHandler writeHandler, String sheetName, Class<?> clazz, List<?> datas){
        EasyExcelFactory
                .write(outputStream,clazz)
                .registerWriteHandler(resetHeaderCellStyleStrategy())
                .registerWriteHandler(writeHandler)
                .sheet(sheetName)
                .doWrite(datas);
    }


    /**
     * eventListener 必须每次调用时，以new的方式创建，不允许被spring管理。若其里面用到spring时，通过构造函数传进去
     * @param inputStream
     * @param clazz
     * @param eventListener : 可以直接继承 AnalysisEventListener 即可
     */
    public static void readWithSingleSheet(InputStream inputStream, Class<?> clazz, ReadListener<?> eventListener) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcelFactory.read(inputStream, clazz, eventListener).sheet().doRead();
    }

    /**
     * 读取全部sheet
     * @param inputStream
     * @param clazz
     * @param eventListener
     */
    public static void readWithAllSheet(InputStream inputStream, Class<?> clazz, ReadListener<?> eventListener) {
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcelFactory.read(inputStream, clazz, eventListener).doReadAll();
    }

    /**
     * 读取部分sheet
     * @param inputStream
     * @param sheetMaps [指定需要的sheet及每个sheet的header和Listener]
     */
    public static void readWithPartSheet(InputStream inputStream, Map<Integer,Map<Class<?>,ReadListener<?>>> sheetMaps) {
        //
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcelFactory.read(inputStream).build();
            // 允许每一个sheet 对应不同的Head和Listener
            List<ReadSheet> readSheets= new ArrayList<>(sheetMaps.size());
            for(Map.Entry<Integer,Map<Class<?>,ReadListener<?>>> sheetEntry : sheetMaps.entrySet()){
                Map.Entry<Class<?>,ReadListener<?>> headerEntry= sheetEntry.getValue().entrySet().iterator().next();
                readSheets.add(EasyExcelFactory.readSheet(sheetEntry.getKey()).head(headerEntry.getKey()).registerReadListener(headerEntry.getValue()).build());
            }
            // 这里注意 一定要把sheet1 sheet2,... 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheets);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 读取部分sheet
     * @param inputStream
     * @param sheetMaps [指定需要的sheet及每个sheet的header和Listener]
     */
    public static void readWithPartSheetWithName(InputStream inputStream, Map<String,Map<Class<?>,ReadListener<?>>> sheetMaps) {
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcelFactory.read(inputStream).build();
            // 允许每一个sheet 对应不同的Head和Listener
            List<ReadSheet> readSheets= new ArrayList<>(sheetMaps.size());
            for(Map.Entry<String,Map<Class<?>,ReadListener<?>>> sheetEntry : sheetMaps.entrySet()){
                Map.Entry<Class<?>,ReadListener<?>> headerEntry= sheetEntry.getValue().entrySet().iterator().next();
                readSheets.add(EasyExcelFactory.readSheet(sheetEntry.getKey()).head(headerEntry.getKey()).registerReadListener(headerEntry.getValue()).build());
            }
            // 这里注意 一定要把sheet1 sheet2,... 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheets);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @NotNull
    private static HorizontalCellStyleStrategy resetHeaderCellStyleStrategy() {
        //头的策略
        WriteCellStyle headWriteCellStyle = buildHeadWriteCellStyle();
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = buildContentWriteCellStyle();
        //内容风格可以定义多个，若有需要。。。
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    @NotNull
    private static WriteCellStyle buildContentWriteCellStyle() {
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        //内容字体大小
        contentWriteFont.setFontName("黑体");
        contentWriteFont.setFontHeightInPoints((short)11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //设置自动换行
        contentWriteCellStyle.setWrapped(true);
        //设置垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置边框样式
        setBorderStyle(contentWriteCellStyle);
        return contentWriteCellStyle;
    }

    @NotNull
    private static WriteCellStyle buildHeadWriteCellStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headWriteCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headWriteCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置head头边框
        setBorderStyle(headWriteCellStyle);
        return headWriteCellStyle;
    }

    private static void setBorderStyle(WriteCellStyle contentWriteCellStyle) {
        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //颜色
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
    }
}
