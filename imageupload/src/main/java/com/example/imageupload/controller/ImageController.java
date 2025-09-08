package com.example.imageupload.controller;

import com.example.imageupload.entity.Image;
import com.example.imageupload.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageController {

    private final ImageRepository repo;

    public ImageController(ImageRepository repo) {
        this.repo = repo;
    }

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String url = "/uploads/" + fileName;

        Image image = new Image();
        image.setFileName(fileName);
        image.setUrl(url);
        repo.save(image);

        return ResponseEntity.ok(image);
    }

    @GetMapping
    public List<Image> getAllImages() {
        return repo.findAll();
    }
}
