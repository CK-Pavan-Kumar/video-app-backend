package com.videoappbackend.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController()
@CrossOrigin("*")
public class FileUploadController {

    private final AmazonS3 awsS3Client;

    @Autowired
    public FileUploadController(AmazonS3 awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    @PostMapping("/FileUpload")
    public ResponseEntity<String> fileUpload(@RequestParam("images") MultipartFile images) {
        try {
            String filenameExtension = StringUtils.getFilenameExtension(images.getOriginalFilename());

            String key = UUID.randomUUID().toString() + "." + filenameExtension;

            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentLength(images.getSize());
            metaData.setContentType(images.getContentType());

            try (InputStream inputStream = images.getInputStream()) {
                awsS3Client.putObject("fileupload", key, inputStream, metaData);
            }

            awsS3Client.setObjectAcl("fileupload", key, CannedAccessControlList.PublicRead);

            var url = awsS3Client.getUrl("fileupload", key).toString();

            return new ResponseEntity<>(url, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occurred while uploading the file", e);
        }
    }
}
