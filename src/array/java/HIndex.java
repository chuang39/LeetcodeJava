package array.java;

import java.util.TreeMap;

/**
 * Created by khuang on 9/21/15.
 */
public class HIndex {
    public int hIndex(int[] citations) {
        int n = citations.length;
        // count of papers which has corresponding citations.
        // citations: 0 to n
        int[] count = new int[n+1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n)
                count[n]++;
            else
                count[citations[i]]++;
        }

        int sum = 0;
        for (int i = n; i >= 0; i--) {
            sum += count[i];
            if (sum >= i)
                return i;
        }
        return 0;
    }

    public int hIndex2(int[] citations) {
        int n = citations.length;
        int l = 0, h = n-1;
        // Find the first element >= number of element from it to end N-idx
        // e.g.
        // 0, 1, 2, 5, 6
        // num[3] = 5 > 3, so h index = N-idx=5-3=2
        while (l <= h) {
            int mid = (l + h) / 2;
            if (citations[mid] < n - mid)
                l = mid+1;
            else
                h = mid-1;
        }
        return n - l;
    }
}
