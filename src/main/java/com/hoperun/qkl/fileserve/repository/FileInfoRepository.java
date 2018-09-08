package com.hoperun.qkl.fileserve.repository;

import com.hoperun.qkl.fileserve.domain.FileInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileInfoRepository extends MongoRepository<FileInfo,Integer> {

     List<FileInfo> findAllByMd5(String md5);

}
