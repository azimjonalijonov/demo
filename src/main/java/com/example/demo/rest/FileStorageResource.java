package com.example.demo.rest;

import com.example.demo.Entity.FileStorage;
import com.example.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api")
public class FileStorageResource {
    private final FileStorageService fileStorageService;

    @Value("${upload.server.folder}")
    private String serverFolderPath;

    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    @PostMapping("/upload")
    public ResponseEntity save(@RequestParam("file") MultipartFile multipartFile){
        FileStorage fileStorage =fileStorageService.save(multipartFile);
        return ResponseEntity.ok(fileStorage);
    }
    @GetMapping("/file-preview/{hashId}")
    public ResponseEntity preview(@PathVariable String hashId) throws MalformedURLException {
            FileStorage fileStorage = fileStorageService.findByHashId(hashId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName =\""+ UriEncoder.encode(fileStorage.getName()))
                    .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                    .contentLength(fileStorage.getFileSize())
                    .body(new FileUrlResource(String.format("%s/%s",this.serverFolderPath,fileStorage.getUploadFolder())));

    }
}
