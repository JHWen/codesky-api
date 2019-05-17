package top.codesky.forcoder.controller;

import com.sun.org.apache.bcel.internal.classfile.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.common.exception.StorageException;
import top.codesky.forcoder.common.properties.StorageProperties;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.params.UserAdditionInfoUpdateParams;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.StorageService;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.CodeskyUtils;

import java.util.Date;

/**
 * @Date: 2019/5/10 13:55
 * @Author: codesky
 * @Description: 文件上传控制层
 */
@Api(tags = {"文件上传接口"})
@RestController
@RequestMapping("/api")
public class FileUploadController {
    private final StorageService storageService;

    private final StorageProperties properties;

    private final UserService userService;

    @Autowired
    public FileUploadController(StorageService storageService, StorageProperties properties, UserService userService) {
        this.storageService = storageService;
        this.properties = properties;
        this.userService = userService;
    }


    @ApiOperation(value = "上传文件", notes = "返回上传文件的访问url")
    @PostMapping(path = "/upload")
    public ResponseVo uploadFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        String filename = storageService.store(multipartFile);

        return ResponseVo.success(ResultCodeEnum.SUCCESS
                , CodeskyUtils.getFileUrl(properties.getRootUrl(), filename));
    }

    /**
     * 更新用户头像
     *
     * @param userInfo
     * @param multipartFile
     * @return
     */
    @ApiOperation(value = "更新用户头像", notes = "返回头像上传结果")
    @PostMapping(path = "/me/avatar")
    public ResponseVo updateAvatar(@SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo,
                                   @RequestParam("file") MultipartFile multipartFile) {
        //存储头像图片
        String filename = storageService.store(multipartFile);
        String avatarUrl = CodeskyUtils.getFileUrl(properties.getRootUrl(), filename);

        UserAdditionInfoUpdateParams params = new UserAdditionInfoUpdateParams();
        params.setUserId(userInfo.getId());
        params.setAvatarUrl(avatarUrl);
        params.setGmtModified(new Date());

        if (userService.updateUserAdditionInfo(params)) {
            return ResponseVo.success(ResultCodeEnum.SUCCESS);
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ResponseVo> handleStorageFileNotFound(StorageException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR));
    }
}
