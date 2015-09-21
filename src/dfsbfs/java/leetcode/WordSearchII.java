package dfsbfs.java.leetcode;

import java.util.*;

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    TrieNode(){}
    TrieNode(char c) {this.c = c;}
}

class Pair<M, N> {
    M m;
    N n;
    public Pair(M m, N n) {
        this.m = m;
        this.n = n;
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        HashMap<Character, TrieNode> children = root.children;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!children.containsKey(c)) {
                TrieNode newT = new TrieNode(c);
                children.put(c, newT);
            }
            TrieNode t = children.get(c);
            children = t.children;

            if (i == s.length()-1)
                t.isLeaf = true;
        }
    }

    public void delete(String s) {
        TrieNode cur = root;
        TrieNode last = root;
        Stack<Pair<TrieNode, Character>> stack = new Stack<Pair<TrieNode, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cur.children.containsKey(c)) {
                stack.push(new Pair<>(cur, new Character(c)));
                cur = cur.children.get(c);
                last = cur;
            } else {
                break;
            }
        }

        if (!last.isLeaf)
            return;
        if (!last.children.isEmpty()) {
            last.isLeaf = false;
        } else {
            while (!stack.isEmpty()) {
                Pair<TrieNode, Character> p = stack.pop();
                p.m.children.remove(p.n);
                if (p.m.children.size() > 0 || p.m.isLeaf)
                    break;
            }
        }
    }
}
public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        Trie t = new Trie();
        for (String word : words) {
            t.insert(word);
        }

        ArrayList<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, t.root, sb, res, t);
            }
        }

        return res;
    }

    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};

    void dfs(char[][] board, int x, int y, TrieNode t, StringBuilder path, List<String> res, Trie root) {
        if (t.isLeaf) {
            res.add(path.toString());
            root.delete(path.toString());
        }

        path.append(board[x][y]);
        char temp = board[x][y];
        board[x][y] = '#';
        for(int i = 0; i < 4; i++) {
            if (isValid(board, x+dx[i], y+dy[i], t)) {
                char c = board[x+dx[i]][y+dy[i]];
                dfs(board, x + dx[i], y + dy[i], t.children.get(c), path, res, root);
            }
        }
        board[x][y] = temp;
        path.deleteCharAt(path.length()-1);

    }

    boolean isValid(char[][] board, int x, int y, TrieNode t) {
        boolean res = (x >= 0 && x < board.length && y >= 0 && y < board[0].length && t.children.containsKey(board[x][y]));
        System.out.println(res);
        return res;
    }

    public static void main(String [] args) {
        WordSearchII s = new WordSearchII();
        char[][] board = {{'a', 'a', 'a', 'a'},{'a', 'a', 'a', 'a'},{'a', 'a', 'a', 'a'}};
        String [] words = {"aaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaab"};
        List<String> res = s.findWords(board, words);
        for (String item: res)
            System.out.println(item);
    }
}

