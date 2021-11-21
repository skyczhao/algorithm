package utils;

import java.util.List;

/**
 * NTreeNode
 *
 * @author tobin
 * @since 2021-11-21
 */
public class NTreeNode {

    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {
    }

    public NTreeNode(int _val) {
        val = _val;
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
};
