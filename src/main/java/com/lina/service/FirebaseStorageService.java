package com.lina.service;

import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {
    public String uploadFile(MultipartFile file) throws IOException {
        // generate a new name;
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        // get a bucket and blob id;
        Bucket bucket = StorageClient.getInstance().bucket();
        bucket.create(newName, file.getInputStream(), file.getContentType());
        // Construct the download URL
        String encodedFileName = URLEncoder.encode(newName, StandardCharsets.UTF_8.name()).replace("+", "%20");
        return "https://firebasestorage.googleapis.com/v0/b/" + bucket.getName() + "/o/" + encodedFileName + "?alt=media";
        //return blob.getMediaLink(); // Or construct the download URL as needed
    }
}
