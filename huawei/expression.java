package huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 算式处理:
 * BUG1: 思路不清晰, 可用栈解决, 需要注意计算
 * BUG2: 一个栈的时候, 注意String 2 Integer, 用例: ( 2 + 3 ) * 6 ^ 2 $
 * 思考: 分两条stack会不会更好理解和处理, stack<Integer> & stack<String>
 *
 * @since 2020-05-29
 */
public class expression {

    public static Map<String, Integer> priority = new HashMap<String, Integer>() {{
        put("-", 0);
        put("+", 0);
        put("%", 1);
        put("/", 1);
        put("*", 1);
        put("^", 2);
        put("(", -1);
        put(")", -1);
    }};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String currOp;

        Stack<String> stack = new Stack<>();
        while (in.hasNext()) {
            while (true) {
                currOp = in.next();
                if (currOp.equals("$")) {
                    break;
                }

                if (!priority.containsKey(currOp)) {
                    // 非op, 即数字
                    stack.add(currOp);
                } else if ("(".equals(currOp)) {
                    stack.add(currOp);
                } else if (")".equals(currOp)) {
                    String num = stack.pop();
                    while (!"(".equals(stack.peek())) {
                        String lastOp = stack.pop();
                        String lastNum = stack.pop();
                        num = calculate(lastNum, lastOp, num);
                    }
                    stack.pop();
                    stack.add(num);
                } else {
                    String num = stack.pop();
                    while (!stack.empty()) {
                        String lastOp = stack.peek();
                        if (priority.get(lastOp) >= priority.get(currOp)) {
                            lastOp = stack.pop();
                            String lastNum = stack.pop();
                            num = calculate(lastNum, lastOp, num);
                        } else {
                            break;
                        }
                    }

                    stack.add(num);
                    stack.add(currOp);
                }

//                System.out.println(currOp);
            }

            String num = stack.pop();
            while (!stack.empty()) {
                String lastOp = stack.pop();
                String lastNum = stack.pop();
                num = calculate(lastNum, lastOp, num);
            }
            System.out.println(num);
        }

        in.close();
    }

    private static String calculate(String left, String operation, String right) {
        int a = Integer.parseInt(left); // BUG2
        int b = Integer.parseInt(right); // BUG2

        int value = 0;
        if ("^".equals(operation)) {
            value = 1;
            for (int i = 0; i < b; i++) {
                value *= a;
            }
        } else if ("*".equals(operation)) {
            value = a * b;
        } else if ("/".equals(operation)) {
            value = a / b;
        } else if ("%".equals(operation)) {
            value = a % b;
        } else if ("+".equals(operation)) {
            value = a + b;
        } else if ("-".equals(operation)) {
            value = a - b;
        } else {
            return "NaN";
        }

        return String.valueOf(value);
    }
}
