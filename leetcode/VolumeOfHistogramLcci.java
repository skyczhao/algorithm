package leetcode;

/**
 * VolumeOfHistogramLcci
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 * 面试题 17.21. 直方图的水量
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/solution/sao-miao-xian-jie-ti-xiao-lu-bu-gao-by-o-dhe0/
 *
 * @since 2021-04-02
 */
public class VolumeOfHistogramLcci {

    public static void main(String[] args) {
        VolumeOfHistogramLcci sol = new VolumeOfHistogramLcci();
        System.out.println(sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 1, 1}));
        System.out.println(sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 1, 2, 1, 0, 1}));
        System.out.println(sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 0, 2, 1, 0, 1}));
        System.out.println(sol.trap(new int[]{4, 2, 3}));
        System.out.println(sol.trap(new int[]{}));
        System.out.println(sol.trap(new int[]{0}));
    }

    public int trap(int[] height) {
        int total = 0;
        while (true) {
            int lastStart = -1;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > 0) {
                    if (lastStart >= 0) {
                        total += (i - lastStart - 1);
                    }
                    lastStart = i;
                    height[i]--;
                }
            }
            if (lastStart == -1) {
                break;
            }
        }
        return total;
    }

    public int trap_2(int[] height) {
        if (height == null || height.length < 1) { // [] fail
            return 0;
        }

        int lastTotal = -1;
        int total = 0;
        while (total == 0 || lastTotal != total) { // 4, 2, 3 fail
            lastTotal = total;
            int lastStart = -1;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > 0) {
                    if (lastStart >= 0) {
                        total += (i - lastStart - 1);
                    }
                    lastStart = i;
                    height[i]--;
                }
            }
        }
        return total;
    }

    public int trap_1(int[] height) {
        int lastTotal = -1;
        int total = 0;
        while (lastTotal != total) { // 4, 2, 3 fail
            lastTotal = total;
            int lastStart = -1;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > 0) {
                    if (lastStart >= 0) {
                        total += (i - lastStart - 1);
                    }
                    lastStart = i;
                    height[i]--;
                }
            }
        }
        return total;
    }
}
