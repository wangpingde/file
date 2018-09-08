package com.hoperun.qkl.fileserve.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 *记录用户登陆
 */
@Document(collection = "RecordLogin")
public class RecordLogin implements Serializable {

    private static final long serialVersionUID = 7813925288316174163L;

    @Id
    private String id;

    private String login;

    private Long loginDate;

    private Long refreshDate;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Long loginDate) {
        this.loginDate = loginDate;
    }

    public Long getRefreshDate() {
        return refreshDate;
    }

    public void setRefreshDate(Long refreshDate) {
        this.refreshDate = refreshDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
