package org.java.alg.dfs;

/**
 * @description: TreeNode
 * @author: WeiQ.chen
 * @date: 2022/8/5
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
