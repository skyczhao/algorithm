package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.LinkedList;
import java.util.List;

/**
 * LeafSimilarTrees
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 * 872. 叶子相似的树
 * https://leetcode-cn.com/problems/leaf-similar-trees/solution/di-gui-bian-li-by-oshdyr-opaq/
 *
 * @author tobin
 * @since 2021-05-10
 */
public class LeafSimilarTrees {
    public static void main(String[] args) {
        TreeNode root1 = Trees.fromIntegers(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4});
        TreeNode root2 = Trees.fromIntegers(new Integer[]{3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8});

        LeafSimilarTrees sol = new LeafSimilarTrees();
        System.out.println(sol.leafSimilar(root1, root2));

    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = getLeaves(root1);
        List<Integer> leaves2 = getLeaves(root2);
        if (leaves1.size() != leaves2.size()) {
            return false;
        }
        for (int i = 0; i < leaves1.size(); i++) {
            if (leaves1.get(i) != leaves2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getLeaves(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root.left != null) {
            result.addAll(getLeaves(root.left));
        }
        if (root.right != null) {
            result.addAll(getLeaves(root.right));
        }
        if (root.left == null && root.right == null) {
            result.add(root.val);
        }
        return result;
    }
}
