package com.javaprojects.springbootbackend.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadInterFace {
    String uploadFileToFileSystem(MultipartFile file) throws IOException;
    byte[] downloadFileFromFileSystem(String name) throws IOException;
}
