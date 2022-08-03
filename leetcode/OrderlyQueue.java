package leetcode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * OrderlyQueue
 * https://leetcode.cn/problems/orderly-queue/
 * 899. 有序队列
 * https://leetcode.cn/problems/orderly-queue/solution/fu-he-by-oshdyr-0uba/
 *
 * @author Tobin
 * @since 2022-08-03
 */
public class OrderlyQueue {
    public static void main(String[] args) {
        OrderlyQueue sol = new OrderlyQueue();
        System.out.println(sol.orderlyQueue("baaca", 3));
        System.out.println(sol.orderlyQueue("cba", 1));
        System.out.println(sol.orderlyQueue("aaaaaaaaaa", 1));
        System.out.println(sol.orderlyQueue("aaaabcaa", 1));
        System.out.println(sol.orderlyQueue("abbbbbbc", 1));
        System.out.println(sol.orderlyQueue("aaaaaabc", 1));
        System.out.println(sol.orderlyQueue("bcaa", 1));
        System.out.println(sol.orderlyQueue("abacadavaz", 1));
        System.out.println("error: " + sol.orderlyQueue_error("abacadavaz", 1));
        System.out.println(sol.orderlyQueue("acabadavaz", 1));
        System.out.println("error: " + sol.orderlyQueue_error("acabadavaz", 1));

//    输入：s = "baaca", k = 3
//    输出："aaabc"
//    输入：s = "cba", k = 1
//    输出："acb"

    }

    public String orderlyQueue(String s, int k) {
        if (k < 1) {
            return s;
        }
        if (k == 1) {
            Queue<Integer> startIdxQueue = new LinkedBlockingDeque<>();
            for (int i = 0; i < s.length(); i++) {
                startIdxQueue.add(i);
            }

            // bug 1: 最小的字符, 长度相等的情况, 就要靠下一个位置进行判断
            StringBuilder sb = new StringBuilder();
            for (int move = 0; move < s.length(); move++) {
                // find min char
                char minChar = 'z';
                for (Integer startIdx : startIdxQueue) {
                    char tChar = s.charAt((startIdx + move) % s.length());
                    if (tChar < minChar) {
                        minChar = tChar;
                    }
                }

                // remove different idx
                int oldSize = startIdxQueue.size();
                for (int i = 0; i < oldSize; i++) {
                    int checkIdx = startIdxQueue.poll();
                    if (minChar == s.charAt((checkIdx + move) % s.length())) {
                        startIdxQueue.add(checkIdx);
                    }
                }

                sb.append(minChar);
            }

            return sb.toString();
        }

        // sort and return
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * error result, for reference
     *
     * @param s
     * @param k
     * @return
     */
    public String orderlyQueue_error(String s, int k) {
        if (k < 1) {
            return s;
        }
        if (k == 1) {
            // find min char
            char minChar = 'z';
            for (int i = 0; i < s.length(); i++) {
                char tChar = s.charAt(i);
                if (tChar < minChar) {
                    minChar = tChar;
                }
            }

            // search all min char field
            List<Integer> fieldStart = new ArrayList<>();
            List<Integer> fieldEnd = new ArrayList<>();
            int start = -1;
            int end = -1;
            for (int i = 0; i < s.length(); i++) {
                char tChar = s.charAt(i);
                if (tChar == minChar) {
                    if (start < 0) {
                        start = i;
                    }
                    // mark
                    end = i;
                } else {
                    if (start > -1) {
                        fieldStart.add(start);
                        fieldEnd.add(end);
                    }
                    // clear
                    start = -1;
                    end = -1;
                }
            }
            if (start > -1) {
                fieldStart.add(start);
                fieldEnd.add(end);
            }

            // merge head(0, x) and tail(y, end), to res(y, x)
            int tFieldSize = fieldStart.size();
            if (tFieldSize > 1
                    && fieldStart.get(0) == 0
                    && fieldEnd.get(tFieldSize - 1) == s.length() - 1) {
                int realStart = fieldStart.get(tFieldSize - 1) - s.length();
                fieldStart.set(0, realStart);

                fieldStart.remove(tFieldSize - 1);
                fieldEnd.remove(tFieldSize - 1);
            }

            // find max length
            // bug 1: 最小的字符, 长度相等的情况, 就要靠下一个位置进行判断
            int fieldSize = fieldStart.size();
            int maxFieldLength = 0;
            int maxFieldIdx = 0;
            for (int i = 0; i < fieldSize; i++) {
                int fieldLength = fieldEnd.get(i) - fieldStart.get(i) + 1;
                if (fieldLength > maxFieldLength) {
                    maxFieldLength = fieldLength;
                    maxFieldIdx = i;
                }
            }

            // build result
            StringBuilder sb = new StringBuilder();
            int startIdx = fieldStart.get(maxFieldIdx);
            for (int i = 0; i < s.length(); i++) {
                int realIdx = (startIdx + s.length() + i) % s.length();
                sb.append(s.charAt(realIdx));
            }

            return sb.toString();
        }

        // sort and return
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        return new String(chars);
    }
}

