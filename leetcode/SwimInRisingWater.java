package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * SwimInRisingWater
 * https://leetcode-cn.com/problems/swim-in-rising-water
 * 778. 水位上升的泳池中游泳
 * https://leetcode-cn.com/problems/swim-in-rising-water/solution/bing-cha-ji-nb-by-oshdyr-ztq0/
 *
 * @since 2021-01-30
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};

        SwimInRisingWater sol = new SwimInRisingWater();
        System.out.println(sol.swimInWater(grid));
    }

    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int size = rows * cols;
        int[][] directs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        List<Node> allNodes = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                allNodes.add(new Node(i, j, grid[i][j]));
            }
        }
        Collections.sort(allNodes, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.height - b.height;
            }
        });

        int[] unionSet = new int[size];
        for (int i = 0; i < size; i++) {
            unionSet[i] = i;
        }

        for (int i = 0; i < allNodes.size(); i++) {
            Node curr = allNodes.get(i);
            for (int d = 0; d < 4; d++) {
                int n_x = curr.x + directs[d][0];
                int n_y = curr.y + directs[d][1];
                if (n_x >= 0 && n_x < rows
                        && n_y >= 0 && n_y < cols
                        && curr.height >= grid[n_x][n_y]) {
                    insert(unionSet, n_x * cols + n_y, curr.x * cols + curr.y);
                }
            }

            if (parent(unionSet, 0) == parent(unionSet, size - 1)) {
                return curr.height;
            }
        }
        return allNodes.get(allNodes.size() - 1).height;
    }

    private void insert(int[] unionSet, int a, int b) {
        if (parent(unionSet, a) != parent(unionSet, b)) {
            unionSet[parent(unionSet, a)] = parent(unionSet, b);
        }
    }

    private int parent(int[] unionSet, int idx) {
        if (idx != unionSet[idx]) {
            unionSet[idx] = parent(unionSet, unionSet[idx]);
        }
        return unionSet[idx];
    }

    class Node {
        public int x;
        public int y;
        public int height;

        public Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
