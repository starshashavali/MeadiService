package com.tcs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.domain.Media;
import com.tcs.service.MediaService;


@RestController
public class MediaController {
    @Autowired
    private MediaService mediaService;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Media savedContent = mediaService.saveContent(file);
        return ResponseEntity.ok("File uploaded successfully with ID: " + savedContent.getId());
    }

    @PostMapping("/saveText")
    public ResponseEntity<String> saveText(@RequestParam("text") String text) {
        Media savedMedia = mediaService.saveText(text);
        return ResponseEntity.ok("Text saved successfully with ID: " + savedMedia.getId());
    }

    @GetMapping("/getText/{id}")
    public ResponseEntity<String> getTextById(@PathVariable Long id) {
        String text = mediaService.getTextById(id);
        return ResponseEntity.ok(text);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Media mediaContent = mediaService.getContent(id);
        MediaType mediaType = MediaType.valueOf(mediaContent.getContentType());
        
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(mediaContent.getData());
    }


}