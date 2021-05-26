package leetcode;

/**
 * ReverseSubstringsBetweenEachPairOfParentheses
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 1190. 反转每对括号间的子串
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/solution/zheng-fan-han-shu-di-gui-by-oshdyr-l1cn/
 *
 * @author tobin
 * @since 2021-05-26
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

    public static void main(String[] args) {
        ReverseSubstringsBetweenEachPairOfParentheses sol = new ReverseSubstringsBetweenEachPairOfParentheses();
        System.out.println(sol.reverseParentheses("(abcd)"));
        System.out.println(sol.reverseParentheses("(u(love)i)"));
        System.out.println(sol.reverseParentheses("(ed(et(oc))el)"));
        System.out.println(sol.reverseParentheses("a(bcdefghijkl(mno)p)q"));
        System.out.println(sol.reverseParentheses("(sugqlinrwj)gasmtbk"));
    }

    public String reverseParentheses(String s) {
        StringBuilder result = new StringBuilder();
//        if (s.charAt(0) == '(') { // bug 1: what about "(sugqlinrwj)gasmtbk" ?
//            backward(s, 1, result);
//        } else {
//            forward(s, 0, result);
//        }
        forward(s, 0, result);
        return result.toString();
    }

    public int backward(String s, int idx, StringBuilder total) {
        StringBuilder invSb = new StringBuilder();
        int in = idx;
        while (in < s.length()) {
            char c = s.charAt(in);
            if (c == '(') {
                StringBuilder tmp = new StringBuilder();
                in = forward(s, in + 1, tmp);
                invSb.append(tmp.reverse());
            } else if (c == ')') {
                in++;
                break;
            } else {
                invSb.append(c);
                in++;
            }

        }
        total.append(invSb.reverse());
        return in;
    }

    public int forward(String s, int idx, StringBuilder total) {
        StringBuilder invSb = new StringBuilder();
        int in = idx;
        while (in < s.length()) {
            char c = s.charAt(in);
            if (c == '(') {
                StringBuilder tmp = new StringBuilder();
                in = backward(s, in + 1, tmp);
                invSb.append(tmp);
            } else if (c == ')') {
                in++;
                break;
            } else {
                invSb.append(c);
                in++;
            }
        }
        total.append(invSb);
        return in;
    }
}
