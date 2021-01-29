package leetcode;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * PathWithMinimumEffort
 * https://leetcode-cn.com/problems/path-with-minimum-effort
 * 1631. 最小体力消耗路径
 * https://leetcode-cn.com/problems/path-with-minimum-effort/solution/dpzui-duan-lu-suan-fa-by-oshdyr-s8wg/
 *
 * @author tobin
 * @since 2021-01-29
 */
public class PathWithMinimumEffort {

    public static void main(String[] args) {
        PathWithMinimumEffort sol = new PathWithMinimumEffort();
        // int[][] input = new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}};
        int[][] input = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(sol.minimumEffortPath(input));
    }

    public int minimumEffortPath(int[][] heights) {
        Queue<Node> q = new LinkedTransferQueue<>();
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] weights = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                weights[i][j] = Integer.MAX_VALUE;
            }
        }
        weights[0][0] = 0; // E1

        int[][] directs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        q.add(new Node(0, 0, 0));
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.weight > weights[curr.x][curr.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int n_x = curr.x + directs[i][0];
                int n_y = curr.y + directs[i][1];
                if (n_x >= 0 && n_x < rows
                        && n_y >= 0 && n_y < cols) {
                    int n_weight = Math.abs(heights[curr.x][curr.y] - heights[n_x][n_y]);
                    n_weight = Math.max(n_weight, curr.weight); // E2
                    if (n_weight < weights[n_x][n_y]) {
                        weights[n_x][n_y] = n_weight;
                        q.add(new Node(n_x, n_y, n_weight));
                    }
                }
            }
        }

        return weights[rows - 1][cols - 1];
    }

    class Node {
        public int x;
        public int y;
        public int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
