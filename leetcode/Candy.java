package leetcode;

/**
 * Candy
 * https://leetcode-cn.com/problems/candy/
 * 135. 分发糖果
 *
 * @author tobin
 * @since 2020-12-24
 */
public class Candy {

    public static void main(String[] args) {
        Candy sol = new Candy();
        System.out.println(sol.candy(new int[]{1, 0, 2}));
        System.out.println(sol.candy(new int[]{1, 2, 2}));
    }

    public int candy(int[] ratings) {
        // 参考题解
        // 先完成左侧合法糖果数
        int[] leftSubSeq = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            leftSubSeq[i] = 1;
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                leftSubSeq[i] = leftSubSeq[i - 1] + 1;
            }
        }
        // 再完成右侧合法糖果数, 需要倒序访问
        int[] rightSubSeq = new int[ratings.length];
        for (int i = ratings.length - 1; i >= 0; i--) {
            rightSubSeq[i] = 1;
            if (i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
                rightSubSeq[i] = rightSubSeq[i + 1] + 1;
            }
        }

        // 合并较大结果
        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            if (leftSubSeq[i] > rightSubSeq[i]) {
                total += leftSubSeq[i];
            } else {
                total += rightSubSeq[i];
            }
        }
        return total;
    }

    // TLE
    public int candy_tle(int[] ratings) {
        int[][] rolling = new int[2][ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            rolling[0][i] = 1;
        }

        int curr = 0;
        int next = 1 - curr;
        boolean change = true;
        while (change) {

            change = false;
            for (int i = 0; i < ratings.length; i++) {
                int value = rolling[curr][i];

                int fromLeft = 0;
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    fromLeft = rolling[curr][i - 1] + 1;
                }
                if (fromLeft > value) {
                    change = true;
                    value = fromLeft;
                }

                int fromRight = 0;
                if (i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
                    fromRight = rolling[curr][i + 1] + 1;
                }
                if (fromRight > value) {
                    change = true;
                    value = fromRight;
                }

                rolling[next][i] = value;
            }

            curr = 1 - curr;
            next = 1 - curr;
        }

        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += rolling[curr][i];
        }
        return total;
    }
}
