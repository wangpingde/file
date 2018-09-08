package com.hoperun.qkl.fileserve.service.impl;


import com.hoperun.qkl.fileserve.config.UploadConfig;
import com.hoperun.qkl.fileserve.constant.ServiceType;
import com.hoperun.qkl.fileserve.domain.FileInfo;
import com.hoperun.qkl.fileserve.repository.FileInfoRepository;
import com.hoperun.qkl.fileserve.service.IFTPService;
import com.hoperun.qkl.fileserve.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.hoperun.qkl.fileserve.util.FileUtils.generateFileName;
import static com.hoperun.qkl.fileserve.util.UploadUtils.*;

@Service
public class FTPServiceImpl implements IFTPService {

    @Autowired
    private FileInfoRepository infoRepository;
    /**
     * 上传文件
     * @param md5
     * @param file
     */
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        String id = generateFileName();
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(UUID.randomUUID().toString());
        fileInfo.setFileId(id);
        fileInfo.setMd5(md5);
        fileInfo.setName(name);
        fileInfo.setEtx(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1));
        fileInfo.setServiceType(ServiceType.FTP);
        fileInfo.setUploadDate(System.currentTimeMillis()/1000);
        infoRepository.save(fileInfo);
    }

    /**
     * 分块上传文件
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(fileName);
            fileInfo.setFileId(fileName);
            fileInfo.setMd5(md5);
            fileInfo.setName(name);
            fileInfo.setEtx(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1));
            fileInfo.setServiceType(ServiceType.FTP);
            fileInfo.setUploadDate(System.currentTimeMillis()/1000);
            infoRepository.save(fileInfo);
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        List<FileInfo> allByMd5 = infoRepository.findAllByMd5(md5);
        return !CollectionUtils.isEmpty(allByMd5);
    }
}
