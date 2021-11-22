package leetcode;

/**
 * BuddyStrings
 * https://leetcode-cn.com/problems/buddy-strings/
 * 859. 亲密字符串
 * https://leetcode-cn.com/problems/buddy-strings/solution/ji-qiao-jie-ti-by-oshdyr-wdri/
 *
 * @author tobin
 * @since 2021-11-23
 */
public class BuddyStrings {

    public static void main(String[] args) {
        BuddyStrings sol = new BuddyStrings();

        System.out.println(sol.buddyStrings("abcd", "adcb"));
        System.out.println(sol.buddyStrings("ab", "ba"));
        System.out.println(sol.buddyStrings("ab", "ab"));
        System.out.println(sol.buddyStrings("aa", "aa"));
        System.out.println(sol.buddyStrings("aaaaaaabc", "aaaaaaacb"));
        System.out.println(sol.buddyStrings("ab", "abbbb"));
    }

    public boolean buddyStrings(String s, String goal) {
        // bug 1: length different
        if (s.length() != goal.length()) {
            return false;
        }

        int[] diff_idxs = new int[2]; // 记录2个值不相同的下标
        int[] counts = new int[26]; // 字符计数

        int times = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            counts[idx]++;

            if (s.charAt(i) != goal.charAt(i)) {
                times++;
                if (times > 2) {
                    // impossible
                    return false;
                }

                diff_idxs[times - 1] = i;
            }
        }
        if (times == 1) {
            // impossible
            return false;
        }

        if (times == 0) {
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 1) {
                    // exchange same
                    return true;
                }
            }
        } else {
            if (s.charAt(diff_idxs[0]) == goal.charAt(diff_idxs[1])) {
                // exchange i & j
                return s.charAt(diff_idxs[1]) == goal.charAt(diff_idxs[0]);
            }
        }
        return false;
    }
}


