package others.java.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
    /*
        Topological sort
        We generate an array whose length is number of courses.
        For each course, it records number of prerequisite(inbound edges)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null)
            throw new IllegalArgumentException("Illegal prerequisites array");

        int len = prerequisites.length;


        int[] pCounter = new int[numCourses];
        for (int i = 0; i < len; i++) {
            pCounter[prerequisites[i][0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (pCounter[i] == 0)
                queue.offer(i);

        int numNoPre = queue.size();
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int i = 0; i < len; i++) {
                if (prerequisites[i][1] == top) {
                    pCounter[prerequisites[i][0]]--;
                    if (pCounter[prerequisites[i][0]] == 0) {
                        numNoPre++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return numNoPre == numCourses;
    }

    // Course Schedule II
    // Return workable order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null)
            throw new IllegalArgumentException();

        int len = prerequisites.length;
        if (len == 0) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++)
                res[i] = i;
            return res;
        }

        int[] pCount = new int[numCourses];
        for (int i = 0; i < len; i++) {
            pCount[prerequisites[i][0]]++;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++)
            if (pCount[i] == 0)
                q.offer(i);

        int numNoPre = q.size();
        int[] res = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int top = q.poll();
            res[idx++] = top;

            for (int i = 0; i < len; i++) {
                if (prerequisites[i][1] == top) {
                    pCount[prerequisites[i][0]]--;
                    if (pCount[prerequisites[i][0]] == 0) {
                        numNoPre++;
                        q.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        if (numNoPre == numCourses)
            return res;
        else
            return new int[0];
    }
}
