package com.hoperun.qkl.fileserve.domain;

import com.hoperun.qkl.fileserve.constant.ServiceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "FileInfo")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 639340453665586376L;

    @Id
    private String id;

    private String fileId;

    private String name;

    private String md5;

    private String etx;

    private Long uploadDate;

    private ServiceType serviceType;

    private ApplicationInfo ApplicationInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getEtx() {
        return etx;
    }

    public void setEtx(String etx) {
        this.etx = etx;
    }

    public Long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public com.hoperun.qkl.fileserve.domain.ApplicationInfo getApplicationInfo() {
        return ApplicationInfo;
    }

    public void setApplicationInfo(com.hoperun.qkl.fileserve.domain.ApplicationInfo applicationInfo) {
        ApplicationInfo = applicationInfo;
    }
}
