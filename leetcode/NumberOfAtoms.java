package leetcode;


import java.util.*;

/**
 * NumberOfAtoms
 * https://leetcode-cn.com/problems/number-of-atoms/
 * 726. 原子的数量
 * https://leetcode-cn.com/problems/number-of-atoms/solution/zhan-de-shi-yong-jie-jue-gua-hao-pi-pei-esprf/
 *
 * @author tobin
 * @since 2021-07-05
 */
public class NumberOfAtoms {
    public static void main(String[] args) {
        NumberOfAtoms sol = new NumberOfAtoms();
        System.out.println(sol.countOfAtoms("Be32"));
        System.out.println(sol.countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(sol.countOfAtoms("Mg(OH)2"));
        System.out.println(sol.countOfAtoms("H2O"));
        System.out.println(sol.countOfAtoms("Mg(H2O)N"));
    }

    private class KeyCount {
        public String key;
        public int count;

        public KeyCount(String key, int count) {
            this.key = key;
            this.count = count;
        }
    }

    public String countOfAtoms(String formula) {

        List<KeyCount> stack = new ArrayList<>();
        // 表达式解释为数量
        StringBuilder curr = new StringBuilder();
        int currCount = 0;
        boolean waitForNumber = false;
        int number = 0;
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                // 非终止符号
                if (c >= 'a' && c <= 'z') {
                    curr.append(c);
                } else {
                    if (waitForNumber) {
                        number = number * 10 + (c - '0');
                    } else {
                        currCount = currCount * 10 + (c - '0');
                    }
                }
            } else {
                // 终止符号
                if (waitForNumber) {
                    if (number < 1) {
                        number = 1; // bug
                    }
                    for (int idx = stack.size() - 1; idx >= 0; idx--) {
                        if (stack.get(idx).count < 0) {
                            stack.remove(idx);
                            break;
                        }

                        stack.get(idx).count = stack.get(idx).count * number;
                    }

                    waitForNumber = false;
                }

                if (curr.length() > 0) {
                    if (currCount == 0) {
                        currCount = 1;
                    }
                    stack.add(new KeyCount(curr.toString(), currCount));

                    curr = new StringBuilder();
                    currCount = 0;
                }

                if (c >= 'A' && c <= 'Z') {
                    curr.append(c);
                } else if (c == '(') {
                    stack.add(new KeyCount("", -1));
                } else if (c == ')') {
                    waitForNumber = true;
                    number = 0;
                }
            }
        }

        // 表达式收尾
        if (waitForNumber) {
            if (number < 1) {
                number = 1; // bug
            }
            for (int idx = stack.size() - 1; idx >= 0; idx--) {
                if (stack.get(idx).count < 0) {
                    stack.remove(idx);
                    break;
                }

                stack.get(idx).count = stack.get(idx).count * number;
            }
        }
        if (curr.length() > 0) {
            if (currCount == 0) {
                currCount = 1;
            }
            stack.add(new KeyCount(curr.toString(), currCount));
        }

        Map<String, Integer> keyCounts = new HashMap<>();
        // 汇总统计
        for (KeyCount keyCount : stack) {
            int newCount = keyCount.count;
            if (keyCounts.containsKey(keyCount.key)) {
                newCount += keyCounts.get(keyCount.key);
            }
            keyCounts.put(keyCount.key, newCount);
        }

        // 字典序
        List<KeyCount> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : keyCounts.entrySet()) {
            resultList.add(new KeyCount(entry.getKey(), entry.getValue()));
        }
        Collections.sort(resultList, new Comparator<KeyCount>() {
            @Override
            public int compare(KeyCount a, KeyCount b) {
                return a.key.compareTo(b.key);
            }
        });

        StringBuilder result = new StringBuilder();
        for (KeyCount keyCount : resultList) {
            result.append(keyCount.key);
            if (keyCount.count > 1) {
                result.append(keyCount.count);
            }
        }
        return result.toString();
    }
}
