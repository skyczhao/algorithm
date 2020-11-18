package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * MatrixCellsInDistanceOrder
 * https://leetcode-cn.com/problems/matrix-cells-in-distance-order/
 * 1030. 距离顺序排列矩阵单元格
 *
 * @since 2020-11-17
 */
public class MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        MatrixCellsInDistanceOrder sol = new MatrixCellsInDistanceOrder();
        int[][] result = sol.allCellsDistOrder(2, 3, 1, 2);
        System.out.println(result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[] directions = new int[]{-1, +1, -C, +C};
        int totalIdx = R * C;
        List<int[]> resultList = new LinkedList<>();

        Set<Integer>[] queues = new HashSet[2];
        queues[0] = new HashSet<>();
        queues[0].add(r0 * C + c0);
        queues[1] = new HashSet<>();

        int nextQueueIdx = 0;
        while (!queues[nextQueueIdx].isEmpty()) {
            Set<Integer> currQueue = queues[nextQueueIdx];
            nextQueueIdx = (nextQueueIdx + 1) % 2;
            Set<Integer> nextQueue = queues[nextQueueIdx];
            nextQueue.clear();

            // loop current level
            for (Integer idx : currQueue) {
                int r = idx / C;
                int c = idx % C;
                int oriDist = Math.abs(r - r0) + Math.abs(c - c0);
                resultList.add(new int[]{r, c});

                // loop each direction
                for (int direct : directions) {
                    int nextIdx = idx + direct;
                    if (nextIdx < 0 || nextIdx >= totalIdx) {
                        continue;
                    }

                    int nextR = nextIdx / C;
                    int nextC = nextIdx % C;
                    int nextDist = Math.abs(nextR - r0) + Math.abs(nextC - c0);
                    if (nextDist - oriDist == 1) {
                        nextQueue.add(nextIdx);
                    }
                }
            }
        }

        int[][] result = new int[resultList.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
