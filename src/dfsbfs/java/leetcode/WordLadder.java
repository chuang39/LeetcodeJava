package dfsbfs.java.leetcode;

import java.util.*;

/**
 * Created by khuang on 10/5/15.
 */

class Node {
    String word;
    int depth;
    public Node(String s, int d) {
        word = s;
        depth = d;
    }
}

public class WordLadder {
/*
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();

        if (beginWord == null || endWord == null || beginWord.length() == 0)
            return res;

        // Maintain a hashmap for visited words
        Map<String, List<Node>> visited = new HashMap<String, List<Node>>();

        // BFS to find the minimum sequence length
        getMinLength(beginWord, endWord, wordList, visited);



    }

    void getMinLength(String beginWord, String endWord, HashSet<String> dict
        Map<String, List<Node>> visited) {

        // maintain
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(beginWord, 1));

        dict.add(endWord);
        int lastLevel = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();


            for (int i = 0; i < node.word.length(); i++) {
                StringBuilder sb = new StringBuilder(node.word);
                char origin = sb.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin) continue;

                    sb.setCharAt(i, c);
                    String s = sb.toString();

                    if (s.equals(endWord)) {

                    }
                }
            }
        }
    }
*/
}