package yyl.leetcode.p00;

/**
 * <h3>解码方法</h3><br>
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：<br>
 * 'A' -> 1<br>
 * 'B' -> 2<br>
 * ...<br>
 * 'Z' -> 26<br>
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * </pre>
 */
public class P0091_DecodeWays {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("1"));// 1
        System.out.println(solution.numDecodings("10"));// 1
        System.out.println(solution.numDecodings("12"));// 2
        System.out.println(solution.numDecodings("226"));// 3
        System.out.println(solution.numDecodings("100"));// 0
        System.out.println(solution.numDecodings("301"));// 0
        System.out.println(solution.numDecodings("2311352081218212632013108210842041412"));// 1200
    }

    // 动态规划+空间压缩
    // 因为根据状态转移方程可以看出，dp[i]其实只和dp[i-1]，dp[i-2]有关，所以可以对dp数组进行压缩(只保留2个数)
    // 时间复杂度：O(N)，N为编码长度
    // 空间复杂度：O(1)，常量辅助空间
    static class Solution {
        public int numDecodings(String s) {
            int length = s == null ? 0 : s.length();
            // 无解的情况
            if (length == 0 || s.charAt(0) == '0') {
                return 0;
            }
            // 只有一个字符，有解的情况只能有一种编码方式
            if (length == 1) {
                return 1;
            }
            int dp1 = 1;
            int dp2 = 1;
            for (int i = 1; i < length; i++) {
                char previous = s.charAt(i - 1);
                char current = s.charAt(i);
                if (current == '0' && previous != '1' && previous != '2') {
                    return 0;
                }
                int dp = 0;
                if (current != '0') {
                    dp += dp1;
                }
                if (previous == '1' || (previous == '2' && current <= '6')) {
                    dp += i == 1 ? 1 : dp2;
                }
                dp2 = dp1;
                dp1 = dp;
            }
            return dp1;
        }
    }


    // 动态规划
    // 假设字符串数字是从左到右增加，1->12->123->123...N
    // 可以看出，假设 i-1位数字的解码方式为 dp[i-1]，那么dp[i]会有两种可能的解码方式：
    // 1、最后一位单独编码,条件为S[i]!=0，这种方式下，当前字符串总解码数并没有增加，取决于i-1位数的解码数。
    // 2、和前一位组合成两位数编码，条件为S[i-1]S[i] > 26，当前字符串总解码数取决于前 i-2位数的解码数。
    // 得到状态转移方程：
    // 满足第1种解码方式，则 dp[i]=dp[i-1]
    // 满足第2种解码方式，则 dp[i]=dp[i-2]
    // 两种解码方式都能满足的，则dp[i]=dp[i-1]+dp[i-2]
    // 另外需要考虑无解的情况：如果出现数字0，和前面的数组组合大比如于26(也就是出现0，前面的字符必须是1或者2)
    // 最后， 初始状态，dp[0]=1，然后再继续操作
    // 时间复杂度：O(N)，N为编码长度
    // 空间复杂度：O(N)
    static class Solution2 {
        public int numDecodings(String s) {
            int length = s == null ? 0 : s.length();
            // 无解的情况
            if (length == 0 || s.charAt(0) == '0') {
                return 0;
            }
            // 只有一个字符，有解的情况只能有一种编码方式
            if (length == 1) {
                return 1;
            }
            int[] dp = new int[length];
            dp[0] = 1;
            for (int i = 1; i < length; i++) {
                char previous = s.charAt(i - 1);
                char current = s.charAt(i);
                // 00,30~90
                if (current == '0' && previous != '1' && previous != '2') {
                    return 0;
                }
                // 单独一组的情况 (0不能独立一组)
                if (current != '0') {
                    dp[i] += dp[i - 1];
                }
                // 和前一位组合成两位数编码的情况(11~26)
                if (previous == '1' || (previous == '2' && current <= '6')) {
                    // 如果是i==1，不能直接dp[i - 2]了，因为数组下标越界，所以需要做判断
                    // 如果S[0]S[1]符合11~26，那么dp[2]==2，否则p[2]==1 (不需要考虑异常，因为前面已经排除00,30~90无效编码了)
                    // 又因为上面 S[0]!='0'时候会加 1(dp[0]==1)，所以此处 i==1的时候加1就可以了
                    dp[i] += i == 1 ? 1 : dp[i - 2];
                }
            }
            return dp[s.length() - 1];
        }
    }

}
