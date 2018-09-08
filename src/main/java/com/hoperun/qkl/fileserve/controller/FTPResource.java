package com.hoperun.qkl.fileserve.controller;

import com.hoperun.qkl.fileserve.service.IFTPService;
import com.hoperun.qkl.fileserve.service.impl.FTPServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.hoperun.qkl.fileserve.util.Md5util.getMd5;

@RestController
@RequestMapping("/api/ftp")
public class FTPResource {

    @Autowired
    private IFTPService ftpService;

    @PostMapping("/file")
    public void upload(String name, String md5, MultipartFile file) throws IOException {
        if(StringUtils.isEmpty(md5)){
            md5 = getMd5(file);
        }
        if(StringUtils.isEmpty(name)){
            name = file.getOriginalFilename();
        }
        ftpService.upload(name, md5,file);
    }



    @PostMapping("/bigFile")
    public void upload(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            ftpService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            ftpService.upload(name, md5,file);
        }
    }

}
