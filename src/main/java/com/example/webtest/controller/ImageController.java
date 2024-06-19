package com.example.webtest.controller;

import com.example.webtest.model.Image;
import com.example.webtest.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public String startPage() {
        return "start";
    }

    @PostMapping(value = "/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile file) {
        imageService.saveImage(file);
        return "start";
    }

    @GetMapping(value = "/{picName}")
    public ResponseEntity<byte[]> getPicture(@PathVariable String picName) {
        Image image = imageService.getPicture(picName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(image.getPicByte());
    }
}
