package others.java.leetcode;


import java.util.*;

public class AlienDictionary {
    void buildGraph(String[] words, HashMap<Character, Set<Character>> outbounds, HashMap<Character, Integer> inbounds) {

        // init outbounds and inbounds, we need to guarantee every char in inbound
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            for (int j = 0; j < len; j++) {
                char c =words[i].charAt(j);
                if (!inbounds.containsKey(c)) {
                    inbounds.put(c, 0);
                    outbounds.put(c, new HashSet<Character>());
                }
            }
        }

        // build outbounds x->y, x must be before y. We use outbounds to traverse.
        for (int i = 0; i < words.length-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int minlen = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minlen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    outbounds.get(c1).add(c2);
                    break;
                }
            }
        }

        // build inbounds. We use inbounds to find node without inbound edge
        for (Character c : outbounds.keySet()) {
            for (Character cc : outbounds.get(c)) {
                inbounds.put(cc, inbounds.get(cc)+1);
            }
        }
    }

    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> outbounds = new HashMap<Character, Set<Character>>();
        HashMap<Character, Integer> inbounds = new HashMap<>();

        buildGraph(words, outbounds, inbounds);

        Queue<Character> q = new LinkedList<Character>();
        for (Map.Entry<Character, Integer> entry: inbounds.entrySet()) {
            Character c= entry.getKey();
            Integer i = entry.getValue();
            if (i == 0) {
                q.offer(c);
            }
        }
        String res = "";
        while (!q.isEmpty()) {
            Character c = q.poll();
            res = res+c;
            for (Character cc : outbounds.get(c)) {
                inbounds.put(cc, inbounds.get(cc)-1);
                if (inbounds.get(cc) == 0)
                    q.add(cc);
            }
        }

        if (inbounds.size() != res.length())
            return "";

        return res;
    }


    public static void main(String [] args) {
        AlienDictionary s = new AlienDictionary();
        String[] words = {"ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"};
        s.alienOrder(words);
    }
}
