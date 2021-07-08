package leetcode;

/**
 * FindMajorityElementLcci
 * https://leetcode-cn.com/problems/find-majority-element-lcci/
 * 面试题 17.10. 主要元素
 * https://leetcode-cn.com/problems/find-majority-element-lcci/solution/mei-xiang-dao-ke-yi-er-ci-bian-li-dan-hu-5fk9/
 *
 * @author tobin
 * @since 2021-07-09
 */
public class FindMajorityElementLcci {

    public static void main(String[] args) {
        FindMajorityElementLcci sol = new FindMajorityElementLcci();
        System.out.println(sol.majorityElement(new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5}));
        System.out.println(sol.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(sol.majorityElement(new int[]{3, 2}));
        System.out.println(sol.majorityElement(new int[]{1, 1, 4, 3, 2, 2, 2, 2}));
    }

    public int majorityElement(int[] nums) {
        // Boyer-Moore 投票算法

        // 一次遍历, 筛选的值不一定就是超过半数的值
        int curr = 0;
        int count = 0;
        for (int num : nums) {
            if (count < 1) {
                curr = num;
                count = 1;
            } else if (curr == num) {
                count++;
            } else {
                count--;
            }
        }

        // 重新计数, 判定是不是真的超过半数
        count = 0;
        for (int num : nums) {
            if (num == curr) {
                count++;
            }
        }

        if (count * 2 > nums.length) {
            return curr;
        }
        return -1;
    }
}
