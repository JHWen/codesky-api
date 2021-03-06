package top.codesky.forcoder.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.codesky.forcoder.model.entity.UserAdditionInfo;

import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @Date: 2019/5/5 11:30
 * @Author: codesky
 * @Description: 工具类
 */
public class CodeskyUtils {
    //白名单机制 防止xss
    private static final Whitelist whitelist = Whitelist.basicWithImages();

    private static final String DEFAULT_USERNAME = "anonymous";
    private static final String DEFAULT_AVATAR_URl = "https://images.nowcoder.com/images/20170615/1347798_1497491166458_8E7B0656F73A23F6ECE12F2DAD72C5A7@0e_100w_100h_0c_1i_1o_90Q_1x";
    private static final String DEFAULT_BUSINESS = "计算机软件";
    private static final String DEFAULT_HEADLINE = "这是一句话介绍";
    private static final short DEFAULT_GENDER = 1;
    private static final long DEFAULT_ID = 0L;

    public static final long DEFAULT_SYSTEM_ID = 0L;

    private static final UserAdditionInfo anonymousUser = new UserAdditionInfo(
            DEFAULT_ID, DEFAULT_USERNAME, DEFAULT_ID, DEFAULT_GENDER, DEFAULT_AVATAR_URl, DEFAULT_HEADLINE, DEFAULT_BUSINESS,
            new Date(), new Date()
    );

    private CodeskyUtils() {

    }

    /**
     * 匿名用户的默认信息
     *
     * @return 匿名用户
     */
    public static UserAdditionInfo getAnonymousUser() {
        return anonymousUser;
    }


    /**
     * 白名单机制过滤html标签
     *
     * @param html
     * @return
     */
    public static String cleanHtml(String html) {
        return Jsoup.clean(html, whitelist);
    }


    /**
     * uuid对文件进行重命名
     * todo:改进方法，使用文件的hash值对文件去重
     *
     * @param oldFilename 原文件名
     * @return 新文件名
     */
    public static String renameFile(String oldFilename) {
        if (StringUtils.isEmpty(oldFilename)) {
            throw new NullPointerException("filename is empty!");
        }
        String suffixName = oldFilename.substring(oldFilename.lastIndexOf("."));
        return generateUUID() + suffixName;
    }

    private static String generateUUID() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "");
    }

    /**
     * 获取文件在服务器上的访问地址
     *
     * @param baseUrl  基础URL
     * @param filename 文件名
     * @return URI for file
     */
    public static String getFileUrl(String baseUrl, String filename) {
        return URI.create(baseUrl)
                .resolve(filename)
                .toString();
    }

    /**
     * get the stack trace string of throwable
     *
     * @param t the <code>Throwable</code>
     * @return the stack trace string  generated by the exception's
     * <code>printStackTrace(PrintWriter)</code> method
     */
    public static String getStackTrace(final Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
