package com.javaprojects.springbootbackend.service;

import com.javaprojects.springbootbackend.interfaces.FileUploadInterFace;
import com.javaprojects.springbootbackend.model.FileData;
import com.javaprojects.springbootbackend.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileService implements FileUploadInterFace {

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "/media/rafsun/E08C07D38C07A2E0/Java Springboot Test Project/spring-boot-backend/src/main/resources/myFiles/";

    //UploadFile
    @Override
    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String filepath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filepath).build()
        );
        file.transferTo(new File(filepath));
        if (fileData != null){
            return filepath;

        }
        return null;
//        return  ApiReturn.responseBuilder("Successfully uploaded", HttpStatus.OK, filepath);
    }

    //Download file
    @Override
    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;

    }


}
