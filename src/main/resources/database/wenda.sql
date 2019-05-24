/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : wenda

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 24/05/2019 21:22:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答内容',
  `excerpt` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回答内容摘要',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '回答评论数',
  `voteup_count` int(11) NOT NULL DEFAULT 0 COMMENT '回答点赞数',
  `is_anonymously` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名回答',
  `author_id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回答者id',
  `question_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `gmt_create` datetime(0) NOT NULL COMMENT '回答创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '回答修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_answer_author_to_user`(`author_id`) USING BTREE,
  INDEX `pk_answer_to_question`(`question_id`) USING BTREE,
  CONSTRAINT `pk_answer_author_to_user` FOREIGN KEY (`author_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_answer_to_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES (2, '<p>什么叫阉割？</p><p>说难听点就像太监一样，男人都有的那玩意就他没有。</p><p>小米9在同价位机型中有什么是大家都有的就它没有？</p><p>魅族16s的横向线性马达？iQOO的44W快充？荣耀V20的挖孔屏？Reno的全景屏？</p><p>没有这些根本就不算阉割，因为这些只是不同品牌的差异化卖点，并不是共有的。</p><p>这就跟小米9配备20W无线快充而其他同价位机型没有，同样不能说其他机型阉割了无线快充一样。</p><figure data-size=\"normal\"><noscript><img src=\"https://pic2.zhimg.com/50/v2-a08ec1522ec6a902198fe0747ae3a6fc_hd.jpg\" data-rawwidth=\"800\" data-rawheight=\"800\" data-size=\"normal\" data-caption=\"\" data-default-watermark-src=\"https://pic3.zhimg.com/50/v2-f5f1aa479816120426232d7e2114cb39_hd.jpg\" class=\"origin_image zh-lightbox-thumb\" width=\"800\" data-original=\"https://pic2.zhimg.com/v2-a08ec1522ec6a902198fe0747ae3a6fc_r.jpg\"/></noscript><img src=\"data:image/svg+xml;utf8,&lt;svg xmlns=&#39;http://www.w3.org/2000/svg&#39; width=&#39;800&#39; height=&#39;800&#39;&gt;&lt;/svg&gt;\" data-rawwidth=\"800\" data-rawheight=\"800\" data-size=\"normal\" data-caption=\"\" data-default-watermark-src=\"https://pic3.zhimg.com/50/v2-f5f1aa479816120426232d7e2114cb39_hd.jpg\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"800\" data-original=\"https://pic2.zhimg.com/v2-a08ec1522ec6a902198fe0747ae3a6fc_r.jpg\" data-actualsrc=\"https://pic2.zhimg.com/50/v2-a08ec1522ec6a902198fe0747ae3a6fc_hd.jpg\"/></figure><p>那么小米9有什么阉割吗？</p><p>我认为完全没有，实际上说小米9是同价位机型中功能配置最均衡的手机一点不为过。</p><p>小米9最大的槽点不过是3300mAh电池，有点小，但只提电池容量不提机身尺寸就是耍流氓，这更应该看作取舍而不是阉割。</p><p>况且小米还提供了无线充电三件套解决方案。</p><p>换句话说，如果小米9电池容量提升到4000mAh，那么除非你讨厌小米这个品牌或者买不到，否则单论硬件配置3000价位没有什么理由不选择小米9。</p><figure data-size=\"normal\"><noscript><img src=\"https://pic4.zhimg.com/50/v2-08725dc9c7777439d4417be7a9e798c7_hd.jpg\" data-rawwidth=\"690\" data-rawheight=\"1227\" data-size=\"normal\" data-caption=\"\" data-default-watermark-src=\"https://pic3.zhimg.com/50/v2-37c43269e3689baf97f4d642a378a4cf_hd.jpg\" class=\"origin_image zh-lightbox-thumb\" width=\"690\" data-original=\"https://pic4.zhimg.com/v2-08725dc9c7777439d4417be7a9e798c7_r.jpg\"/></noscript><img src=\"data:image/svg+xml;utf8,&lt;svg xmlns=&#39;http://www.w3.org/2000/svg&#39; width=&#39;690&#39; height=&#39;1227&#39;&gt;&lt;/svg&gt;\" data-rawwidth=\"690\" data-rawheight=\"1227\" data-size=\"normal\" data-caption=\"\" data-default-watermark-src=\"https://pic3.zhimg.com/50/v2-37c43269e3689baf97f4d642a378a4cf_hd.jpg\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"690\" data-original=\"https://pic4.zhimg.com/v2-08725dc9c7777439d4417be7a9e798c7_r.jpg\" data-actualsrc=\"https://pic4.zhimg.com/50/v2-08725dc9c7777439d4417be7a9e798c7_hd.jpg\"/></figure><p class=\"ztext-empty-paragraph\"><br/></p><p>便宜没好货，这是前人总结下来的经验。</p><p>如果死死地抓住这句话，对自己是警醒也是误导。</p><p>问题的关键在于，你得搞清楚小米为什么会更便宜具备更高的性价比，而不是盲目定一个莫须有的“没好货”的罪名。</p><a data-draft-node=\"block\" data-draft-type=\"link-card\" href=\"https://www.zhihu.com/question/311171224/answer/590016077\" data-image=\"https://pic4.zhimg.com/v2-f319fb2ffed48456cf62530973e54343_180x120.jpg\" data-image-width=\"602\" data-image-height=\"340\" class=\"internal\">小米手机为什么要低价高配？</a><p>在上面这个问题下我讨论过小米为啥卖得这么便宜：</p><p>1、传统线下渠道的缺失，小米利润率并不比ov低很多，ov硬件配置相对高价低配很大一部分是让利给渠道商。  </p><p>2、小米营销多年来一直玩“不花钱营销”，这几年虽然也耗资不菲，但是和hov比依然是小巫见大巫。  </p><p>3、小米手机的核心元器件虽然不错，但是设计质感做工等多年来很一般，甚至可以说略差，这部分也是成本。  </p><p>4、小米手机多年来都是产一批卖一批的抢购模式，不需要大量备货，不容易产生库存，同时资金周转速率也比较快，可以节省成本。  </p><p>5、小米在研发投入不如传统大厂，众所周知，小米的员工待遇在同行中并不高，薪资成本可以省下一部分。  </p><p>6、除了手机，小米的生态链产品以及互联网服务可以赚取利润，以弥补手机硬件的微利。</p><p>7、小米售后服务体系不如hov那么完善。</p><p class=\"ztext-empty-paragraph\"><br/></p><p class=\"ztext-empty-paragraph\"><br/></p><p>很多人提到的渠道和宣传可以节省成本，但是这并不意味着阉割。</p><p>你可以理解成这就是小米的商业模式，实际上手机行业已经相当透明了，没那么多阴谋论。</p><p>小米的优点是高性价比、MIUI、IoT产品丰富，缺点是产品品控、配色、颜值一般，售后服务体系有待完善。</p><p>优缺点都是明明白白的。</p><p>举个例子，如果你是个生活在一二线城市的直男，那么小米手机就比较适合你，颜值不优秀也还过得去，小米一二线城市的售后并不比其他品牌差多少，而且还有超越同价位机型的综合硬件配置，优秀的安卓Rom，丰富的智能家居产品。</p><p>单论产品，你可以说小米9不完美，可以说不符合你的需求，可以说你不喜欢，但是小米9没有任何阉割，这是我的看法。</p>', '什么叫阉割？ 说难听点就像太监一样，男人都有的那玩意就他没有。 小米9在同价位机型中有什么是大家都有的就它没有？ 魅族16s的横向线性马达？iQOO的44W快充？荣耀V20的挖孔屏？Reno的全景屏？ 没有这些根本就不算阉割，因为这些只是不同品牌的差异化卖点，并不是共有的。 这就跟小米9配备20W无线快充而其他同价位机型没有，同样不能说其他机型阉割了无线快充一样。 那么小米9有什么阉割吗？ 我认为', 0, 0, 0, 9, 3, '2019-05-07 21:20:39', '2019-05-07 21:20:39');
INSERT INTO `answer` VALUES (3, '<p><b>我的理解是，同价位都有的东西，不上才算阉割，至于多出的东西那叫做卖点。</b></p><p>拿笔记本举例，现在这个年代一个游戏本没有SSD可以算作阉割，因为同价位基本都有（当然也要看配置），但如果你上了一个512的SSD，这就算作卖点了。</p><p>就这点来说v20单摄后置指纹其实是阉割了，iqoo没有长焦也算阉割了……当然就缺失的东西来说，小米也有一个电池小的问题，但这个成本很低，说是阉割就很勉强了。</p><p>至于隐性的东西……小米还在旗舰机上很少做做砍天线、砍传感器、砍材质之类的事情。</p><p><b>但是呢，</b></p><p>消费电子产品并不是单纯的参数打架，一个好的产品不仅需要没有阉割，也需要一定的卖点。更何况国内手机市场已经趋近饱和，没有卖点的产品，注定淹没在茫茫机海之中。</p><p>因此在较低价位的产品中，广泛存在着下面这样的思路：</p><h2>以阉割换卖点</h2><p>成本是固定的，同样成本之下你要在某些方面高别人一头，就必然要在某些方面砍一刀。砍的好，田忌赛马，砍不好，产品暴死。</p><p>小米的思路则是不敢砍，把能上的都给你上了，很均衡，很水桶，但是也少有亮点。</p><p>拿一个被说烂的例子。比较荣耀8x和红米note7，荣耀8x在SoC、相机模组等几乎全面不如note7，但是为啥还能卖的好？</p><p>就是那个千元级里难得的下巴啊。</p><p>这就是以阉割换卖点了。这个例子其实不是特别好，但胜在容易理解。毕竟这个阉割是相对的，以8x的素质，在韭菜机遍地的线下是很容易卖的好的。</p><p>这个逻辑的核心在于把握市场需求，需求其实还算好把握的，但是关键在于市场需求是变动的，产品研发又需要时间，等到产品上市，风向变了怎么办？</p><p>小米很害怕承担这个风险，所以生产出的都是没有阉割的产品。但没有阉割＋性价比，最后结果就是平庸。</p><figure data-size=\"normal\"><noscript><img src=\"https://pic1.zhimg.com/50/v2-c75b079b18b8574741f076ebba481932_hd.jpg\" data-rawwidth=\"1080\" data-rawheight=\"1188\" data-size=\"normal\" data-default-watermark-src=\"https://pic4.zhimg.com/50/v2-6ee53e127edbe03b6c5f122bddc9562d_hd.jpg\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-c75b079b18b8574741f076ebba481932_r.jpg\"/></noscript><img src=\"data:image/svg+xml;utf8,&lt;svg xmlns=&#39;http://www.w3.org/2000/svg&#39; width=&#39;1080&#39; height=&#39;1188&#39;&gt;&lt;/svg&gt;\" data-rawwidth=\"1080\" data-rawheight=\"1188\" data-size=\"normal\" data-default-watermark-src=\"https://pic4.zhimg.com/50/v2-6ee53e127edbe03b6c5f122bddc9562d_hd.jpg\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-c75b079b18b8574741f076ebba481932_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/50/v2-c75b079b18b8574741f076ebba481932_hd.jpg\"/><figcaption>这样的思路下来，就是这样的产品。当然小米9其实是特意做了一个“轻薄”来当卖点的，可惜市场风向已经变了。看见没有，这就是反例_(:з」∠)_</figcaption></figure><p>这个逻辑是厂商决策的问题，也说不上孰好孰坏。其实作为消费者，也没必要对这个纠结太深。</p><p><b>阉割只要没割到你的痛点，那对你来说就不算太大的阉割；更重要的是卖点如果能戳到你，那阉割也就无所谓。</b></p><p><b>反过来，如果你在意阉割，那卖点再好，也比不过一台水桶机。</b></p><p>只比较参数，忽略自己需求，是很蠢的一件事情。参数当然重要，但不要被参数束缚住思维，<b>买个消费电子产品还不能让自己开心，那也太悲哀了。</b></p><p class=\"ztext-empty-paragraph\"><br/></p><hr/><p>多说一句，我和很多知友的观点不一样在于：作为一个米粉，我认为小米是可以承担这个风险的，只是思路还没有从性价比中转换过来，暂时不愿意承担。所以我仍然期待小米的新产品，尽管知乎的风向已经变了，也会去自发地为小米写一些东西（当然这点上<a class=\"member_mention\" href=\"https://www.zhihu.com/people/0d0e1ff61fa85180ab109735023fe6dc\" data-hash=\"0d0e1ff61fa85180ab109735023fe6dc\" data-hovercard=\"p$b$0d0e1ff61fa85180ab109735023fe6dc\">@Deadpool</a> 做的我好多了）。</p><p>无论未来如何，还是先祝小米好运。</p>', '我的理解是，同价位都有的东西，不上才算阉割，至于多出的东西那叫做卖点。 拿笔记本举例，现在这个年代一个游戏本没有SSD可以算作阉割，因为同价位基本都有（当然也要看配置），但如果你上了一个512的SSD，这就算作卖点了。 就这点来说v20单摄后置指纹其实是阉割了，iqoo没有长焦也算阉割了……当然就缺失的东西来说，小米也有一个电池小的问题，但这个成本很低，说是阉割就很勉强了。 至于隐性的东西……小米', 0, 0, 0, 10, 3, '2019-05-07 21:29:36', '2019-05-07 21:29:36');
INSERT INTO `answer` VALUES (4, '<p>小米主要主要成本节约不在硬件和研发上。主要省钱在渠道和宣传，而且有额外收入。</p><p><b>渠道</b>：同样的出厂价，HOV的售价大概比小米高两成以上。小米从创立之初发货主要走物流，很像当年的电视购物，这是小米模式的<b>核心。</b>小米更多的是一家模式创新公司，而不是科技创新。</p><p><b>宣传</b>：小米高管天天微博当网红，目的大概是省宣传费。2019年开春这个表现，显然是急了，但是采取的方法也是性价比策略，加大力度在微博当网红而不是加大力度投广告雇水军。显然也省了不少钱。</p><p><b>硬件</b>：小米当然还是有省钱的，很明显。搞极致性价比必然是按使用频率去掉特性，保留最重要的东西。举几个例子</p><ul><li>IP68防水，小米始终没做，MIX系列都不上。</li><li>USB 接口的视频输出：这东西魅族，华为，锤子都做过，MHL 或者 Type-C 等</li><li>USB 3.0: 事实上 USB 3.0 干扰无线信号的技术问题在 Type-C 出现后有很大改善，大厂都在做，小米始终没做</li><li>OIS: 这点其实还算比较良心，小米6和8的 OIS 效果都还可以，小米在中端型号和去掉了这个功能，不过容易造成误解的都主动说明了（Note3 对比小米6, 8 SE对比 8，均在发布会说明了去掉了 OIS）还行。小米 9 也去掉了 OIS，这显然不是技术问题，魅族 16s 做了，高管说电子防抖能替代 OIS 的说法不可信。</li><li>快充：万年 18W，小米 9 和 黑鲨 2 上了 27W</li><li>频段：小米数字系列支持的 4G 的频段，确实是在不断增多，也可见之前型号（4,6,8） 去掉了一些频段支持。</li><li>耳机孔：从小米 6 开始数字系列坚持阉割耳机孔，这是我最不爽的。不过能省多少钱不太清楚。</li><li>振动马达：这玩意其实挺重要，但是性价比真的低，目前也就国产魅族做的比较好，其他厂商半斤八两。</li></ul><p>当然，讨论以上功能的时候，<b>一定不能抛开售价</b>，这些功能确实用得到，有用，但是搭载这些功能的手机的价位可不是3000元以下的机器。</p><p><b>研发</b>：小米研发投入还是不错的。因为目前产品线没华为那么大，目前研发费用也不是需要特别高，比 OV 略高。比华为来讲，研发费用我目前看到的差别在于</p><ul><li>通信：小米不做设备，只做终端，众所周知传统通信研发费用高，利润低（现阶段），但是对于华为不得不做</li><li>芯片：海思除了 SOC，还有其他乱七八糟一大堆周围芯片，这东西也是非常烧钱。</li><li>底层研究：华为是一个在各方面做技术储备的公司，很多东西超出了手机厂商的范围。例如编译器（我不是非常清楚他们在做什么，但是大概是海思芯片的配套，这里不讨论“方舟编译器”，我认为宣传成分居多），分布式系统，分布式存储，数据库。这些情况我是从招聘了解到的，不过小米其实这些东西也有，但是规模显然小一些。</li><li>AI：说实话这点小米其实做的很多了，不好比较。</li></ul><p>研发比OV强的地方很多，总体研发投入也高，说下比 OV 差的地方：</p><ul><li>快充，这个步步高系做的东西，低压大电流确实不错，小米好长时间没有自研跟着高通。好在现在看到补齐的希望了</li><li>OPPO的潜望长焦: 这东西不归在硬件里是因为这东西生产成本其实不高，但是研发成本比较高，小米一直没做（但我大胆猜测小米肯定在做）</li></ul><p>总而言之，华为因为体量大的原因，有些研发不得不做。而小米目前还没到这么大体量，费用自然低一些。</p><p>另外需要指出的是，OV的销售额虽然大，但是研发投入低于小米，原因就是售价中的渠道成本过高，导致OV的毛利并不比小米多多少。</p><p class=\"ztext-empty-paragraph\"><br/></p><p><b>手机硬件以外的收入</b>：</p><p>2018年度，互联网收入160亿，其中广告101亿。互联网增值服务59亿中，游戏收入27亿（没想到吧）。</p><ul><li>广告：这是小米额外收入的绝对大头，2015 年 ADUI 真的是能被人说到 2019 年。现在广告没以前那么烦人了，不过还是比 OVM 要多不少。另外小米电视的广告收入也不少</li><li>云服务：说实在我不觉得这块收入有多少</li><li>生态链产品和配件，很多产品售价非常小米范，但还有一些配件的<b>利润率</b>其实不低（手机壳），一些生态链的利润率也不低。不过这些利润率不低的东西<b>利润总量</b>好像并不高。</li></ul><p class=\"ztext-empty-paragraph\"><br/></p><p>总结：小米硬件上一直比较良心，不必担心。省的成本大多在别处。</p>', '小米主要主要成本节约不在硬件和研发上。主要省钱在渠道和宣传，而且有额外收入。 渠道：同样的出厂价，HOV的售价大概比小米高两成以上。小米从创立之初发货主要走物流，很像当年的电视购物，这是小米模式的核心。小米更多的是一家模式创新公司，而不是科技创新。 宣传：小米高管天天微博当网红，目的大概是省宣传费。2019年开春这个表现，显然是急了，但是采取的方法也是性价比策略，加大力度在微博当网红而不是加大力度', 0, 0, 0, 11, 3, '2019-05-07 21:32:18', '2019-05-07 21:32:18');
INSERT INTO `answer` VALUES (5, '回答测试问题', '回答测试问题', 0, 0, 0, 10, 1, '2019-05-08 17:28:04', '2019-05-08 17:28:04');
INSERT INTO `answer` VALUES (7, '<h3>回答测试问题</h3><p><br></p><ol><li>我是谁</li><li>我在那里</li><li>我要去哪里</li></ol><p>呵呵哒</p>', '回答测试问题 我是谁 我在那里 我要去哪里 呵呵哒', 0, 0, 0, 11, 1, '2019-05-08 17:34:36', '2019-05-08 17:34:36');
INSERT INTO `answer` VALUES (8, '测试回答问题，哈哈哈哈哈', '测试回答问题，哈哈哈哈哈', 0, 0, 0, 9, 1, '2019-05-13 12:06:20', '2019-05-13 12:06:20');
INSERT INTO `answer` VALUES (9, 'this is a test', 'this is a test', 0, 0, 0, 9, 1, '2019-05-13 12:13:03', '2019-05-13 12:13:03');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `from_id` bigint(11) NOT NULL COMMENT '消息',
  `to_id` bigint(11) UNSIGNED NOT NULL COMMENT '发送者id',
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '接收者id',
  `is_read` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已读',
  `conversation_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话id',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题内容',
  `answer_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的回答数',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的评论数',
  `follower_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的关注人数',
  `author_id` bigint(11) UNSIGNED NULL DEFAULT 0 COMMENT '提问者的id，匿名id为0',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '上一次的更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_question_to_user_authentication_info`(`author_id`) USING BTREE,
  CONSTRAINT `pk_question_to_user_authentication_info` FOREIGN KEY (`author_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '测试问题', '这是一个测试问题', 0, 0, 0, 9, '2019-05-07 16:35:15', '2019-05-07 16:35:15');
INSERT INTO `question` VALUES (2, '如何看待甲骨文公司大规模裁员？', '这是一条描述信息。。。。', 0, 0, 0, 9, '2019-05-07 16:40:51', '2019-05-07 16:40:51');
INSERT INTO `question` VALUES (3, '为什么小米9能卖那么便宜，到底是在哪里阉割的?', '就像荣耀V20少一根5G wifi天线一样的阉割，大多数消费者不知道的方面有哪些', 0, 0, 0, 10, '2019-05-07 16:43:05', '2019-05-07 16:43:05');
INSERT INTO `question` VALUES (4, '为什么根据 IP 地址查询物理所在地，而不是 mac 地址？', '不是说mac地址是计算机网卡唯一的地址吗？这样不是可以直接定位到某一台机器吗？为什么要用IP地址阿？是因为服务器历史记录只会存储IP地址还是什么原因阿？求大神解释一下～', 0, 0, 0, 10, '2019-05-07 16:59:44', '2019-05-07 16:59:44');
INSERT INTO `question` VALUES (5, '如何评价《权力的游戏》第八季第四集 S08E04「The Last Of The Starks」 ?', '本集剧情进展有哪些亮点？\n\n前情提要：', 0, 0, 0, 9, '2019-05-07 17:02:24', '2019-05-07 17:02:24');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_addition_info
-- ----------------------------
DROP TABLE IF EXISTS `user_addition_info`;
CREATE TABLE `user_addition_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '关联用户认证表外键',
  `gender` tinyint(2) NOT NULL DEFAULT 1 COMMENT '性别',
  `avatar_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户头像url',
  `headline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '一句话介绍',
  `business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '所在行业',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_user_additional_info_to_authentication_info`(`user_id`) USING BTREE,
  CONSTRAINT `pk_user_additional_info_to_authentication_info` FOREIGN KEY (`user_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户个人描述信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_addition_info
-- ----------------------------
INSERT INTO `user_addition_info` VALUES (9, 'codesky', 9, 1, 'http://localhost:8081/images/5deaa10bcc6b4845afc8bddeeb96e977.jpg', '不会写代码的程序员', '互联网', '2019-05-05 17:26:55', '2019-05-19 14:42:23');
INSERT INTO `user_addition_info` VALUES (10, 'helloworld', 10, 0, 'https://images.nowcoder.com/images/20170615/1347798_1497491166458_8E7B0656F73A23F6ECE12F2DAD72C5A7@0e_100w_100h_0c_1i_1o_90Q_1x', '不会写代码的程序员', '计算机软件', '2019-05-05 20:55:12', '2019-05-05 20:55:12');
INSERT INTO `user_addition_info` VALUES (11, '扬帆飞剑', 11, 1, 'https://images.nowcoder.com/images/20170615/1347798_1497491166458_8E7B0656F73A23F6ECE12F2DAD72C5A7@0e_100w_100h_0c_1i_1o_90Q_1x', '这是一句话介绍', '计算机软件', '2019-05-07 21:30:40', '2019-05-07 21:30:40');

-- ----------------------------
-- Table structure for user_authentication_info
-- ----------------------------
DROP TABLE IF EXISTS `user_authentication_info`;
CREATE TABLE `user_authentication_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `is_account_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户未过期标志',
  `is_account_non_locked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户未锁定标志',
  `is_credentials_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '密码未过期标志',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户可用标志',
  `gmt_create` datetime(0) NOT NULL COMMENT '账户创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '账户的最近一次修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录认证相关信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_authentication_info
-- ----------------------------
INSERT INTO `user_authentication_info` VALUES (9, 'codesky', '$2a$10$IshJ45BcLckOaTYCbNJXsezfFXBdttjZaAkjESkwYd0BIvqU1mvcK', 1, 1, 1, 1, '2019-05-05 17:26:55', '2019-05-05 17:26:55');
INSERT INTO `user_authentication_info` VALUES (10, 'helloworld', '$2a$10$P9ZbGyi3nUxhOBux36cho.q0U0hX0eGuu7IrK5o90/yCkyXITNe/K', 1, 1, 1, 1, '2019-05-05 20:55:12', '2019-05-05 20:55:12');
INSERT INTO `user_authentication_info` VALUES (11, '扬帆飞剑', '$2a$10$61Z/HNNx0/tJU/HEHsl.uukfx7c8gI5ak8nj.QlziBsD4mMOw.7Ri', 1, 1, 1, 1, '2019-05-07 21:30:40', '2019-05-07 21:30:40');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_user_role_to_user`(`user_id`) USING BTREE,
  INDEX `pk_user_role_to_role`(`role_id`) USING BTREE,
  CONSTRAINT `pk_user_role_to_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_user_role_to_user` FOREIGN KEY (`user_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色授权表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
