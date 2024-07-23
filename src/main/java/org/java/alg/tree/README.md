1、获取树的深度 org.java.alg.tree.WtreeDepth.getDepth

2、最小堆的调整，插入，删除....







const int N = 10;

int n;
int state[N];   // 0 表示还没放数，1~n表示放了哪个数
bool used[N];   // true表示用过，false表示还未用过

void dfs(int u)
{
    if (u > n)  // 边界
    {
        for (int i = 1; i <= n; i ++ ) printf("%d ", state[i]); // 打印方案
        puts("");
        return;
    }


 // 依次枚举每个分支，即当前位置可以填哪些数
    for (int i = 1; i <= n; i ++ )
        if (!used[i])
        {
            state[u] = i;
            used[i] = true;
            dfs(u + 1);
            //递归搜索下一个位置
            // 恢复现场
            state[u] = 0;  //回溯
            used[i] = false;
        }
}

int main()
{
    scanf("%d", &n);
    dfs(g);//从第一个位置开始搜索遍历
    return 0;
}
