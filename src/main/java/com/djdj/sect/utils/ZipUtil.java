package com.djdj.sect.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author lichanghao
 * @date 2024/6/3
 * @Desc 压缩包工具类
 */
@Slf4j
public class ZipUtil {

    private ZipUtil() {}

    /**
     * @param inputStreams 要压缩的文件流 map<FileName,InputStream>
     * @param outputStream 输出流
     */
    public static void zipFilesByStreams(Map<String, InputStream> inputStreams, OutputStream outputStream) {
        if (inputStreams == null || inputStreams.isEmpty()) {
            return;
        }
        Map<String, byte[]> fileMap = new HashMap<>();
        String originName = null;
        try {
            byte[] tempBuff = null;
            InputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            int len = -1;
            for (Map.Entry<String, InputStream> file : inputStreams.entrySet()) {
                originName = file.getKey();
                log.info("zipFilesByStreams,zipFile:[{}]", originName);
                fileInputStream = file.getValue();
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                byteArrayOutputStream = new ByteArrayOutputStream();
                tempBuff = new byte[1024];
                while ((len = bufferedInputStream.read(tempBuff)) != -1) {
                    byteArrayOutputStream.write(tempBuff, 0, len);
                }
                fileMap.put(originName, byteArrayOutputStream.toByteArray());
                log.info("zipFilesByStreams,originName[{}] zip success:", originName);
                bufferedInputStream.close();
                fileInputStream.close();
                byteArrayOutputStream.close();
            }
        } catch (Exception e) {
            log.error("zipFilesByStreams,originName[{}] zip fail:[{}]", originName, e.toString());
        }
        zipFiles(fileMap, outputStream);
        log.info("zipFilesByStreams,zipFile success...");
    }

    /**
     * @param inputStreams 要压缩的文件流字节数组  map<FileName,byte[]>
     * @param outputStream 输出流
     */
    public static void zipFiles(Map<String, byte[]> inputStreams, OutputStream outputStream) {
        if (inputStreams == null || inputStreams.isEmpty()) {
            return;
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        String originName = null;
        try {
            byte[] tempBuff = null;
            for (Map.Entry<String, byte[]> file : inputStreams.entrySet()) {
                originName = file.getKey();
                log.info("zipFiles:[{}]", originName);
                tempBuff = file.getValue();
                //分别设置压缩包中每一文件的文件名（必须含文件后缀）
                zipOutputStream.putNextEntry(new ZipEntry(originName));
                //将图片添加到压缩包中
                zipOutputStream.write(tempBuff, 0, tempBuff.length);
                log.info("originName[{}] zip success:", originName);
                zipOutputStream.closeEntry();
            }
        } catch (Exception e) {
            log.info("originName[{}] zip fail:[{}]", originName, e.toString());
        } finally {
            try {
                zipOutputStream.flush();
            } catch (IOException e) {
                log.error("zipOutputStream Io flush Fail：{}", e.toString());
            }
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                log.error("zipOutputStream Io Close Fail：{}", e.toString());
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("outputStream Io Close Fail one：{}", e.toString());
            }
        }
        log.info("zipFiles success...");
    }
}
