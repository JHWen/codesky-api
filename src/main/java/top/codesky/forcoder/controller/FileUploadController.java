package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.common.exception.StorageException;
import top.codesky.forcoder.common.properties.StorageProperties;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.StorageService;
import top.codesky.forcoder.util.CodeskyUtils;

/**
 * @Date: 2019/5/10 13:55
 * @Author: codesky
 * @Description: 文件上传控制层
 */
@Api(tags = {"文件上传接口"})
@RestController
public class FileUploadController {
    private final StorageService storageService;

    private final StorageProperties properties;

    @Autowired
    public FileUploadController(StorageService storageService, StorageProperties properties) {
        this.storageService = storageService;
        this.properties = properties;
    }


    @ApiOperation(value = "上传文件", notes = "返回上传文件的访问url")
    @PostMapping(path = "/upload")
    public ResponseVo uploadFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        String filename = storageService.store(multipartFile);

        return ResponseVo.success(ResultCodeEnum.SUCCESS
                , CodeskyUtils.getFileUrl(properties.getRootUrl(), filename));
    }


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ResponseVo> handleStorageFileNotFound(StorageException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR));
    }
}
