package top.codesky.forcoder.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Date: 2019/5/20 20:32
 * @Author: codesky
 * @Description: 文件上传返回参数，文件访问的url
 */
@Data
@AllArgsConstructor
public class UploadResultVO {
    private String fileUrl;
}
