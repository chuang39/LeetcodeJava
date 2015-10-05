package dfsbfs.java.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
    Leetcode: https://leetcode.com/problems/walls-and-gates/

    Two solutions: both start from the gate (rooms[i][j] == 0)
    1) DFS: code is shorter
    2) BFS: push all gates to queue and do BFS together
 */
public class WallsAndGates {

    public void wallsAndGatesDFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0)
                    dfs(rooms, i, j, 0, true);
            }
        }
    }

    public void dfs(int[][] rooms, int m , int n, int dist, boolean isFirst) {
        if (!isFirst && !isValid(rooms, m, n, dist))
            return;

        rooms[m][n] = dist;
        dfs(rooms, m+1, n, dist+1, false);
        dfs(rooms, m-1, n, dist+1, false);
        dfs(rooms, m, n+1, dist+1, false);
        dfs(rooms, m, n-1, dist+1, false);
    }

    public void wallsAndGatesBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0)
                    q.offer(encode(rooms, i, j));
            }
        }
        bfs(rooms, q);
    }

    public void bfs(int[][] rooms, Queue<Integer> q) {
        int curLevel = q.size();
        int nextLevel = 0;
        int dist = 0;

        int[] directionm = {1, -1, 0, 0};
        int[] directionn = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            while (curLevel > 0) {
                int cur = q.poll();
                curLevel--;
                int m = cur / rooms[0].length;
                int n  = cur % rooms[0].length;

                if (rooms[m][n] > dist) {
                    rooms[m][n] = dist;
                }
                for (int i = 0; i < 4; i++) {
                    int x = m + directionm[i];
                    int y = n + directionn[i];
                    if (isValid(rooms, x, y, dist+1)) {
                        q.offer(encode(rooms, x, y));
                        nextLevel++;
                    }
                }

            }
            curLevel = nextLevel;
            nextLevel = 0;
            dist++;
        }
    }

    boolean isValid(int[][] rooms, int x, int y, int dist) {
        // out of bound
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length)
            return false;

        // dist+1 < rooms[x][y]
        if (rooms[x][y] <= dist)
            return false;

        return true;
    }

    int encode(int[][] rooms, int m, int n) {
        return m * rooms[0].length + n;
    }

    public static void main(String [] args) {
        WallsAndGates s = new WallsAndGates();
        int[][] rooms = {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}
        };
        s.wallsAndGatesDFS(rooms);
    }

}
