package leetcode;

/**
 * ImplementTriePrefixTree
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 208. 实现 Trie (前缀树)
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-shu-jie-gou-by-oshdyr-4qgt/
 *
 * @since 2021-04-14
 */
public class ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app"));       // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}

class Trie {

    TrieNode head;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        head = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() < 1) {
            return;
        }

        TrieNode next = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode curr = next.get(c);
            if (curr == null) {
                next.set(c, new TrieNode());
                curr = next.get(c);
            }
            next = curr;
        }
        next.setLeaf(true);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() < 1) {
            return false;
        }

        TrieNode next = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode curr = next.get(c);
            if (curr == null) {
                return false;
            }
            next = curr;
        }
        return next.isLeaf();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() < 1) {
            return false;
        }

        TrieNode next = head;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            TrieNode curr = next.get(c);
            if (curr == null) {
                return false;
            }
            next = curr;
        }
        return true;
    }
}

class TrieNode {
    private static final int SIZE = 26;
    private TrieNode[] children;
    private boolean isLeaf;

    public TrieNode() {
        children = new TrieNode[SIZE];
        isLeaf = false;
    }

    public boolean set(char c, TrieNode node) {
        int idx = c - 'a';
        if (idx < 0 || idx >= SIZE) {
            return false;
        }
        children[idx] = node;
        return true;
    }

    public TrieNode get(char c) {
        int idx = c - 'a';
        if (idx < 0 || idx >= SIZE) {
            return null;
        }
        return children[idx];
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}