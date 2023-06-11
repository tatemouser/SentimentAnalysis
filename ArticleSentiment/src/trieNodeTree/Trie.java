package trieNodeTree;
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int code) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.containsKey(c)) {
                current.put(c, new TrieNode());
            }
            current = current.get(c);
        }
        current.setEndOfWord(true);
        current.setCode(code);
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord();
    }

    public int getCode(String word) {
        TrieNode node = searchPrefix(word);
        if (node != null && node.isEndOfWord()) {
            return node.getCode();
        }
        return -1; // Return -1 if word not found or is not the end of a word
    }

    private TrieNode searchPrefix(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.containsKey(c)) {
                return null;
            }
            current = current.get(c);
        }
        return current;
    }
}