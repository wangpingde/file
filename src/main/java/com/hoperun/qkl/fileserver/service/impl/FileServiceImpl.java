package com.hoperun.qkl.fileserver.service.impl;

import com.hoperun.qkl.fileserver.service.IFileService;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Override
    public String saveFile(InputStream inputStream, String fileName, String content) {
        GridFSFile gridFSFile = gridFsTemplate.store(inputStream,fileName,content);
        if(gridFSFile!=null){
            return gridFSFile.getId().toString();
        }
        return null;
    }

    @Override
    public GridFSDBFile queryFileById(String fileId) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
    }

    @Override
    public GridFSDBFile queryFileByMd5(String md5) {
        GridFSDBFile md5File = gridFsTemplate.findOne(new Query(Criteria.where("md5").is(md5)));
        return md5File;
    }

    @Override
    public GridFSDBFile queryFileWithQuery(Query query) {
        return gridFsTemplate.findOne(query);
    }

    @Override
    public void deleteFile(String fileId) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(fileId)));
    }
}
