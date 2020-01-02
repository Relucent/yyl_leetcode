package yyl.leetcode.p00;

/**
 * <h3>爬楼梯</h3><br>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。<br>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？<br>
 * 注意：给定 n 是一个正整数。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶

 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * </pre>
 */
public class P0070_ClimbingStairs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));// 3
    }

    // 动态规划+空间优化 (斐波那契数)
    // 通过状态转移方程：dp[i] = dp[i-1] + dp[i-2]
    // 可以看出，N阶楼梯爬楼的方法数只和前两个阶的方法数有关，所以状态转移方程只需要记录前两阶的方法数即可。
    // 斐波那契数列（Fibonacci sequence），又称黄金分割数列、因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，故又称为“兔子数列”。
    // 斐波那契数列是这样一个数列：1、1、2、3、5、8、13、21、34、……。
    // 在数学上，斐波那契数列以如下被以递推的方法定义：F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）。
    // 通过公式可以发现，这个计算N阶爬楼梯数的问题，恰巧是一个斐波那契数。
    // 时间复杂度：O(n)，单循环到 n
    // 空间复杂度：O(1)，使用常量级空间。
    static class Solution {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int previous = 1;
            int current = 2;
            int next = 0;
            for (int i = 2; i < n; i++) {
                next = current + previous;
                previous = current;
                current = next;
            }
            return current;
        }
    }

    // 动态规划
    // 第 i 阶可以由以下两种方法得到：
    // 在第 (i−1) 阶后向上爬2阶。
    // 在第 (i−2) 阶后向上爬 2阶。
    // 所以到达第 i 阶的方法总数就是到第 (i−1) 阶和第(i−2) 阶的方法数之和。
    // 令 dp[i]dp[i]dp[i] 表示能到达第 i 阶的方法总数，得到状态转移方程：
    // dp[i] = dp[i-1] + dp[i-2]
    // 时间复杂度：O(n)，单循环到 n
    // 空间复杂度：O(n)，dp数组用了n的空间
    static class Solution1 {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n - 1];
        }
    }
}
