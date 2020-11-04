package leetcode;

/**
 * ValidMountainArray
 * https://leetcode-cn.com/problems/valid-mountain-array/
 *
 * @since 2020-11-03
 */
public class ValidMountainArray {
    public static void main(String[] args) {

        ValidMountainArray sol = new ValidMountainArray();
        System.out.println(sol.validMountainArray(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(sol.validMountainArray(new int[]{1, 2, 3, 3, 6, 5}));
        System.out.println(sol.validMountainArray(new int[]{1, 2, 3, 4, 6, 5}));
        System.out.println(sol.validMountainArray(new int[]{6, 5, 4, 3, 2, 1})); // error
    }

    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }

        boolean isIncrease = true;
        int lastValue = -1;
        int idx = 0;
        for (int value : A) {
            if (isIncrease) {
                if (value < lastValue) {
                    if (idx == 1) {
                        return false;
                    }
                    isIncrease = false;
                } else if (value == lastValue) {
                    return false;
                }
            } else {
                if (value >= lastValue) {
                    return false;
                }
            }
            lastValue = value;
            idx++;
        }

        return !isIncrease;
    }
}
