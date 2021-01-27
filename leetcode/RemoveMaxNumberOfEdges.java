package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * RemoveMaxNumberOfEdges
 * https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 * 1579. 保证图可完全遍历
 * https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solution/bing-cha-ji-xue-xi-by-oshdyr-20c0/
 *
 * @author tobin
 * @since 2021-01-27
 */
public class RemoveMaxNumberOfEdges {
    public static void main(String[] args) {
        RemoveMaxNumberOfEdges sol = new RemoveMaxNumberOfEdges();

        System.out.println(sol.maxNumEdgesToRemove(4,
                new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}}));
        System.out.println(sol.maxNumEdgesToRemove(4,
                new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}}));
        System.out.println(sol.maxNumEdgesToRemove(4,
                new int[][]{{3, 2, 3}, {1, 1, 2}, {2, 3, 4}}));
        System.out.println(sol.maxNumEdgesToRemove(4,
                new int[][]{{3, 1, 2}, {3, 3, 4}, {1, 1, 3}, {2, 2, 4}}));
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        List<int[]> alice = new LinkedList<>();
        List<int[]> bob = new LinkedList<>();
        List<int[]> both = new LinkedList<>();
        for (int[] edge : edges) {
            switch (edge[0]) {
                case 1:
                    alice.add(edge);
                    break;
                case 2:
                    bob.add(edge);
                    break;
                default:
                    both.add(edge);
            }
        }

        int edgeUsed = 0;

        // 由共同路径可以构建的树
        int[] unionSet = new int[n];
        for (int i = 0; i < n; i++) {
            unionSet[i] = -1;
        }
        for (int[] edge : both) {
            int i = edge[1] - 1;
            int j = edge[2] - 1;
            if (unionSet[i] < 0 && unionSet[j] < 0) {
                unionSet[i] = i;
                unionSet[j] = i;
            } else if (unionSet[i] >= 0 && unionSet[j] >= 0) {
                if (parent(unionSet, i) == parent(unionSet, j)) {
                    // skip add edge
                    continue;
                }
                // 合并两个子树
                // random select one, actually should use small-subTree attach to big-subTree
                // 需要找到两个的父亲进行合并
                unionSet[parent(unionSet, j)] = parent(unionSet, i);
            } else if (unionSet[i] >= 0) {
                unionSet[j] = parent(unionSet, i);
            } else { // unionSet[j] >= 0
                unionSet[i] = parent(unionSet, j);
            }
            edgeUsed++;
        }

        // alice路径可以构建的树
        int[] aliceUnionSet = new int[n];
        for (int i = 0; i < n; i++) {
            aliceUnionSet[i] = unionSet[i];
        }
        for (int[] edge : alice) {
            int i = edge[1] - 1;
            int j = edge[2] - 1;
            if (aliceUnionSet[i] < 0 && aliceUnionSet[j] < 0) {
                aliceUnionSet[i] = i;
                aliceUnionSet[j] = i;
            } else if (aliceUnionSet[i] >= 0 && aliceUnionSet[j] >= 0) {
                if (parent(aliceUnionSet, i) == parent(aliceUnionSet, j)) {
                    // skip add edge
                    continue;
                }
                // 合并两个子树
                // random select one, actually should use small-subTree attach to big-subTree
                // 需要找到两个的父亲进行合并
                aliceUnionSet[parent(aliceUnionSet, j)] = parent(aliceUnionSet, i);
            } else if (aliceUnionSet[i] >= 0) {
                aliceUnionSet[j] = parent(aliceUnionSet, i);
            } else { // unionSet[j] >= 0
                aliceUnionSet[i] = parent(aliceUnionSet, j);
            }
            edgeUsed++;
        }

        // check alice
        int aliceParent = parent(aliceUnionSet, 0);
        for (int i = 0; i < n; i++) {
            if (aliceParent != parent(aliceUnionSet, i)) {
                return -1;
            }
        }

        // bob路径可以构建的树
        int[] bobUnionSet = new int[n];
        for (int i = 0; i < n; i++) {
            bobUnionSet[i] = unionSet[i];
        }
        for (int[] edge : bob) {
            int i = edge[1] - 1;
            int j = edge[2] - 1;
            if (bobUnionSet[i] < 0 && bobUnionSet[j] < 0) {
                bobUnionSet[i] = i;
                bobUnionSet[j] = i;
            } else if (bobUnionSet[i] >= 0 && bobUnionSet[j] >= 0) {
                if (parent(bobUnionSet, i) == parent(bobUnionSet, j)) {
                    // skip add edge
                    continue;
                }
                // 合并两个子树
                // random select one, actually should use small-subTree attach to big-subTree
                // 需要找到两个的父亲进行合并
                bobUnionSet[parent(bobUnionSet, j)] = parent(bobUnionSet, i);
            } else if (bobUnionSet[i] >= 0) {
                bobUnionSet[j] = parent(bobUnionSet, i);
            } else { // unionSet[j] >= 0
                bobUnionSet[i] = parent(bobUnionSet, j);
            }
            edgeUsed++;
        }

        // check bob
        int bobParent = parent(bobUnionSet, 0);
        for (int i = 0; i < n; i++) {
            if (bobParent != parent(bobUnionSet, i)) {
                return -1;
            }
        }

        return edges.length - edgeUsed;
    }

    private int parent(int[] unionSet, int idx) {
        int oldParent = unionSet[idx];
        if (oldParent >= 0 && oldParent != idx) {
            unionSet[idx] = parent(unionSet, oldParent);
        }
        return unionSet[idx];
    }
}
