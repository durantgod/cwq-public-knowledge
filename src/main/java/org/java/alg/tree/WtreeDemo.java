package org.java.alg.tree;

/**
 * @description: WtreeDemo 实现树的深度，广度优先搜索
 * @author: WeiQ.chen
 * @date: 2022/6/30
 */
public class WtreeDemo {

    private static final int n = 3;
    private static final boolean[] state = new boolean[n + 1];

    public static void main(String[] args) {
        dfs(1);
    }

    private static void dfs(int u) {
        if (u % 4 == 0) {
            for (int i = 1; i <= n; i++) {
                if (state[i]) {
                    System.out.print(i);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!state[i]) {
                state[i] = true;
                dfs(i + 1);
                state[i] = false;
            }
        }

    }
}
