package com.hoperun.qkl.fileserve.util;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.UUID;

/**
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 写入文件
     * @param target
     * @param src
     * @throws IOException
     */
    public static void write(String target, InputStream src) throws IOException {
        OutputStream os = new FileOutputStream(target);
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = src.read(buf))) {
            os.write(buf,0,len);
        }
        os.flush();
        os.close();
    }

    /**
     * 分块写入文件
     * @param target
     * @param targetSize
     * @param src
     * @param srcSize
     * @param chunks
     * @param chunk
     * @throws IOException
     */
    public static void writeWithBlok(String target, Long targetSize, InputStream src, Long srcSize, Integer chunks, Integer chunk) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(target,"rw");
        randomAccessFile.setLength(targetSize);
        if (chunk == chunks - 1 && chunk != 0) {
            randomAccessFile.seek(chunk * (targetSize - srcSize) / chunk);
        } else {
            randomAccessFile.seek(chunk * srcSize);
        }
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = src.read(buf))) {
            randomAccessFile.write(buf,0,len);
        }
        randomAccessFile.close();
    }

    /**
     * 生成随机文件名
     * @return
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }
    /**
     * 文件类型确定传输http
     *
     * @param name
     * @return
     */
    public static MediaType mediaType(String name) {
        String sufix = name.substring(name.lastIndexOf(".") + 1);
        if (!StringUtils.isEmpty(sufix)) {
            if ("GIF".equals(sufix.toUpperCase())) {
                return MediaType.IMAGE_GIF;
            }
            if ("PNG".equals(sufix.toUpperCase())) {
                return MediaType.IMAGE_PNG;
            }
            if ("JPEG".equals(sufix.toUpperCase()) || "JPG".equals(sufix.toUpperCase()) || "IMG".equals(sufix.toUpperCase())) {
                return MediaType.IMAGE_JPEG;
            }
            if ("PDF".equals(sufix.toUpperCase())) {
                return MediaType.parseMediaType("application/json");
            }
        }
        return MediaType.APPLICATION_OCTET_STREAM;

    }


    /**
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] deSerialize(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(buf)) != -1)
                bos.write(buf, 0, len);
            return bos.toByteArray();
        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (bos != null) {
                bos.flush();
                bos.close();
            }
        }
        return null;
    }


}
