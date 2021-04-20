package leetcode;

/**
 * ImplementStrstr
 * https://leetcode-cn.com/problems/implement-strstr/
 * 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/solution/kmpxue-xi-zhang-wo-by-oshdyr-0m85/
 *
 * @author tobin
 * @since 2021-04-20
 */
public class ImplementStrstr {
    public static void main(String[] args) {
        ImplementStrstr sol = new ImplementStrstr();
        System.out.println(sol.strStr("hello", "ll"));
        System.out.println(sol.strStr("aaaaaa", "bba"));
        System.out.println(sol.strStr("", ""));
        System.out.println(sol.strStr("aaaaacd", "aac")); // 看它的前一个字符的前缀表的数值是多少
        System.out.println(sol.strStr("aa", "aa"));
        System.out.println(sol.strStr("bbbbababbbaabbba", "abb"));
        System.out.println(sol.strStr("adcadcaddcadde", "adcadde"));
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }

        int[] next = new int[needle.length()]; // next数组, KEY 1
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < needle.length(); i++) {
            while (j >= 0) {
                if (needle.charAt(j) == needle.charAt(i)) {
                    break;
                }
                if (j == 0) {
                    j = j - 1;
                    break;
                }
                j = next[j - 1]; // 同样是根据前缀来找, KEY 4
            }

            j++;
            next[i] = j; // j + 1后再赋值, KEY 2
        }

        int moveJ = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (moveJ >= 0) {
                if (haystack.charAt(i) == needle.charAt(moveJ)) {
                    moveJ++;
                    break;
                }
                if (moveJ == 0) {
                    break;
                }
                // 看它的前一个字符的前缀表的数值是多少, KEY 3
                moveJ = next[moveJ - 1];
            }
            if (moveJ == needle.length()) {
                return i + 1 - moveJ;
            }
        }
        return -1;
    }
}
