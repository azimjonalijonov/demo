package com.example.demo.rest;

import com.example.demo.Entity.FileStorage;
import com.example.demo.service.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileStorageResource {
    private final FileStorageService fileStorageService;

    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    @PostMapping("/upload")
    public ResponseEntity save(@PathVariable("file") MultipartFile multipartFile){
        FileStorage fileStorage =fileStorageService.save(multipartFile);
        return ResponseEntity.ok(fileStorage);
    }

}
