package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * LastStoneWeight
 * 1046. 最后一块石头的重量: https://leetcode-cn.com/problems/last-stone-weight/
 * 题解: https://leetcode-cn.com/problems/last-stone-weight/solution/jian-dan-mo-ni-ji-ke-jie-ti-by-oshdyr-9jeo/
 *
 * @since 2020-12-30
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        LastStoneWeight sol = new LastStoneWeight();
        System.out.println(sol.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(sol.lastStoneWeight(new int[]{3, 7, 8}));
    }

    public int lastStoneWeight(int[] stones) {
        List<Integer> values = new LinkedList<>();
        for (int stone : stones) {
            values.add(stone);
        }
        Collections.sort(values, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        while (values.size() > 1) {
            int first = values.remove(0);
            int second = values.remove(0);

            int left = Math.abs(first - second);
            if (left > 0) {
                boolean isInsert = false;
                for (int i = 0; i < values.size(); i++) {
                    if (left > values.get(i)) {
                        isInsert = true;
                        values.add(i, left);
                        break;
                    }
                }

                if (!isInsert) {
                    values.add(left);
                }
            }
        }

        if (values.size() == 0) {
            return 0;
        }
        return values.get(0);
    }
}
