package top.codesky.forcoder.sensitivefilter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Date: 2019/5/24 14:06
 * @Author: codesky
 * @Description: 字典树节点
 */
public class TrieNode {
    //父节点
    private TrieNode parent;
    //失败索引
    private TrieNode failure;
    //子节点
    private TrieNode[] childNodes;

    private char word;
    //关键词终结标志
    private boolean end;
    //map维护词与节点对应的关系
    private Map<Character, TrieNode> childMap;
    //关键词的最后一个词，保留完整的关键词信息
    //end=true时，不为空
    private String result;

    private static final TrieNode[] DEFAULT_EMPTY_DATA = {};

    public TrieNode(TrieNode parent, char word) {
        this.parent = parent;
        this.word = word;
        this.childMap = new HashMap<>();
        this.childNodes = DEFAULT_EMPTY_DATA;
        this.end = false;
    }

    public void addTrieNode(TrieNode node) {
        childMap.put(node.word, node);
        childNodes = new TrieNode[childMap.size()];
        Iterator<TrieNode> iterator = childMap.values().iterator();
        for (int i = 0; i < childNodes.length; i++) {
            if (iterator.hasNext()) {
                childNodes[i] = iterator.next();
            }
        }
    }

    public boolean containChildNode(char word) {
        return childMap.get(word) != null;
    }

    public TrieNode getChildNode(char word) {
        return childMap.get(word);
    }

    public TrieNode getParent() {
        return parent;
    }

    public TrieNode[] getChildNodes() {
        return childNodes;
    }

    public char getWord() {
        return word;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isLeaf() {
        return childMap.size() == 0;
    }

    public TrieNode getFailure() {
        return failure;
    }

    public void setFailure(TrieNode failure) {
        this.failure = failure;
    }
}
