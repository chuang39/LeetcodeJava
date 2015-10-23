package dp.java.leetcode;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        String minWord = word1.length() <= word2.length() ? word1 : word2;
        String maxWord = word1.length() <= word2.length() ? word2 : word1;

        int[][] A = new int[2][minWord.length()+1];
        int preRow = 0;

        for (int i = 0; i <= minWord.length(); i++) {
            A[preRow][i] = i;
        }

        for (int i = 0; i < maxWord.length(); i++) {
            int curRow = preRow ^ 1;
            A[curRow][0] = i+1;
            for (int j = 0; j < minWord.length(); j++) {
                int cost = (maxWord.charAt(i) == minWord.charAt(j)) ? 0 : 1;
                A[curRow][j+1] = Math.min(A[preRow][j]+cost, Math.min(A[preRow][j+1]+1, A[curRow][j]+1));
            }
            preRow = curRow;
        }

        return A[preRow][minWord.length()];
    }
    public static void main(String [] args) {
        EditDistance s = new EditDistance();
        s.minDistance("a", "ab");
    }
}
