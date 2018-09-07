package com.hoperun.qkl.fileserve.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.core.query.Query;

import java.io.InputStream;

public interface IFileService {

    String saveFile(InputStream inputStream, String fileName, String content);

    GridFSDBFile queryFileById(String fileId);

    GridFSDBFile queryFileByMd5(String md5);

    GridFSDBFile queryFileWithQuery(Query query);

    void deleteFile(String fileId);

}
