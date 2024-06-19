package com.example.webtest.service;

import com.example.webtest.model.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
    void saveImage(MultipartFile file);

    Image getPicture(String name);
}
