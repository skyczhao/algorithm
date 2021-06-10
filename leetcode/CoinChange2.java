package leetcode;

/**
 * CoinChange2
 * https://leetcode-cn.com/problems/coin-change-2/
 * 518. 零钱兑换 II
 * https://leetcode-cn.com/problems/coin-change-2/solution/ji-xu-dong-tai-gui-hua-bfs-by-oshdyr-hj7q/
 *
 * @author tobin
 * @since 2021-06-10
 */
public class CoinChange2 {
    public static void main(String[] args) {
        CoinChange2 sol = new CoinChange2();
        System.out.println(sol.change(5, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        if (amount < 1) {
            return 1;
        }

        int[] amountCount = new int[amount + 1];
        amountCount[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i < amount + 1; i++) {
                int next = i + coin;
                if (next <= amount) {
                    amountCount[next] += amountCount[i];
                } else {
                    break;
                }
            }
        }
        return amountCount[amount];
    }
}
