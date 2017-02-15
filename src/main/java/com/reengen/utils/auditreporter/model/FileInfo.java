package com.reengen.utils.auditreporter.model;

//Model class for Files
public class FileInfo {
    private final String name;
    private final String id;
    private final int size;

    private UserInfo userInfo;

    public FileInfo(final String id, final String name, final int size) {
        this.name = name;
        this.id = id;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FileInfo fileInfo = (FileInfo) o;

        if (this.size != fileInfo.size) return false;
        if (!this.name.equals(fileInfo.name)) return false;
        if (!this.id.equals(fileInfo.id)) return false;
        return this.userInfo.equals(fileInfo.userInfo);
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.id.hashCode();
        result = 31 * result + this.size;
        result = 31 * result + this.userInfo.hashCode();
        return result;
    }

   /*
    @Override
    public int compareTo(final FileInfo o) {
        if (getSize() > o.getSize())
            return 1;
        else
            return 0;
    }*/
}
