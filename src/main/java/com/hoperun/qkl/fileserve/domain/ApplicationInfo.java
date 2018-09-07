package com.hoperun.qkl.fileserve.domain;

import java.io.Serializable;

public class ApplicationInfo implements Serializable {

    private String address;

    private String businessLine;

    private String subBusinessLine;

    private String login;

    private String version;

    private String busCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public String getSubBusinessLine() {
        return subBusinessLine;
    }

    public void setSubBusinessLine(String subBusinessLine) {
        this.subBusinessLine = subBusinessLine;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
