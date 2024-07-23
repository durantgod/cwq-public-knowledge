package org.java.alg.tree;

import org.java.alg.tree.Wtree;

import java.util.Date;
import java.util.Objects;

/**
 * @description: WtreeDepth
 * @author: WeiQ.chen
 * @date: 2022/9/6
 */
public class  WtreeDepth {

    public void count() {
        org.java.alg.tree.Wtree tree = org.java.alg.tree.Wtree.getTree();
        System.out.println(getDepth(tree));

        org.java.alg.tree.Wtree tree1 = new org.java.alg.tree.Wtree();
        System.out.println(getDepth(tree1));
    }

    /**
     * 获取深度
     *
     * @param tree 树
     * @return 树深度
     */
    private int getDepth(Wtree tree) {
        if (Objects.isNull(tree)) {
            return -1;
        }
        int left = getDepth(tree.getLeftNode());

        int right = getDepth(tree.getRightNode());

        return Math.max(left, right) + 1;
    }


}
