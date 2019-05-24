package top.codesky.forcoder.sensitivefilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Date: 2019/5/24 14:27
 * @Author: codesky
 * @Description: 敏感词处理业务逻辑
 */
@Service
@Slf4j
public class SensitiveFilter implements InitializingBean {

    private TrieNode rootNode;

    private static final char DEFAULT_REPLACE_CHARACTER = '*';

    @Override
    public void afterPropertiesSet() throws Exception {
        // 1.从文件SensitiveWords.txt读取敏感词
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        List<String> patterns = new ArrayList<>();
        try {
            inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("SensitiveWords.txt");
            Assert.notNull(inputStream, "敏感词库文件不存在");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
                patterns.add(lineText.trim());
            }
        } catch (Exception exception) {
            log.error("敏感词库读取失败:{}", exception.getMessage());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        // 2.构建字典树
        buildTrieTree(patterns);
        // 3.创建失败索引，加快扫描速度
        createFailureIndex();
    }


    /**
     * build a Trie Tree
     *
     * @param patterns sensitive words array
     */
    private void buildTrieTree(List<String> patterns) {
        rootNode = new TrieNode(null, ' ');
        TrieNode parent;
        for (String pattern : patterns) {
            //跳过空串
            if (StringUtils.isEmpty(pattern)) {
                continue;
            }
            parent = rootNode;
            for (int i = 0; i < pattern.length(); i++) {
                if (parent.containChildNode(pattern.charAt(i))) {
                    parent = parent.getChildNode(pattern.charAt(i));
                } else {
                    TrieNode node = new TrieNode(parent, pattern.charAt(i));
                    parent.addTrieNode(node);
                    parent = node;
                }
            }
            parent.setResult(pattern);
            parent.setEnd(true);
        }
    }

    /**
     * create failure index
     */
    private void createFailureIndex() {
        //initial root and child nodes of root
        rootNode.setFailure(null);
        ArrayDeque<TrieNode> arrayDeque = new ArrayDeque<>();
        for (TrieNode node : rootNode.getChildNodes()) {
            node.setFailure(rootNode);
            for (TrieNode tmp : node.getChildNodes()) {
                arrayDeque.addLast(tmp);
            }
        }

        while (!arrayDeque.isEmpty()) {
            TrieNode node = arrayDeque.removeFirst();
            TrieNode failureIndex = node.getParent().getFailure();
            while (failureIndex != null && !failureIndex.containChildNode(node.getWord())) {
                failureIndex = failureIndex.getFailure();
            }
            if (failureIndex == null) {
                node.setFailure(rootNode);
            } else {
                node.setFailure(failureIndex.getChildNode(node.getWord()));
            }
            for (TrieNode tmp : node.getChildNodes()) {
                arrayDeque.addLast(tmp);
            }
        }
    }


    /**
     * replace sensitive word by *
     *
     * @param text text to replace sensitive word
     * @return text that has filtered
     */
    public String replaceSensitiveWord(String text) {
        char[] message = text.toCharArray();

        TrieNode pos = rootNode;
        for (int i = 0; i < message.length; i++) {
            while (pos != rootNode && !pos.containChildNode(text.charAt(i))) {
                pos = pos.getFailure();
            }
            if (pos.containChildNode(text.charAt(i))) {
                pos = pos.getChildNode(text.charAt(i));

                while (pos.isEnd()) {
                    //list.add(i - pos.getResult().length() + 1);
                    for (int j = i - pos.getResult().length() + 1; j <= i; j++)
                        message[j] = DEFAULT_REPLACE_CHARACTER;
                    if (!pos.isLeaf()) {
                        break;
                    }
                    pos = pos.getFailure();
                }
            }
        }
        return String.valueOf(message);
    }

    /**
     * count the number of sensitive word in text
     *
     * @param text text is ready to filter
     * @return Map  (sensitive word,the number of it)
     */
    public Map<String, Integer> countSensitiveWord(String text) {
        Map<String, Integer> resultMap = new HashMap<>();

        TrieNode pos = rootNode;
        for (int i = 0; i < text.length(); i++) {
            while (pos != rootNode && !pos.containChildNode(text.charAt(i))) {
                pos = pos.getFailure();
            }
            if (pos.containChildNode(text.charAt(i))) {
                pos = pos.getChildNode(text.charAt(i));

                while (pos.isEnd()) {
                    if (resultMap.get(pos.getResult()) == null) {
                        resultMap.put(pos.getResult(), 1);
                    } else {
                        resultMap.put(pos.getResult(), resultMap.get(pos.getResult()) + 1);
                    }

                    if (!pos.isLeaf()) {
                        break;
                    }
                    pos = pos.getFailure();
                }
            }
        }
        return resultMap;
    }

    /**
     * find sensitive positions in text
     *
     * @param text text is ready to filter
     * @return Map (sensitive word, position by index)
     */
    public Map<String, ArrayList<Integer>> findAllPosition(String text) {
        Map<String, ArrayList<Integer>> resultMap = new HashMap<>();
        TrieNode pos = rootNode;
        for (int i = 0; i < text.length(); i++) {
            while (pos != rootNode && !pos.containChildNode(text.charAt(i))) {
                pos = pos.getFailure();
            }
            if (pos.containChildNode(text.charAt(i))) {
                pos = pos.getChildNode(text.charAt(i));

                while (pos.isEnd()) {
                    ArrayList<Integer> list = resultMap.computeIfAbsent(pos.getResult(), k -> new ArrayList<>());
                    list.add(i - pos.getResult().length() + 1);
                    if (!pos.isLeaf()) {
                        break;
                    }
                    pos = pos.getFailure();
                }
            }
        }
        return resultMap;
    }


}
