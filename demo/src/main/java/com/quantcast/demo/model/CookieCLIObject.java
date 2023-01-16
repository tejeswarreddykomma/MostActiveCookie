package com.quantcast.demo.model;

public class CookieCLIObject {
    String filePath;
    String date;
    public CookieCLIObject(String filePath, String date) {
        super();
        this.filePath = filePath;
        this.date = date;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
