package com.djdj.sect.utils;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.word.WordExportUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author lichanghao
 * @date 2024/5/8
 * @Desc
 */
@Slf4j
public class DownloadOfficeFileUtil {

    public static final String EXCEL_SUFFIX = ".xlsx";
    public static final String WORD_SUFFIX = ".docx";
    public static final String ZIP_SUFFIX = ".zip";
    public static final String ATTACHMENT_FILENAME = ".attachment;filename=";
    public static final String CONTENT_TYPE = "application/octet-stream";
    public static final String HEADER_CONTENT_TYPE = "content-type";
    public static final String CONTENT_DISPOSITION = "Content-disposition";
    public static final String APPLICATION_MS_EXCEL = "application/ms-excel";
    public static final String APPLICATION_MS_WORD = "application/ms-word";



    private DownloadOfficeFileUtil() {
    }

    @SneakyThrows
    public static void setZipDownloadResponse(HttpServletResponse response, String fieldName) {
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (StringUtils.hasText(fieldName)) {
            response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + URLEncoder.encode(fieldName, StandardCharsets.UTF_8) + ZIP_SUFFIX);
        }
    }

    @SneakyThrows
    public static void setExcelDownloadResponse(HttpServletResponse response, String fieldName) {
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEADER_CONTENT_TYPE, APPLICATION_MS_EXCEL);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (StringUtils.hasText(fieldName)) {
            response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + URLEncoder.encode(fieldName, StandardCharsets.UTF_8) + DownloadOfficeFileUtil.EXCEL_SUFFIX);
        }
    }

    @SneakyThrows
    private static void setWordDownLoadResponse(String fileName, HttpServletResponse response, XWPFDocument doc){
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader(HEADER_CONTENT_TYPE, APPLICATION_MS_WORD);
            response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + URLEncoder.encode(fileName + DownloadOfficeFileUtil.WORD_SUFFIX, StandardCharsets.UTF_8));
            doc.write(response.getOutputStream());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    @SneakyThrows
    public static void wordTemplateExport(Map<String, Object> map, String templatePath, String fileName, HttpServletResponse response){
        TemplateExportParams params = new TemplateExportParams(templatePath);
        //开启横向遍历
        params.setColForEach(true);
        XWPFDocument doc = WordExportUtil.exportWord07(params.getTemplateUrl(), map);
        setWordDownLoadResponse(fileName,response,doc);
    }

    @SneakyThrows
    public static InputStream wordTemplateExport(Map<String, Object> map, String templatePath){
        TemplateExportParams params = new TemplateExportParams(templatePath);
        params.setColForEach(true);
        XWPFDocument doc = WordExportUtil.exportWord07(params.getTemplateUrl(), map);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc.write(outputStream);
        //转 InputStream 流
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
