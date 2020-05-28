package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/decode-string/ 
 * 考察: 问题模拟; 借助堆栈助力理解与实现;
 * BUG: 注意括号复合的复杂情形
 * 
 */
public class decode_string {
    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.decodeString("3[a]2[bc]"));
        //System.out.println(sol.decodeString("3[a2[c]]"));
        //System.out.println(sol.decodeString("2[abc]3[cd]ef"));
        System.out.println(sol.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(sol.decodeString("ab3[ab4[c]d]"));

    }
}

class Solution {
    public String decodeString(String s) {
        Stack<String> seqs = new Stack<>();
        Stack<Integer> dups = new Stack<>();

        int times = 0;
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (curr.length() > 0) {
                    // 字符串入栈备用
                    seqs.add(curr.toString());
                    curr = new StringBuilder();
                }

                // 针对数字的计算
                times = times * 10 + (c - '0');
            } else {
                if (times > 0) {
                    // 数字入栈备用
                    dups.add(times);
                    times = 0;
                }

                if (c == '[') {
                    // 左括号入栈标记
                    seqs.add(c + "");
                } else if (c == ']') {
                    // 弹出一个左括号
                    seqs.pop();
                    // 右括号触发一次聚合
                    int subTimes = dups.pop();
                    StringBuilder subStr = new StringBuilder();
                    for (int j = 0; j < subTimes; j++) {
                        subStr.append(curr);
                    }

                    // 与数字前的字符串再聚合
                    curr = new StringBuilder();
                    if (!seqs.empty() 
                        && !seqs.peek().equals("[")) {
                        curr.append(seqs.pop());
                    }
                    curr.append(subStr);
                } else {
                    curr.append(c);
                }
            }
        }

        return curr.toString();

    }
}