package leetcode;

/**
 * StringRotationLcci
 * https://leetcode.cn/problems/string-rotation-lcci/
 * 面试题 01.09. 字符串轮转
 * https://leetcode.cn/problems/string-rotation-lcci/solution/by-oshdyr-wdqx/
 *
 * @author Tobin
 * @since 2022-09-29
 */
public class StringRotationLcci {

    public static void main(String[] args) {
        StringRotationLcci sol = new StringRotationLcci();
        System.out.println(sol.isFlipedString("waterbottle", "erbottlewat"));
        System.out.println(sol.isFlipedString("aa", "aba"));
    }


    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        String tmp = s1;
        for (int i = 0; i < s1.length(); i++) {
            if (tmp.equals(s2)) {
                return true;
            }
            char start = tmp.charAt(0);
            tmp = tmp.substring(1) + start;
        }
        return false;
    }
}
