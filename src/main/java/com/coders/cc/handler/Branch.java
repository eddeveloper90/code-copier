/**
 * @author eddeveloper <ed.developer90@gmail.com>
 * Date :  2020-12-03
 * Time : 8:38 PM
 */
package com.coders.cc.handler;

import java.util.concurrent.atomic.AtomicInteger;

public class Branch {
    private static AtomicInteger _ID = new AtomicInteger(0);
    private int id;
    private int level = 0;
    private boolean directory = false;
    private int parentId;
    private String parentPath;
    private String path;
    private String ext;

    public Branch(int level, int parentId, String parentPath, String path, String ext) {
        this.level = level;
        this.path = path;
        this.setExt(ext);
        id = _ID.incrementAndGet();
        this.parentId = parentId;
        this.parentPath = parentPath;

//        System.out.println(this.toString());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        if (ext.contains(".")) {
            String[] parts = ext.split("\\.");
            this.ext = parts[parts.length - 1];
        } else {
            this.ext = "";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getFileName() {
        return getPath().replace(getParentPath(), "");
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", level=" + level +
                ", parentId=" + parentId +
                ", parentPath='" + parentPath + '\'' +
                ", path='" + path + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
