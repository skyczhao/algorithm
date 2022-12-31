package leetcode;

/**
 * FirstLetterToAppearTwice
 * https://leetcode.cn/problems/first-letter-to-appear-twice/
 * 2351. 第一个出现两次的字母
 * https://leetcode.cn/problems/first-letter-to-appear-twice/solutions/2039667/mei-ju-jian-dan-ti-by-oshdyr-l3sg/
 *
 * @author tobin
 * @since 2023-01-01
 */
public class FirstLetterToAppearTwice {
    public static void main(String[] args) {
        FirstLetterToAppearTwice sol = new FirstLetterToAppearTwice();

        System.out.println(sol.repeatedCharacter("abccbaacz"));
        System.out.println(sol.repeatedCharacter("abcdd"));
    }

    public char repeatedCharacter(String s) {
        int[] count = new int[27];
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            count[idx]++;
            if (count[idx] > 1) {
                return c;
            }
        }
        return 'a';
    }
}
