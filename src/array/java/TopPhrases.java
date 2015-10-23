package array.java;


/*
# Uber phone interview question
#
# text file, n = number of phrases to return, m = max number of words in a phrase
#  text = "i love san franscisco giants baseball giants are a great team. san fransisco is a great city giants are a team I love go san fransisco giants"
#  giants = 4
#  san fransisco = 3
#  san fransisco gaints= 2
#  team = 2
#
#  n = 4 (top 4 results)
#  m = 3 (max words in a phrase)
#
#  Map<String,Integer> phraseFrequency (String text, int n, int m){
#
#  }

*/

import java.util.HashMap;
import java.util.Map;

public class TopPhrases {
    Map<String,Integer> phraseFrequency (String text, int n, int m) {
        // corner case

        Map<String, Integer> map = new HashMap<String, Integer>();

        if (text == null || text.length() == 0)
            return map;

        String[] testArr = text.split(" ");

        //for (int j = 0; j < Math.min(m, textArr.length()))


        for (int i = 0; i < testArr.length-m+1; i++) {
            String cur = "";

            // "a b c" -> c, b c, a b c,
            for (int j = m; j >=0; j--) {
                cur = (testArr[j] +" "+ cur).trim();
                if (!map.containsKey(cur)) {
                    map.put(cur, 1);
                } else {
                    map.put(cur, map.get(cur)+1);
                }
            }

        }


        return map;

    }

    public static void main(String[] args) {
        TopPhrases s = new TopPhrases();

        String text = "i love san franscisco giants baseball giants are a great team. san fransisco is a great city giants are a team I love go san fransisco giants";
        Map<String, Integer> res = s.phraseFrequency(text, 4, 3);
        for (Map.Entry<String, Integer> entry: res.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }
}
