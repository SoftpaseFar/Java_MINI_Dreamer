package cn.badguy.dream.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file, String path);
}
