package others.java.leetcode.UnionFind;

public class GraphValidTree {
    // 1. ids: records root of a node
    // 2. sizes: sizes of each union. When we combine, smaller one should be appended to larger group
    int[] ids;
    int[] sizes;
    int count;  // Count is important for union find for many cases. It records how many separate unions left.

    public boolean validTree(int n, int[][] edges) {
        // For union fine, we need
        // (1) initialize union find
        // (2) implement union() and fine()
        ids = new int[n];
        sizes = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }

        for (int i = 0; i < edges.length; i++) {
            if (find(edges[i][0]) == find(edges[i][1]))
                return false;
            union(edges[i][0], edges[i][1]);
        }
        return count == 1 ? true: false;
    }

    int find(int i) {
        while (i != ids[i]) {
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return ids[i];
    }

    void union(int p, int q) {
        int rp = find(p);
        int rq = find(q);
        if (rp == rq)
            return;
        count--;
        if (sizes[rp] > sizes[rq]) {
            ids[rq] = rp;
            sizes[rp] += sizes[rq];
        } else {
            ids[rq] = rp;
            sizes[rp] += sizes[rq];
        }
    }
}
