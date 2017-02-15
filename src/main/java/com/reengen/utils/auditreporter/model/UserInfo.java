package com.reengen.utils.auditreporter.model;

import java.util.ArrayList;
import java.util.List;

// Model class for users
public class UserInfo {
    private String userId;
    private String userName;
    private List<FileInfo> files;

    public UserInfo(final String userId, final String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public List<FileInfo> getFiles() {
        if (this.files == null) this.files = new ArrayList<>();
        return this.files;
    }

    public void setFiles(final List<FileInfo> files) {
        this.files = files;
    }

    public void addFile(final FileInfo fileInfo) {
        this.files.add(fileInfo);
    }
}
