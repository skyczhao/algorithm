package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * satisfiability
 * https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 *
 * @author tobin
 * @since 2020-06-09
 */
public class satisfiability {
    public static void main(String[] args) {

        Solution s = new Solution();

        System.out.println(s.equationsPossible(new String[]{"a==b", "b!=a"}));
        System.out.println(s.equationsPossible(new String[]{"b==a", "a==b"}));
        System.out.println(s.equationsPossible(new String[]{"a==b", "b==c", "a==c"}));
        System.out.println(s.equationsPossible(new String[]{"a!=a"}));
        System.out.println(s.equationsPossible(new String[]{"c==c", "b==d", "x!=z"}));
        System.out.println(s.equationsPossible(new String[]{"c==c", "f!=a", "f==b", "b==c"}));
        System.out.println(s.equationsPossible(new String[]{"a==b", "e==c", "b==c", "a!=e"}));

    }

    static class Solution {
        public boolean equationsPossible(String[] equations) {
            Map<Character, Character> unionSet = new HashMap<>();

            // 构造并查集
            for (String equation : equations) {
                // 备选变量
                char left = equation.charAt(0);
                boolean leftContain = unionSet.containsKey(left);
                char right = equation.charAt(3);
                boolean rightContain = unionSet.containsKey(right);

                if (equation.contains("==")) {
                    // 两个变量等式
                    if (leftContain && rightContain) {
                        if (!unionSet.get(left).equals(unionSet.get(right))) {
                            Character rightValue = unionSet.get(right);
                            Character leftValue = unionSet.get(left);
                            // 属于两个集合, 但存在桥梁
                            // 也即可以两个集合合成一个, BUG 3
                            for (Map.Entry<Character, Character> entry : unionSet.entrySet()) {
                                if (entry.getValue().equals(rightValue)) { // 取key还是value, 傻傻分不清, BUG 4
                                    unionSet.put(entry.getKey(), leftValue);
                                }
                            }
                        }
                    } else if (leftContain) {
                        unionSet.put(right, unionSet.get(left));
                    } else if (rightContain) {
                        unionSet.put(left, unionSet.get(right));
                    } else {
                        unionSet.put(left, left);
                        unionSet.put(right, left);
                    }
                }
            }

            // 利用并查集判断不等于的合法性
            for (String equation : equations) {
                // 备选变量
                char left = equation.charAt(0);
                boolean leftContain = unionSet.containsKey(left);
                char right = equation.charAt(3);
                boolean rightContain = unionSet.containsKey(right);

                if (equation.contains("!=")) {
                    // 两个变量不等式
                    if (leftContain && rightContain) {
                        if (unionSet.get(left).equals(unionSet.get(right))) {
                            return false;
                        }
                    }

                    // 特殊情况: BUG 1&2
                    if (left == right) {
                        return false;
                    }
                }

            }

            return true;
        }

    }
}

