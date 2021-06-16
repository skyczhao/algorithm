package leetcode;

/**
 * StoneGame
 * https://leetcode-cn.com/problems/stone-game/
 * 877. 石子游戏
 * https://leetcode-cn.com/problems/stone-game/solution/zi-yan-dpjie-ti-by-oshdyr-vvhm/
 *
 * @author tobin
 * @since 2021-06-16
 */
public class StoneGame {
    public static void main(String[] args) {
        StoneGame sol = new StoneGame();
        System.out.println(sol.stoneGame(new int[]{5, 3, 4, 5}));
    }

    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][][] bestChoices = new int[length][length][2];

        for (int size = 0; size < length; size++) {
            for (int idx = 0; idx + size < length; idx++) {
                int start = idx;
                int end = idx + size;
                if (size > 0) {
                    int choseStart = piles[start] + bestChoices[size - 1][start + 1][1];
                    int choseEnd = piles[end] + bestChoices[size - 1][start][1];
                    if (choseStart > choseEnd) {
                        bestChoices[size][start][0] = choseStart;
                        bestChoices[size][start][1] = bestChoices[size - 1][start + 1][0];
                    } else {
                        bestChoices[size][start][0] = choseEnd;
                        bestChoices[size][start][1] = bestChoices[size - 1][start][0];
                    }
                } else {
                    // init
                    bestChoices[size][start][0] = piles[start];
                    bestChoices[size][start][1] = 0;
                }
            }
        }
        return bestChoices[length - 1][0][0] > bestChoices[length - 1][0][1];
    }
}
