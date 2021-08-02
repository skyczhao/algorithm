package leetcode;


import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * NetworkDelayTime
 * https://leetcode-cn.com/problems/network-delay-time/
 * 743. 网络延迟时间
 * https://leetcode-cn.com/problems/network-delay-time/solution/dan-dian-chuan-bo-bfs-zhu-yi-geng-xin-de-konk/
 *
 * @author tobin
 * @since 2021-08-02
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        NetworkDelayTime sol = new NetworkDelayTime();

//        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
//        System.out.println(sol.networkDelayTime(times, 4, 2));
//        int[][] times = {{1, 2, 1}};
//        System.out.println(sol.networkDelayTime(times, 2, 1));
//        int[][] times = {{1, 2, 1}};
//        System.out.println(sol.networkDelayTime(times, 2, 2));
        int[][] times = {{1, 2, 1}, {2, 3, 2}, {1, 3, 4}};
        System.out.println(sol.networkDelayTime(times, 3, 1));

    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> matrix = new HashMap<>();
        for (int[] time : times) {
            Map<Integer, Integer> row = null;
            if (matrix.containsKey(time[0])) {
                row = matrix.get(time[0]);
            } else {
                row = new HashMap<>();
            }

            row.put(time[1], time[2]);
            matrix.put(time[0], row);
        }

        int[] delays = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            delays[i] = Integer.MAX_VALUE;
        }
        delays[k] = 0;

        Queue<Integer> next = new LinkedTransferQueue<>();
        next.add(k);

        Set<Integer> visited = new HashSet<>();
        visited.add(k);

        while (!next.isEmpty()) {
            int curr = next.poll();
            int currDelay = delays[curr];
            if (!matrix.containsKey(curr)) {
                continue;
            }
            Map<Integer, Integer> row = matrix.get(curr);
            for (Map.Entry<Integer, Integer> entry : row.entrySet()) {
                int to = entry.getKey();
                int weight = entry.getValue();
                int toDelay = currDelay + weight;

                if (toDelay < delays[to]) {
                    delays[to] = toDelay;
                    next.add(to);
                }
                visited.add(to);
            }
        }

        if (visited.size() == n) {
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (delays[i] > max) {
                    max = delays[i];
                }
            }
            return max;
        }
        return -1;
    }

}
