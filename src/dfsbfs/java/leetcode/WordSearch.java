package dfsbfs.java.leetcode;

import java.util.ArrayList;

/**
 * Created by khuang on 9/19/15.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;
        if (word.length() == 0)
            return true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean res = dfs(board, i, j, word, 0);
                if (res)
                    return res;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, int x, int y, String word, int start) {
        if (start == word.length())
            return true;

        if (board[x][y] != word.charAt(start) || x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return false;

        board[x][y] = '#'; // Mark it as visited. No need visited[][]
        boolean res = dfs(board, x-1, y, word, start+1) ||
                dfs(board, x+1, y, word, start+1) ||
                dfs(board, x, y-1, word, start+1) ||
                dfs(board, x, y+1, word, start+1);
        board[x][y] = word.charAt(start);

        return res;
    }

    public static void main(String [] args) {
        WordSearch s = new WordSearch();
        char[][] board = {{'a'}};
        s.exist(board, "ab");
    }
}
