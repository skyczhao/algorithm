package leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * AssignCookies
 * https://leetcode-cn.com/problems/assign-cookies/
 * 455. 分发饼干
 *
 * @since 2020-12-25
 */
public class AssignCookies {
    public static void main(String[] args) {
        AssignCookies sol = new AssignCookies();
        System.out.println(sol.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(sol.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
    }

    public int findContentChildren(int[] g, int[] s) {
        List<Integer> boys = new LinkedList<>();
        for (int i : g) {
            boys.add(i);
        }
        Collections.sort(boys);
        List<Integer> cookies = new LinkedList<>();
        for (int i : s) {
            cookies.add(i);
        }
        Collections.sort(cookies);

        int total = 0;
        boolean[] cookieUsed = new boolean[s.length];
        int j = 0;
        for (int i = 0; i < g.length; i++) {
            int requirement = boys.get(i);
            //for (int j = 0; j < s.length; j++) { // 不必循环,如果前面都不满足,也不用遍历了
            while (j < s.length) {
                if (!cookieUsed[j] && cookies.get(j) >= requirement) {
                    cookieUsed[j] = true;
                    total++;
                    break;
                }
                j++;
            }
        }

        return total;
    }
}
