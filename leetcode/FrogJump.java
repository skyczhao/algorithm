package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * FrogJump
 * https://leetcode-cn.com/problems/frog-jump/
 * 403. 青蛙过河
 * https://leetcode-cn.com/problems/frog-jump/solution/ji-yi-hua-sou-suo-by-oshdyr-uzom/
 *
 * @author tobin
 * @since 2021-04-29
 */
public class FrogJump {
    public static void main(String[] args) {
        FrogJump sol = new FrogJump();
        System.out.println(sol.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(sol.canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    public boolean canCross(int[] stones) {
        int[] gaps = new int[]{-1, 0, 1};

        Map<Integer, Set<Integer>> value2lastSteps = new HashMap<>();
        Set<Integer> firstLastSteps = new HashSet<>();
        firstLastSteps.add(0);
        value2lastSteps.put(0, firstLastSteps);

        Set<Integer> allValues = new HashSet<>();
        for (int stone : stones) {
            allValues.add(stone);
        }

        for (int idx = 0; idx < stones.length; idx++) {
            int currValue = stones[idx];
            Set<Integer> lastSteps = value2lastSteps.get(currValue);
            if (lastSteps == null || lastSteps.isEmpty()) {
                continue;
            }

            for (Integer lastStep : lastSteps) {
                int waitValue = currValue + lastStep;
                for (int gap : gaps) {
                    int nextValue = waitValue + gap;
                    if (nextValue > currValue && allValues.contains(nextValue)) {
                        Set<Integer> nextLastValue = value2lastSteps.get(nextValue);
                        if (nextLastValue == null) {
                            nextLastValue = new HashSet<>();
                        }
                        nextLastValue.add(lastStep + gap);
                        value2lastSteps.put(nextValue, nextLastValue);
                    }
                }
            }
            value2lastSteps.remove(currValue);

            Set<Integer> lastLastSteps = value2lastSteps.get(stones[stones.length - 1]);
            if (lastLastSteps != null && lastLastSteps.size() > 0) {
                return true;
            }
        }
        return false;
    }
}
