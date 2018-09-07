package com.hoperun.qkl.fileserve.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFTPService {

    /**
     * 上传文件
     * @param name
     * @param md5
     * @param file
     * @throws IOException
     */
    void upload(String name, String md5, MultipartFile file) throws IOException;

    /**
     * 分块上传文件
     * @param name
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    void uploadWithBlock(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException;

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    boolean checkMd5(String md5);

}
