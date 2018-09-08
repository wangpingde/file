package com.hoperun.qkl.fileserve.controller;

import com.hoperun.qkl.fileserve.domain.FileInfo;
import com.hoperun.qkl.fileserve.service.IFileService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hoperun.qkl.fileserve.util.FileUtils.deSerialize;
import static com.hoperun.qkl.fileserve.util.FileUtils.mediaType;
import static com.hoperun.qkl.fileserve.util.Md5util.getMd5;

@RestController
@RequestMapping("/api/file")
public class FileResource {

    @Resource
    private IFileService iFileService;

    @GetMapping
    public ResponseEntity<?> listFiles(Pageable page,FileInfo fileInfo) {
        try {
            if(page == null){
                page = new PageRequest(0, 10);
            }
            Query query = new Query();
            Page<FileInfo> fileInfos = iFileService.listFiles(page,query);
            return new ResponseEntity(fileInfos,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            GridFSDBFile gridFSDBFile = iFileService.queryFileByMd5(getMd5(file));
            if(gridFSDBFile != null){
                map.put("fileId", gridFSDBFile.getId().toString());
                map.put("fileName", gridFSDBFile.getFilename());
                return new ResponseEntity(map,HttpStatus.OK);
            }
            String fileName = file.getOriginalFilename();
            String fileId = iFileService.saveFile(file.getInputStream(), fileName,mediaType(fileName).toString());

            map.put("fileId", fileId);
            map.put("fileName", fileName);
            return new ResponseEntity(map,HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> downloadFile(HttpServletRequest request, @PathVariable String fileId) {
        try {
            GridFSDBFile file = iFileService.queryFileById(fileId);
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.parseMediaType(file.getContentType());
            headers.setContentType(mediaType);
            return new ResponseEntity<byte[]>(deSerialize(file.getInputStream()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> deleteFile(HttpServletRequest request, @PathVariable String fileId) {
        try {
            iFileService.deleteFile(fileId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
