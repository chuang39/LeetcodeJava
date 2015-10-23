package array.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for an interval.
 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();

        if (intervals == null || intervals.isEmpty())
            return res;

        Comparator<Interval> cmp = new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        };
        Collections.sort(intervals, cmp);

        int cur = 0, next = 1;
        while (cur < intervals.size()) {
            Interval curInt = intervals.get(cur);
            if (next < intervals.size() && intervals.get(next).start <= curInt.end) {
                curInt.end = Math.max(curInt.end, intervals.get(next).end);
                next++;
            } else {
                if (next > cur+1)
                    intervals.subList(cur+1, next).clear();
                cur = cur + 1;
                next = next + 1;
            }
        }
        return intervals;
    }
}
