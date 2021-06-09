package leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * ProfitableSchemes
 * https://leetcode-cn.com/problems/profitable-schemes/
 * 879. 盈利计划
 * https://leetcode-cn.com/problems/profitable-schemes/solution/shuo-dong-tai-gui-hua-you-dian-rao-dan-s-e48h/
 *
 * @author tobin
 * @since 2021-06-09
 */
public class ProfitableSchemes {

    public static void main(String[] args) {
        ProfitableSchemes sol = new ProfitableSchemes();

        System.out.println(sol.profitableSchemes(5, 3,
                new int[]{2, 2}, new int[]{2, 3}));
        System.out.println(sol.profitableSchemes(10, 5,
                new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }


    private static int THRESHOLD = 1000000007;
    private static int EDGE = 20000; // 为什么是20000, 因为profit累加起来可能达到10000

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int size = group.length;
        Map<Integer, Integer> peopleProfitCounts = new HashMap<>();
        peopleProfitCounts.put(0, 1);
        for (int i = 0; i < size; i++) {
            int currPeople = group[i];
            int currProfit = profit[i];

            Map<Integer, Integer> newValue = new HashMap<>();
            // loop
            for (Integer lastIdx : peopleProfitCounts.keySet()) {
                int lastPeople = lastIdx / EDGE;
                int lastProfit = lastIdx % EDGE;
                int lastCount = peopleProfitCounts.get(lastIdx);

                int newPeople = lastPeople + currPeople;
                int newProfit = lastProfit + currProfit;
                int newIdx = newPeople * EDGE + newProfit;

                if (newPeople > n) {
                    continue;
                }

                if (peopleProfitCounts.containsKey(newIdx)) {
                    int count = peopleProfitCounts.get(newIdx);
                    int t = (count + lastCount) % THRESHOLD;
                    newValue.put(newIdx, t);
                } else {
                    newValue.put(newIdx, lastCount);
                }
            }
            for (Map.Entry<Integer, Integer> entry : newValue.entrySet()) {
                peopleProfitCounts.put(entry.getKey(), entry.getValue());
            }
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : peopleProfitCounts.entrySet()) {
            int key = entry.getKey();
            int judge = key % EDGE;
            if (judge >= minProfit) {
                result += entry.getValue();
                result %= THRESHOLD;
            }
        }

        return result;
    }
}
