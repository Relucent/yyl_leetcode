package yyl.leetcode.p00;

/**
 * <h3>编辑距离</h3><br>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。<br>
 * 你可以对一个单词进行如下三种操作：<br>
 * 1. 插入一个字符<br>
 * 2. 删除一个字符<br>
 * 3. 替换一个字符<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释: 
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')

 * 示例 2:
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释: 
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * </pre>
 */
public class P0072_EditDistance {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("intention", "execution"));
    }

    // 动态规划 +空间优化(滚动数组)
    // 根据状态转移方程，可以发现，每个格子只和上一层和左边格子有关，所以二维数组可以压缩成一维数组
    // 时间复杂度：O(m*n)，m和n为两个字符串分别的长度
    // 空间复杂度：O(m)
    static class Solution {
        public int minDistance(String word1, String word2) {
            if (word1 == null && word2 == null) {
                return 0;
            }
            if (word1 == null || word1.isEmpty()) {
                return word2.length();
            }
            if (word2 == null || word2.isEmpty()) {
                return word1.length();
            }
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();
            int len1 = chars1.length;
            int len2 = chars2.length;
            int[] dp = new int[len1 + 1];
            for (int i = 1; i <= len1; i++) {
                dp[i] = i;
            }
            for (int j = 1; j <= len2; j++) {
                int topL = dp[0];
                dp[0] = j;
                for (int i = 1; i <= len1; i++) {
                    int temp = dp[i];
                    dp[i] = Math.min(Math.min(dp[i - 1] + 1, dp[i] + 1), topL + (chars1[i - 1] != chars2[j - 1] ? 1 : 0));
                    topL = temp;
                }
            }
            return dp[len1];
        }
    }

    // 动态规划
    // 算法思路：可以把问题看作用最少步骤将 str1[1…i] 转化为 str2[1…j]。
    // 计算 str1[1…i]转化为 str2[1…j]的最少步骤：
    // 1、设 str1[1…i-1] 转化为 str2[1…j] 的最少操作数为k。
    // 2、设 str1[1…i] 转化为 str2[1…j-1] 的最少操作数为 m。
    // 3、设 str1[1…i-1] 转化为 str2[1…j-1] 的最少操作数为n。
    // 那么：
    // 1. 如果从 str1[1…i-1]~str2[1…j]到str1[1…i]~str2[1…j]，需执行一步删除str1[i]的操作，所以操作数为 k+1
    // 2. 如果从 str1[1…i]~str2[1…j-1]到str1[1…i]~str2[1…j]，需执行一步str1后插入str2[j]的操作，所以操作数为 m+1
    // 3. 如果从 str1[1…i-1]~str2[1…j-1]到str1[1…i]~str2[1…j]
    // 3.1 若str1[i]==str2[j]，两个字符串最后一个字符相等，则不需要进行操作，总最少操作数为 n
    // 3.2 若str1[i]!=str2[j]，两个字符串最后一个字符不等，需要用str2[j]替换str1[i]，总最少操作数为 n+1
    // 取 k+1，m+1，(n或n+1) 中最小值即为str1[1…i]~str2[1…j]的的最少操作数(最小编辑距离)。
    // 得到状态转移方程
    // pd[i][j] = min(pd[i-1][j]+1, pd[i][j-1]+1,(pd[i-1][j-1]+(str1[i]!=str2[j]?1:0))
    // 以word1 = "horse", word2 = "ros"为例：
    // |*|#|h|o|r|s|e|
    // |#|0|1|2|3|4|5|
    // |r|1|1|2|2|3|4|
    // |o|2|2|1|2|3|4|
    // |s|3|3|2|3|2|3|
    // 最后得到最小编辑距离为3
    // 时间复杂度：O(m*n)，m和n为两个字符串分别的长度
    // 空间复杂度：O(m*n)，循环的每一步都要记录结果
    static class Solution2 {
        public int minDistance(String word1, String word2) {
            if (word1 == null && word2 == null) {
                return 0;
            }
            if (word1 == null || word1.isEmpty()) {
                return word2.length();
            }
            if (word2 == null || word2.isEmpty()) {
                return word1.length();
            }
            int len1 = word1.length();
            int len2 = word2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    dp[i][j] = Math.min(//
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), //
                            dp[i - 1][j - 1] + (word1.charAt(i - 1) != word2.charAt(j - 1) ? 1 : 0)//
                    );
                }
            }
            return dp[len1][len2];
        }
    }
}
