package com.hoperun.qkl.fileserve.service.impl;

import com.hoperun.qkl.fileserve.domain.FileInfo;
import com.hoperun.qkl.fileserve.service.IFileService;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private MongoTemplate  mongoTemplate;


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

    @Override
    public Page<FileInfo> listFiles(Pageable pageable, Query query) {

        long count = mongoTemplate.count(query, FileInfo.class);
        query.with(new Sort(Sort.Direction.DESC, "uploadDate"));
        query.skip(pageable.getPageNumber()*pageable.getPageSize());
        query.limit(pageable.getPageSize());

        List<FileInfo> fileInfos = mongoTemplate.find(query, FileInfo.class);
        return new PageImpl<FileInfo>(fileInfos,pageable,count);

    }
}
