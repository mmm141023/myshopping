package com.fendou.service;

import com.fendou.commen.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    ServerResponse uploadFile(MultipartFile file);
}
