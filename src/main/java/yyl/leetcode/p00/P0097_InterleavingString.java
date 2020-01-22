package yyl.leetcode.p00;

/**
 * <h3>交错字符串</h3><br>
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 * </pre>
 */
public class P0097_InterleavingString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));// true
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc"));// false
    }

    // 动态规划
    // 以 aabcc,dbbca,aadbbcbcac为例，二维数组如下：
    // # Ø d b b c a
    // Ø T F F F F F
    // a T F F F F F
    // a T T T T T F
    // b F T T F T F
    // c F F T T T T
    // c F F F T F T
    // 1、字符串s1和s2的长度和必须等于s3的长度，如果不等于，返回false。
    // 2、当s1和s2是空串的时候，s3必然是空串，则返回true。所以dp[0][0]=true。
    // 3、然后若s1和s2其中的一个为空串的话，另一个按位和s3对比，如果相同则是true，否则是false。
    // 这样我们可以填充DP最左和最上的值
    // 4、在任意非边缘位置dp[i][j]时，它的左边或上边有可能为true或是false，两边都可以更新过来。
    // 如果左边为true那么：当前位置的值为 s1[i-1]==s3[i+j-1]
    // 如果上面为true那么：当前位置的值为 s2[j-1]==s3[i+j-1]
    // 如果都为false，那么当前位置的值只能是 false
    // 综合一下，得出状态转移方程：dp[i][j]=(dp[i-1][j]&&s1[i-1]==s3[i-1+j])||(dp[i][j-1]&&s2[j-1]==s3[j-1+i]);
    // 时间复杂度：O(MN)，M和N是两个字符串分别的长度
    // 空间复杂度：O(MN)
    static class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            int n1 = s1.length();
            int n2 = s2.length();
            int n3 = s3.length();
            if (n1 == 0 && n2 == 0 && n3 == 0) {
                return true;
            }
            if (n1 + n2 != n3) {
                return false;
            }
            boolean[][] dp = new boolean[n1 + 1][n2 + 1];
            dp[0][0] = true;
            // 注意 dp是从0开始的，所以计算s1,s2,s3位置时候需要 i-1
            for (int i = 1; i <= n1; i++) {
                dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            }
            for (int j = 1; j <= n2; j++) {
                dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
            }
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    // s3[(i+1)+(j+1)-1]=s3[i+j-1]
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) //
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
            return dp[n1][n2];
        }
    }

    // 使用一维动态规划
    // 这种方法与前一种方法基本一致，但是利用一维数组(滚动数组)去储存前缀结果。
    // 时间复杂度：O(MN)，M和N是两个字符串分别的长度
    // 空间复杂度：O(M)
    static class Solution1 {
        public boolean isInterleave(String s1, String s2, String s3) {
            int n1 = s1.length();
            int n2 = s2.length();
            int n3 = s3.length();
            if (n1 == 0 && n2 == 0 && n3 == 0) {
                return true;
            }
            if (n1 + n2 != n3) {
                return false;
            }

            if (n1 + n2 != n3) {
                return false;
            }
            boolean dp[] = new boolean[n2 + 1];
            for (int i = 0; i <= n1; i++) {
                for (int j = 0; j <= n2; j++) {
                    if (i == 0 && j == 0) {
                        dp[j] = true;
                    } else if (i == 0) {
                        dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[n2];
        }
    }
}
