package com.tcs.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.domain.Media;
import com.tcs.repository.MediaRepository;


@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    
    public Media saveText(String text) {
        Media media = new Media();
        media.setData(text.getBytes(StandardCharsets.UTF_8));
        media.setContentType("text/plain");
        return mediaRepository.save(media);
    }

    public String getTextById(Long id) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new RuntimeException("Media not found"));
        if (!"text/plain".equals(media.getContentType())) {
            throw new IllegalStateException("Requested media is not text");
        }
        return new String(media.getData(), StandardCharsets.UTF_8);
    }

    public Media saveContent(MultipartFile file) throws IOException {
        Media mediaContent = new Media();
        String contentType = file.getContentType();
        byte[] dataBytes;

        if ("text/plain".equals(contentType)) {
            dataBytes = file.getInputStream().readAllBytes();
        } else {
            dataBytes = file.getBytes();
        }

        mediaContent.setData(dataBytes);
        mediaContent.setContentType(contentType);
        return mediaRepository.save(mediaContent);
    }

    public Media getContent(Long id) {
        return mediaRepository.findById(id).orElseThrow();
    }


}