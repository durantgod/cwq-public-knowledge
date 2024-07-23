package org.java.alg.tree;

import lombok.Data;

/**
 * @description: Wtree
 * @author: WeiQ.chen
 * @date: 2022/6/30
 */
@Data
public class Wtree {
    /**
     * 右树
     */
    Wtree rightNode;

    /**
     * 左树
     */
    Wtree leftNode;

    /**
     * 树名
     */
    String name;

    /**
     * 节点权重
     */
    int num;

    public Wtree(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public Wtree(int num) {
        this.num = num;
    }

    public Wtree() {
    }

    public static Wtree getTree() {
        Wtree root = new Wtree("root", 10);

        Wtree r0 = new Wtree("r0", 8);
        Wtree r1 = new Wtree("r1", 5);
        r1.setLeftNode(new Wtree());
        Wtree l1 = new Wtree("l1", 3);
        r0.setRightNode(r1);
        r0.setLeftNode(l1);
        root.setRightNode(r0);

        Wtree l0 = new Wtree("l0", 12);
        root.setLeftNode(l0);

        return root;
    }

    public static void main(String[] args) {
        int[] num = {9, 3,7, 6,5, 1,10, 2};
        Wtree tree = buildTree(num, 0);

        // 调整
        adjustTree(tree);
    }

    /**
     * 构建一颗完全二叉树，初始数组为：9,3,7,6,5,1,10,2
     */
    public static Wtree buildTree(int[] num, int index) {
        if (index >= num.length) {
            return null;
        }
        Wtree node = new Wtree(num[index]);
        node.leftNode = buildTree(num, 2 * index + 1);
        node.rightNode = buildTree(num, 2 * index + 2);
        return node;
    }


    /**
     * 调整为最小堆
     * @param tree
     */
    public static void adjustTree(Wtree tree) {

    }

}

