package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * BasicCalculator
 * https://leetcode-cn.com/problems/basic-calculator/
 * 224. 基本计算器
 * https://leetcode-cn.com/problems/basic-calculator/solution/zhan-mo-ni-ji-suan-qi-by-oshdyr-21qv/
 *
 * @since 2021-03-10
 */
public class BasicCalculator {
    public static void main(String[] args) {
        BasicCalculator sol = new BasicCalculator();
        System.out.println(sol.calculate("2 - 1+2"));
        System.out.println(sol.calculate("10 - (10 - 2)"));
        System.out.println(sol.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        Set<Character> chars = new HashSet<>();
        chars.add('(');
        chars.add(')');
        chars.add('+');
        chars.add('-');
        chars.add(' ');

        Stack<Character> operators = new Stack<>();
        Stack<Integer> values = new Stack<>();
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (chars.contains(c)) {
                if (c == '(') {
                    operators.add(c);
                } else if (c == ')') {
                    while (operators.peek() != '(') {
                        int last = values.pop();
                        char lastOp = operators.pop();
                        if (lastOp == '+') {
                            value = last + value;
                        } else {
                            value = last - value;
                        }
                    }
                    operators.pop();
                } else if (c == '+' || c == '-') {
                    // 减法不能直接从后往前计算, 需要对应更改值为负数
                    if (!operators.isEmpty() && operators.peek() == '-') {
                        value = -1 * value;
                        operators.pop();
                        operators.add('+');
                    }
                    values.add(value);
                    operators.add(c);

                    value = 0;
                }
            } else {
                value = value * 10 + (int) (c - '0');
            }
        }

        while (!operators.isEmpty()) {
            int last = values.pop();
            char lastOp = operators.pop();
            if (lastOp == '+') {
                value = last + value;
            } else {
                value = last - value;
            }
        }

        return value;
    }
}
