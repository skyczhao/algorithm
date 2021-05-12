package leetcode;

import java.util.*;

/**
 * XorQueriesOfASubarray
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
 * 1310. 子数组异或查询
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray/solution/pai-xu-hou-hua-dong-chuang-kou-yi-huo-by-l7su/
 *
 * @since 2021-05-12
 */
public class XorQueriesOfASubarray {
    public static final int SIZE = 30001;

    public static void main(String[] args) {
        XorQueriesOfASubarray sol = new XorQueriesOfASubarray();

        // int[] res = sol.xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}});
        int[] res = sol.xorQueries(new int[]{4, 8, 2, 10}, new int[][]{{2, 3}, {1, 3}, {0, 0}, {0, 3}});

        for (int value : res) {
            System.out.println(value);
        }
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        List<Integer> list = new LinkedList<>();
        for (int[] query : queries) {
            int idx = query[0] * SIZE + query[1];
            list.add(idx);
        }
        Collections.sort(list);

        Map<Integer, Integer> resultMap = new HashMap<>();

        int lastX = -1;
        int lastY = -1;
        int lastValue = 0;
        for (int idx : list) {
            int x = idx / SIZE;
            int y = idx % SIZE;

            int value = lastValue;
            if (lastX == -1) {
                value = arr[x];
                for (int i = x + 1; i <= y; i++) {
                    value = value ^ arr[i];
                }
            } else {
                for (int i = lastX; i < x; i++) {
                    value = value ^ arr[i];
                }
                int minY = y < lastY ? y : lastY;
                int maxY = y + lastY - minY;
                for (int i = minY + 1; i <= maxY; i++) {
                    value = value ^ arr[i];
                }
            }

            resultMap.put(idx, value);
            lastX = x;
            lastY = y;
            lastValue = value;
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = resultMap.get(queries[i][0] * SIZE + queries[i][1]);
        }

        return result;
    }
}
