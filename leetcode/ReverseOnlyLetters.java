package leetcode;

import java.util.Stack;

/**
 * ReverseOnlyLetters
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * 917. 仅仅反转字母
 * https://leetcode-cn.com/problems/reverse-only-letters/solution/zhan-shi-yong-by-oshdyr-g537/
 *
 * @author tobin
 * @since 2022-02-23
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                stack.add(c);
            } else if (c >= 'A' && c <= 'Z') {
                stack.add(c);
            }
        }
        StringBuilder resSB = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                resSB.append(stack.pop());
            } else if (c >= 'A' && c <= 'Z') {
                resSB.append(stack.pop());
            } else {
                resSB.append(c);
            }
        }
        return resSB.toString();
    }

    public static void main(String[] args) {
        ReverseOnlyLetters sol = new ReverseOnlyLetters();
        System.out.println(sol.reverseOnlyLetters("a-bC-dEf-ghIj"));
    }
}
