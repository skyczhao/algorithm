package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * RussianDollEnvelopes
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 * 354. 俄罗斯套娃信封问题
 * https://leetcode-cn.com/problems/russian-doll-envelopes/solution/pai-xu-hou-pan-duan-bao-han-by-oshdyr-o094/
 *
 * @since 2021-03-05
 */
public class RussianDollEnvelopes {

    public static void main(String[] args) {
//         [[5,4],[6,4],[6,7],[2,3]]

        RussianDollEnvelopes sol = new RussianDollEnvelopes();
//        System.out.println(sol.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(sol.maxEnvelopes(new int[][]{{10, 17}, {10, 19}, {16, 2}, {19, 18}, {5, 6}}));
    }

    private int checkContain(int[] container, int[] target) {
        if (container[0] > target[0] && container[1] > target[1]) {
            return 1;
        }
        if (container[0] < target[0] && container[1] < target[1]) {
            return -1;
        }
        return 0;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int size = envelopes.length;
        List<int[]> envList = Arrays.asList(envelopes);
        // 被快排坑了?
        envList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                if (a[0] > b[0] && a[1] > b[1]) {
                    return 1;
                }
                if (a[0] < b[0] && a[1] < b[1]) {
                    return -1;
                }
                return a[0] - b[0];
            }
        });

        int totalMaxCount = 0;
        int[] counts = new int[size];
        for (int i = 0; i < size; i++) {
            int maxCount = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (counts[j] + 1 > maxCount &&
                        checkContain(envList.get(i), envList.get(j)) > 0) {
                    maxCount = counts[j] + 1;
                }
            }
            counts[i] = maxCount;
            if (maxCount > totalMaxCount) {
                totalMaxCount = maxCount;
            }
        }

        return totalMaxCount;
    }
}
