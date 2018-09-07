package com.hoperun.qkl.fileserve.repository;

import com.hoperun.qkl.fileserve.domain.FileInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileInfoRepository extends MongoRepository<FileInfo,Integer> {



}
