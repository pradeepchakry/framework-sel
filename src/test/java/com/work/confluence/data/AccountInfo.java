package com.work.confluence.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Account Information POGO Class
 */
public class AccountInfo {
    Logger logger = LoggerFactory.getLogger(AccountInfo.class);
    private String url;
    private String userName;
    private String password;
    private String userSpace;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserSpace() {
        return userSpace;
    }

    public void setUserSpace(String userSpace) {
        this.userSpace = userSpace;
    }
}
