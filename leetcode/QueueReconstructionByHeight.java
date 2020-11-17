package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * QueueReconstructionByHeight
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 * 406. 根据身高重建队列
 *
 * @since 2020-11-16
 */
public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        QueueReconstructionByHeight sol = new QueueReconstructionByHeight();
        int[][] res = sol.reconstructQueue(people);
        for (int[] p : res) {
            System.out.println(p[0] + ", " + p[1]);
        }
    }


    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length < 1) {
            return people;
        }

        List<int[]> result = new LinkedList<>();
        List<int[]> origin = new ArrayList<>();
        for (int[] person : people) {
            origin.add(new int[]{person[0], person[1]});
        }
        boolean[] flags = new boolean[people.length];
        for (int idx = 0; idx < people.length; idx++) {
            flags[idx] = true;
        }

        int minIdx = 0;
        boolean isChange = false;
        while (true) {
            isChange = false;
            minIdx = 0;
            int[] minValue = origin.get(minIdx);
            for (int idx = 0; idx < origin.size(); idx++) {
                if (!flags[idx]) {
                    continue;
                }
                isChange = true;
                int[] curr = origin.get(idx);
                if (curr[1] == 0) {
                    if (!flags[minIdx]) {
                        minIdx = idx;
                        minValue = curr;
                    }
                    if (minValue[1] != 0) {
                        minIdx = idx;
                        minValue = curr;
                    } else if (curr[0] < minValue[0]) {
                        minIdx = idx;
                        minValue = curr;
                    }
                }
            }
            if (!isChange) {
                break;
            }

            flags[minIdx] = false;
            result.add(people[minIdx]);

            for (int idx = 0; idx < origin.size(); idx++) {
                if (!flags[idx]) {
                    continue;
                }
                int[] curr = origin.get(idx);
                if (minValue[0] >= curr[0]) {
                    curr[1]--;
                }
            }
        }

        int[][] resultArr = new int[result.size()][];
        for (int idx = 0; idx < result.size(); idx++) {
            resultArr[idx] = result.get(idx);
        }
        return resultArr;
    }
}
