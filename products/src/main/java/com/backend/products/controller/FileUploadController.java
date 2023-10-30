package com.backend.products.controller;

import com.backend.products.services.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private CloudStorageService cloudStorageService; // Inyecta el servicio de Google Cloud Storage

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            // Llama al servicio de Google Cloud Storage para cargar el archivo
            cloudStorageService.uploadFile(fileName, fileBytes);

            return "El archivo se ha cargado correctamente.";
        } catch (Exception e) {
            return "Error al cargar el archivo: " + e.getMessage();
        }
    }
}
