package top.codesky.forcoder.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Date: 2019/5/10 13:03
 * @Author: codesky
 * @Description: 文件存储服务
 */
public interface StorageService {
    void init();

    String store(MultipartFile file);
}
