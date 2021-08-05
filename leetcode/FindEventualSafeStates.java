package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * FindEventualSafeStates
 * https://leetcode-cn.com/problems/find-eventual-safe-states/
 * 802. 找到最终的安全状态
 * https://leetcode-cn.com/problems/find-eventual-safe-states/solution/3zhuang-tai-di-gui-by-oshdyr-ytai/
 * 还可以参考拓扑排序
 *
 * @author tobin
 * @since 2021-08-05
 */
public class FindEventualSafeStates {

    public static void main(String[] args) {

        int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
//        int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        FindEventualSafeStates sol = new FindEventualSafeStates();
        List<Integer> res = sol.eventualSafeNodes(graph);
        for (Integer v : res) {
            System.out.println(v);
        }

    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] isSafe = new int[graph.length];
        boolean[] isVisited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            isSafe[i] = 0;
            isVisited[i] = false;
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (dp(graph, isSafe, isVisited, i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean dp(int[][] graph,
                       int[] isSafe, boolean[] isVisited,
                       int curr) {
        if (isSafe[curr] == -1) {
            return false;
        }
        if (isSafe[curr] == 1) {
            return true;
        }
        if (isVisited[curr]) {
            isSafe[curr] = -1;
            return false;
        }

        isVisited[curr] = true;
        int[] nexts = graph[curr];
        for (int next : nexts) {
            if (!dp(graph, isSafe, isVisited, next)) {
                isVisited[curr] = false;
                isSafe[curr] = -1;
                return false;
            }
        }
        isVisited[curr] = false;
        isSafe[curr] = 1;
        return true;
    }
}
