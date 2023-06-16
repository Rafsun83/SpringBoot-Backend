package com.javaprojects.springbootbackend.service;

public class TestReturnObject {
    private String filePath;
    private String name;
    private String type;
    private String message;

    private String code;

    public TestReturnObject(String filePath, String name, String type, String message, String code) {
        this.filePath = filePath;
        this.name = name;
        this.type = type;
        this.message = message;
        this.code = code;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public  String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus(){
        return code;
    }

    public void setHttpStatus(String code) {
        this.code = code;
    }
}
