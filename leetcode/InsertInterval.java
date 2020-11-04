package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * InsertInterval
 * https://leetcode-cn.com/problems/insert-interval/
 *
 * @since 2020-11-04
 */
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();

        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = new int[]{4, 8};
        int[][] res = sol.insert(intervals, newInterval);
        for (int[] row : res) {
            System.out.println(row[0] + ", " + row[1]);
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> resList = new LinkedList<>();

        int tmpStart = newInterval[0];
        int tmpEnd = newInterval[1];
        boolean isDone = false;
        for (int[] interval : intervals) {
            if (isDone) {
                resList.add(interval);
                continue;
            }
            if (interval[1] < tmpStart) {
                resList.add(interval);
                continue;
            }
            if (interval[0] > tmpEnd) {
                resList.add(new int[]{tmpStart, tmpEnd});
                resList.add(interval);
                isDone = true;
                continue;
            }

            tmpStart = interval[0] < tmpStart ? interval[0] : tmpStart;
            if (interval[1] > tmpEnd) {
                tmpEnd = interval[1];
                resList.add(new int[]{tmpStart, tmpEnd});
                isDone = true;
            }
        }
        if (!isDone) {
            resList.add(new int[]{tmpStart, tmpEnd});
        }

        int[][] resArr = new int[resList.size()][];
        for (int i = 0; i < resList.size(); i++) {
            resArr[i] = resList.get(i);
        }
        return resArr;
    }
}
