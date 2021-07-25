package leetcode;

import java.util.*;

/**
 * RestoreTheArrayFromAdjacentPairs
 * https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/
 * 1743. 从相邻元素对还原数组
 * https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/solution/que-ding-bian-jie-jie-fa-zi-ran-chu-by-o-9cr6/
 *
 * @author tobin
 * @since 2021-07-25
 */
public class RestoreTheArrayFromAdjacentPairs {
    public static void main(String[] args) {
        RestoreTheArrayFromAdjacentPairs sol = new RestoreTheArrayFromAdjacentPairs();

//        int[][] input = {{2, 1}, {3, 4}, {3, 2}};
//        int[][] input = {{4, -2}, {1, 4}, {-3, 1}};
        int[][] input = {{100000, -100000}};
        int[] res = sol.restoreArray(input);
        for (int value : res) {
            System.out.println(value);
        }
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, Integer> map_one = new HashMap<>();
        Map<Integer, Integer> map_two = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            if (map_one.containsKey(pair[0])) {
                map_two.put(pair[0], pair[1]);
                counts.put(pair[0], 2);
            } else {
                map_one.put(pair[0], pair[1]);
                counts.put(pair[0], 1);
            }

            if (map_one.containsKey(pair[1])) {
                map_two.put(pair[1], pair[0]);
                counts.put(pair[1], 2);
            } else {
                map_one.put(pair[1], pair[0]);
                counts.put(pair[1], 1);
            }
        }

        // find edge
        List<Integer> edges = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                edges.add(entry.getKey());
            }
        }
        if (edges.size() != 2) {
            return null;
        }

        // build result
        int size = map_one.size();
        int[] result = new int[size];
        int next = edges.get(0);
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < size; i++) {
            result[i] = next;
            used.add(next);

            int tmp = map_one.get(next);
            if (next == edges.get(1)) {
                break;
            } else if (used.contains(tmp)) {
                tmp = map_two.get(next);
            }
            next = tmp;
        }

        return result;
    }
}
