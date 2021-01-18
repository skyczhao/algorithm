package leetcode;

import java.util.*;

/**
 * MostStonesRemoved
 * https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
 * https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/solution/er-wei-bing-cha-ji-by-oshdyr-t78h/
 * 947. 移除最多的同行或同列石头
 *
 * @since 2021-01-15
 */
public class MostStonesRemoved {

    public static void main(String[] args) {
        MostStonesRemoved sol = new MostStonesRemoved();
        System.out.println(sol.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(sol.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
        System.out.println(sol.removeStones(new int[][]{{5, 9}, {9, 0}, {0, 0}, {7, 0}, {4, 3}, {8, 5}, {5, 8}, {1, 1}, {0, 6}, {7, 5}, {1, 6}, {1, 9}, {9, 4}, {2, 8}, {1, 3}, {4, 2}, {2, 5}, {4, 1}, {0, 2}, {6, 5}}));
        System.out.println(sol.removeStones(new int[][]{{0, 0}}));
    }

    public int removeStones(int[][] stones) {
        int counts = 0;
        // 统一存储x和y的并查集, 而同一个点决定x和y能够连接
        Map<Integer, Integer> mixIndexs = new HashMap<>();
        for (int[] stone : stones) {
            int x = stone[0] + 10001; // +10001避免与y重叠
            int y = stone[1];

            int findX = -1;
            if (mixIndexs.containsKey(x)) {
                findX = mixIndexs.get(x);
                while (findX != mixIndexs.get(findX)) {
                    findX = mixIndexs.get(findX);
                }
            } else {
                // 新增, 以x为基础的
                findX = x;
                counts++;
            }
            mixIndexs.put(x, findX);

            int findY = -1;
            if (mixIndexs.containsKey(y)) {
                findY = mixIndexs.get(y);
                while (findY != mixIndexs.get(findY)) {
                    findY = mixIndexs.get(findY);
                }
            } else {
                // 新增, 以y为基础的
                findY = y;
                counts++;
            }
            mixIndexs.put(y, findY);

            if (findX != findY) {
                // 链接, x和y连接上
                mixIndexs.put(findX, findY);
                counts--;
            }
        }

        return stones.length - counts;
    }

    public int removeStones_tle(int[][] stones) {
        List<Set<Integer>[]> commonParent = new LinkedList<>();
        for (int[] stone : stones) {
            Set<Integer>[] parent = null;
            for (Set<Integer>[] sets : commonParent) {
                if (sets[0].contains(stone[0]) || sets[1].contains(stone[1])) {
                    parent = sets;

                    break;
                }
            }
            if (parent == null) {
                parent = new Set[2];
                parent[0] = new HashSet<>();
                parent[1] = new HashSet<>();
                commonParent.add(parent);
            }
            parent[0].add(stone[0]);
            parent[1].add(stone[1]);
        }

        for (int i = commonParent.size() - 1; i >= 0; i--) {
            Set<Integer>[] toCheck = commonParent.get(i);
            for (int j = 0; j < i; j++) {
                Set<Integer>[] parent = commonParent.get(j);
                boolean flag = false;
                for (Integer x : toCheck[0]) {
                    if (parent[0].contains(x)) {
                        flag = true;
                        break;
                    }
                }
                for (Integer y : toCheck[1]) {
                    if (parent[1].contains(y)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    parent[0].addAll(toCheck[0]);
                    parent[1].addAll(toCheck[1]);
                    commonParent.remove(i);
                    break;
                }
            }
        }

        return stones.length - commonParent.size();
    }

    public int removeStones_err(int[][] stones) {
        Map<Integer, Integer> rowCounts = new HashMap<>();
        int maxRow = 0;
        Map<Integer, Integer> colCounts = new HashMap<>();
        int maxCol = 0;
        for (int[] stone : stones) {
            int rowCount = 0;
            if (rowCounts.containsKey(stone[0])) {
                rowCount = rowCounts.get(stone[0]);
            }
            rowCounts.put(stone[0], rowCount + 1);
            maxRow = Math.max(maxRow, stone[0]);

            int colCount = 0;
            if (colCounts.containsKey(stone[1])) {
                colCount = colCounts.get(stone[1]);
            }
            colCounts.put(stone[1], colCount + 1);
            maxCol = Math.max(maxCol, stone[1]);
        }
        maxRow += 1;
        maxCol += 1;

        int[][] counts = new int[maxRow][maxCol];
        for (int[] stone : stones) {
            int r = stone[0];
            int c = stone[1];
            if (rowCounts.containsKey(r) &&
                    colCounts.containsKey(c)) {
                counts[r][c] = rowCounts.get(r) + colCounts.get(c) - 1;
            }
        }

        int remove = 0;
        while (true) {
            // find min
            int minRow = -1;
            int minCol = -1;
            int min = Integer.MAX_VALUE;
            for (int r = 0; r < maxRow; r++) {
                for (int c = 0; c < maxCol; c++) {
                    if (counts[r][c] > 1 &&
                            counts[r][c] < min) {
                        min = counts[r][c];
                        minRow = r;
                        minCol = c;
                    }
                }
            }

            // none and exit
            if (minRow < 0) {
                break;
            }
            remove++;

            // remove row
            for (int c = 0; c < maxCol; c++) {
                counts[minRow][c]--;
            }
            // remove col
            for (int r = 0; r < maxRow; r++) {
                counts[r][minCol]--;
            }
            counts[minRow][minCol] = 0;
        }

        return remove;
    }
}

//        int rowAviCut = 0;
//        for (int r = 0; r < maxRow+1; r++) {
//            if (rowCounts.containsKey(r)) {
//                int count = rowCounts.get(r);
//                if (count > 1) {
//                    rowAviCut += (count - 1);
//                }
//            }
//        }
//        int colAviCut = 0;
//        for (int c = 0; c < maxCol+1; c++) {
//            if (colCounts.containsKey(c)) {
//                int count = colCounts.get(c);
//                if (count > 1) {
//                    colAviCut += (count - 1);
//                }
//            }
//        }