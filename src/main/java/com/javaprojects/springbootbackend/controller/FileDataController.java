package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.model.FileData;
import com.javaprojects.springbootbackend.service.ApiReturn;
import com.javaprojects.springbootbackend.service.FileService;
import com.javaprojects.springbootbackend.service.TestReturnObject;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/fileSystem")
public class FileDataController {

    @Autowired
    private FileService fileService;



    @PostMapping
    public ResponseEntity<TestReturnObject> uploadFile(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = fileService.uploadFileToFileSystem(file);
        TestReturnObject response = new TestReturnObject(uploadImage, file.getOriginalFilename(), file.getContentType(), "File Uploaded Successfully", "200");
        return ResponseEntity.ok(response);
//        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);

    }

    @GetMapping("{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] imageData = fileService.downloadFileFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

    }








//    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
//        String filepath = FOLDER_PATH + file.getOriginalFilename();
//        System.out.println("file i don't find you"+ filepath);
//        FileData fileData = fileDataRepository.save(FileData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filepath).build()
//        );
//        file.transferTo(new File(filepath));
//        if (fileData != null){
//            return "File Uploaded Successfully" + filepath;
//        }
//    return null;
//    }
//
//    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {
//        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
//        String filePath = fileData.get().getFilePath();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//
//    }
}
