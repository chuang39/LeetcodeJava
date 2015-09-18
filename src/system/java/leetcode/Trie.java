package system.java.leetcode;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khuang on 9/13/15.
 */
class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode(){}

    public TrieNode(char c) {
        this.c = c;
    }
}


public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            // set leaf
            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }

    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = this.root.children;

        TrieNode t = null;
        for (Character c: str.toCharArray()) {
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }

    // return if the word is in the trie
    public boolean search(String str) {
        TrieNode t = searchNode(str);
        if (t != null && t.isLeaf) {
            return true;
        } else {
            return false;
        }
    }

    // return if the trie has the given prefix
    public boolean searchPrefix(String) {
        TrieNode t = searchNode(String);
        if (t == null)
            return false;
        else
            return true;
    }
}
