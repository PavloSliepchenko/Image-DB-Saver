package com.example.webtest.service.impl;

import com.example.webtest.model.Image;
import com.example.webtest.repository.ImageRepository;
import com.example.webtest.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public void saveImage(MultipartFile file) {
        if (!file.getContentType().startsWith("image")) {
            throw new RuntimeException("Please upload the picture!");
        }
        try {
            Image image = new Image();
            image.setPicByte(file.getBytes());
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image getPicture(String name) {
        return imageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("There is no such picture"));
    }
}
