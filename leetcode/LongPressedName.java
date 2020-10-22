package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * LongPressedName
 * https://leetcode-cn.com/problems/long-pressed-name/
 * 
 * @since 2020-10-21
 */
public class LongPressedName {

    public static void main(String[] args) {
        LongPressedName sol = new LongPressedName();
        System.out.println(sol.isLongPressedName("alex", "aaleex"));
        System.out.println(sol.isLongPressedName("saeed", "ssaaedd"));
        System.out.println(sol.isLongPressedName("leelee", "lleeelee"));
        System.out.println(sol.isLongPressedName("laiden", "laiden"));
        System.out.println(sol.isLongPressedName("pyplrz", "ppyypllr"));
    }

    public boolean isLongPressedName(String name, String typed) {

        List<Character> nameChar = new LinkedList<>();
        List<Integer> nameCharCount = new LinkedList<>();
        // count name char
        char lastChar = 0;
        int lastCharCount = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == lastChar) {
                lastCharCount++;
            } else {
                nameChar.add(lastChar);
                nameCharCount.add(lastCharCount);

                lastChar = name.charAt(i);
                lastCharCount = 1;
            }
        }
        nameChar.add(lastChar);
        nameCharCount.add(lastCharCount);

        // compare typed char
        lastChar = 0;
        lastCharCount = 0;
        int idx = 0;
        for (int i = 0; i < typed.length(); i++) {
            if (typed.charAt(i) == lastChar) {
                lastCharCount++;
            } else {
                if (idx >= nameChar.size()) {
                    return false;
                }
                if (!nameChar.get(idx).equals(lastChar)) {
                    return false;
                }
                if (nameCharCount.get(idx) > lastCharCount) {
                    return false;
                }

                lastChar = typed.charAt(i);
                lastCharCount = 1;
                idx++;
            }
        }

        // bug1: detect shorter typed
        if (idx != nameChar.size() - 1) {
            return false;
        }
        if (!nameChar.get(idx).equals(lastChar)) {
            return false;
        }
        if (nameCharCount.get(idx) > lastCharCount) {
            return false;
        }

        return true;
    }
}
