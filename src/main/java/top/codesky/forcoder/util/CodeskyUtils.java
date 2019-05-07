package top.codesky.forcoder.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import top.codesky.forcoder.model.entity.UserAdditionInfo;

import java.util.Date;

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

    private static final UserAdditionInfo anonymousUser = new UserAdditionInfo(
            DEFAULT_ID, DEFAULT_USERNAME, DEFAULT_ID, DEFAULT_GENDER, DEFAULT_AVATAR_URl, DEFAULT_HEADLINE, DEFAULT_BUSINESS,
            new Date(), new Date()
    );

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

}
