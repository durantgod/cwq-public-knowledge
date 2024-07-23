package org.java.alg.dfs;

import java.util.Collections;
import java.util.List;

/**
 * @description: TwoTree
 * @author: WeiQ.chen
 * @date: 2022/8/5
 *
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 *    1
 *   / \
 *  n   2
 *     / \
 *    3   n
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * */

/**
 * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，
 * 而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
 * 因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程
 *
 */
public class TwoTree {

    public List<Integer> inorderTraversal(TreeNode root) {
        // 定义一个结果集
        int[] ans = new int[102];




        return Collections.EMPTY_LIST;
    }

}
