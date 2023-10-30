package com.backend.products.services;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

@Service
public class CloudStorageService {

    private final Storage storage;
    private final String bucketName = "rellibikesimages.appspot.com"; // Reemplaza con el nombre de tu bucket

    public CloudStorageService() {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void uploadFile(String blobName, byte[] fileBytes) {
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/octet-stream").build();
        storage.create(blobInfo, fileBytes);
    }

    public byte[] downloadFile(String blobName) {
        Blob blob = storage.get(bucketName, blobName);
        return blob.getContent();
    }

    public void deleteFile(String blobName) {
        storage.delete(bucketName, blobName);
    }
}

