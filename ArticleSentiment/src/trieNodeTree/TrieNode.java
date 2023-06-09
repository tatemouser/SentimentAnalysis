package trieNodeTree;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private Map<Character, TrieNode> links;
    private boolean endOfWord;
    private double code;

    public TrieNode() {
        links = new HashMap<>();
        endOfWord = false;
        code = -1;
    }

    public boolean containsKey(char c) {
        return links.containsKey(c);
    }

    public TrieNode get(char c) {
        return links.get(c);
    }

    public void put(char c, TrieNode node) {
        links.put(c, node);
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }
}
