package com.videoappbackend.Repository;

import com.videoappbackend.Entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepo extends JpaRepository<FileUpload,Integer> {
}
