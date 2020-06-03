package leetcode;

/**
 * new_21_game
 * https://leetcode-cn.com/problems/new-21-game/
 * 1. 概率(数学)
 * 2. 动态规划
 *
 * @since 2020-06-03
 */
public class new_21_game {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.new21Game(10, 1, 10));
        System.out.println(s.new21Game(6, 1, 10));
        System.out.println(s.new21Game(21, 17, 10));
        System.out.println(s.new21Game(3, 2, 5));
        System.out.println(s.new21Game(0, 0, 5));
        System.out.println(s.new21Game(1, 0, 5));
    }

    static class Solution {
        /**
         * K是控制抽牌的次数, 但不等于抽牌次数
         * N是判断输赢条件
         * 
         * @param N
         * @param K
         * @param W
         * @return
         */
        public double new21Game(int N, int K, int W) {
            if (K == 0) {
                return 1.0;
            }
            // 肯定够长的数组
            double[] solution = new double[K + W + N + 1];

            // 数组初始化, 假设牌面为K - 1时, 先计算出下一步抽到的值的胜利概率
            double rate = 0;
            for (int i = 1; i <= W; i++) {
                if (K - 1 + i <= N) {
                    solution[K - 1 + i] = 1;
                    rate += (1.0 / W);
                } else {
                    solution[K - 1 + i] = 0;
                }
            }
            solution[K - 1] = rate;

            // 动态规划
            for (int i = (K - 2); i >= 0; i--) {
                // K - 1时候的胜利概率, 由余下抽到的值决定, 在数组上即对应位置

                // 加速, 改为线性的推理
//                double rate = 0;
//                for (int j = 1; j <= W; j++) {
//                    rate += (solution[i + j] / W);
//                }
                rate = (rate * W + solution[i + 1] - solution[i + 1 + W]) / W;
                solution[i] = rate;
            }

            return solution[0];
        }
    }

    static class Solution3 {
        /**
         * 理解错题意, 以为是K步后总和小于N的概率
         * 
         * @param N
         * @param K
         * @param W
         * @return
         */
        public double new21Game(int N, int K, int W) {
            // 动态规划!

            double[] lastRate = new double[N + 1];
            double[] tmpRate = new double[N + 1];
            lastRate[0] = 0;
            tmpRate[0] = 0;
            for (int bucket = 1; bucket <= K; bucket++) {
                for (int bean = bucket; bean <= N; bean++) {
                    if (bucket == 1) {
                        lastRate[bean] = bean * 1.0 / W;
                    } else {
                        double currRate = 0;
                        for (int pick = 1; pick <= (bean - bucket + 1); pick++) {
                            currRate += (lastRate[bean - pick] * pick * 1.0 / W);
                        }
                        tmpRate[bean] = currRate;
                    }
                }

                if (bucket > 1) {
                    for (int t = 1; t <= N; t++) {
                        lastRate[t] = tmpRate[t];
                    }
                }
            }

            return lastRate[N];
        }
    }

    static class Solution2 {
        public double new21Game(int N, int K, int W) {
            if (K == 1) {
                return N * 1.0 / W;
            }

            int firstAvailable = N - K + 1;
            double totalRate = 0;
            for (int i = 1; i <= firstAvailable; i++) {
                totalRate += (new21Game(N - i, K - 1, W) * 1.0 / W);
            }

            return totalRate;
        }
    }
}
