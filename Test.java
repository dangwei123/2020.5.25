牛牛有一个3*n的土地。这个土地有3行，n列。牛牛想在土地上种至少一朵花。

为了花儿能够茁壮成长，每一朵花的上下左右四个方向不能有其他的花。问有多少种种花的方案。

为防止答案过大，答案对1e9+7取模。

public class Solution {
    /**
     * 牛牛爱花
     * @param n int整型 列数
     * @return int整型
     */
    public int solve (int n) {
        // write code here
        long[][] dp=new long[5][n];
        for(int i=0;i<5;i++){
            dp[i][0]=1;
        }
        int mod=1000000007;
        for(int i=1;i<n;i++){
            dp[0][i]=(dp[0][i-1]+dp[1][i-1]+dp[2][i-1]+dp[3][i-1]+dp[4][i-1])%mod;
            dp[1][i]=(dp[0][i-1]+dp[2][i-1]+dp[3][i-1])%mod;
            dp[2][i]=(dp[0][i-1]+dp[1][i-1]+dp[3][i-1]+dp[4][i-1])%mod;
            dp[3][i]=(dp[0][i-1]+dp[1][i-1]+dp[2][i-1])%mod;
            dp[4][i]=(dp[0][i-1]+dp[2][i-1])%mod;
        }
        return (int)((dp[0][n-1]+dp[1][n-1]+dp[2][n-1]+dp[3][n-1]+dp[4][n-1]-1)%mod);
    }
}



红、黄、蓝三种颜色的气球。

在牛客王国，1个红气球+1个黄气球+1个蓝气球可以兑换一张彩票。

2个红气球+1个黄气球可以兑换1个蓝气球。

2个黄气球+1个蓝气球可以兑换1个红气球。

2个蓝气球+1个红气球可以兑换1个黄气球。

现在牛牛有a个红气球，b个黄气球， c个蓝气球，牛牛想知道自己最多可以兑换多少张彩票。


public class Solution {
    /**
     * 三色球
     * @param a int整型 
     * @param b int整型 
     * @param c int整型 
     * @return int整型
     */
    public int solve (int a, int b, int c) {
        // write code here
        int res=Math.min(a,Math.min(b,c));
        a-=res;
        b-=res;
        c-=res;
        if(a==0&&b>=3&&c>=2) return res+Math.min(b/3,c/2);
        if(b==0&&c>=3&&a>=2) return res+Math.min(c/3,a/2);
        if(c==0&&a>=3&&b>=2) return res+Math.min(a/3,b/2);
        return res;
    }
}


牛牛准备在一个3行n列的跑道上跑步。一开始牛牛位于(1,1)。

当牛牛位于第i行第j列时，他可以的下一步最多可能有三种选择：

1. 跑到第i行第j+1列
2. 跑到第i-1行第j+1列（如果i=1则不可以这么跑）。
3. 跑到第i+1行第j+1列（如果i=3则不可以这么跑）。

跑道上有一些格子设置了路障(一个格子可能有多个路障)，牛牛不能跑到路障上。现在牛牛想知道，从(1,1)到(3,n)有多少条不同的路径？

为了防止答案过大，答案对1e9+7取模。


public class Solution {
    /**
     * 简单变相
     * @param n int整型 
     * @param m int整型 
     * @param x int整型一维数组 
     * @param y int整型一维数组 
     * @return int整型
     */
    public int solve (int n, int m, int[] x, int[] y) {
        // write code here
        int mod=1000000007;
        long[][] dp=new long[4][n+1];
        for(int i=0;i<m;i++){
            dp[x[i]][y[i]]=-1;
        }
        dp[1][2]=dp[1][2]==-1?-1:1;
        dp[2][2]=dp[2][2]==-1?-1:1;
        for(int i=3;i<=n;i++){
            long up=dp[1][i-1]==-1?0:dp[1][i-1];
            long mid=dp[2][i-1]==-1?0:dp[2][i-1];
            long down=dp[3][i-1]==-1?0:dp[3][i-1];
            if(dp[1][i]!=-1) dp[1][i]=(up+mid)%mod;
            if(dp[2][i]!=-1) dp[2][i]=(up+mid+down)%mod;
            if(dp[3][i]!=-1) dp[3][i]=(mid+down)%mod;
        }
        return (int)dp[3][n];
    }
}



牛牛准备在一个3行n列的跑道上跑步。一开始牛牛可以自己选择位于(1,1)还是(2,1)还是(3,1)。

跑道的每一格都有一些金币，当牛牛跑到一个格子，他会获得这个格子的所有金币。

当牛牛位于第i行第j列时，他可以的下一步最多可能有三种选择：

1. 不花费金币跑到第i行第j+1列
2. 花费m_jm 
j
​	
 的金币跑到第i-1行第j+1列（如果i=1则不可以这么跑）。
3. 花费m_jm 
j
​	
 的金币跑到第i+1行第j+1列（如果i=3则不可以这么跑）。
（牛牛是一个富豪，本身带了很多的金币，所以你不用担心他钱不够用）
现在告诉你所有格子的金币数量和每一列的金币花费，牛牛想知道他跑到第n列最多可以赚得多少金币（赚得金币=获得金币-消耗金币）

public class Solution {
    /**
     * 变相
     * @param n int整型 
     * @param a1 int整型一维数组 
     * @param a2 int整型一维数组 
     * @param a3 int整型一维数组 
     * @param m int整型一维数组 
     * @return int整型
     */
    public int solve (int n, int[] a1, int[] a2, int[] a3, int[] m) {
        // write code here
        int[][] dp=new int[4][n+1];
        for(int i=1;i<=n;i++){
            dp[1][i]=Math.max(dp[1][i-1]+a1[i-1],dp[2][i-1]-m[i-1]+a2[i-1]);
            dp[2][i]=Math.max(dp[2][i-1]+a2[i-1],
                     Math.max(dp[1][i-1]+a1[i-1],dp[3][i-1]+a3[i-1])-m[i-1]);
            dp[3][i]=Math.max(dp[3][i-1]+a3[i-1],dp[2][i-1]-m[i-1]+a2[i-1]);
        }
        return Math.max(dp[1][n],Math.max(dp[2][n],dp[3][n]));
    }
}



给出一个字符串S，牛牛想知道这个字符串有多少个子序列等于"niuniu"
子序列可以通过在原串上删除任意个字符(包括0个字符和全部字符)得到。
为了防止答案过大，答案对1e9+7取模

public class Solution {
    /**
     * 好多牛牛
     * @param s string字符串 
     * @return int整型
     */
    public int solve (String s) {
        // write code here
        int[] dp=new int[6];
        int mod=1000000007;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='n'){
                dp[0]=(dp[0]+1)%mod;
                dp[3]=(dp[2]+dp[3])%mod;
            }else if(c=='i'){
                dp[1]=(dp[1]+dp[0])%mod;
                dp[4]=(dp[4]+dp[3])%mod;
            }else if(c=='u'){
                dp[2]=(dp[2]+dp[1])%mod;
                dp[5]=(dp[5]+dp[4])%mod;
            }
        }
        return dp[5];
    }
}


