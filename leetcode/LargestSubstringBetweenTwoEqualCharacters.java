package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LargestSubstringBetweenTwoEqualCharacters
 * https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
 * 1624. 两个相同字符之间的最长子字符串
 * https://leetcode.cn/problems/largest-substring-between-two-equal-characters/solution/ji-wei-by-oshdyr-wy1e/
 *
 * @author tobin
 * @since 2022-09-17
 */
public class LargestSubstringBetweenTwoEqualCharacters {

    public static void main(String[] args) {
        LargestSubstringBetweenTwoEqualCharacters sol = new LargestSubstringBetweenTwoEqualCharacters();

        System.out.println(sol.maxLengthBetweenEqualCharacters("aa"));
        System.out.println(sol.maxLengthBetweenEqualCharacters("abca"));
        System.out.println(sol.maxLengthBetweenEqualCharacters("cbzxy"));
        System.out.println(sol.maxLengthBetweenEqualCharacters("cabbac"));
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> charFirstIdx = new HashMap<>();
        Map<Character, Integer> charLastIdx = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charFirstIdx.containsKey(c)) {
                charLastIdx.put(c, i);
            } else {
                charFirstIdx.put(c, i);
            }
        }

        int maxLength = -1;
        for (Character c : charLastIdx.keySet()) {
            int lastIdx = charLastIdx.get(c);
            int firstIdx = charFirstIdx.get(c);
            int length = lastIdx - firstIdx - 1;
            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }
}
