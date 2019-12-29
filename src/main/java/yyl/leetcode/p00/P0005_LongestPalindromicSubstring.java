package yyl.leetcode.p00;

/**
 * <h3>最长回文子串</h3><br>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。<br>
 * 示例 1：<br>
 * 输入: "babad" 输出: "bab"<br>
 * 注意: "aba" 也是一个有效答案。<br>
 * 示例 2：<br>
 * 输入: "cbbd" 输出: "bb"<br>
 * <br>
 * Given a string s, find the longest palindromic substring in s. <br>
 * You may assume that the maximum length of s is 1000. * <br>
 * Example:<br>
 * Input: "babad"<br>
 * Output: "bab"<br>
 * Note: "aba" is also a valid answer.<br>
 * Example:<br>
 * Input: "cbbd"<br>
 * Output: "bb" <br>
 */
// 给出一个字符串S，找到一个最长的连续回文串。
public class P0005_LongestPalindromicSubstring {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("babad"));// bab || aba
        System.out.println(solution.longestPalindrome("cbbd"));// bb
        System.out.println(solution.longestPalindrome(
                "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"//
        ));// ranynar
        System.out.println(solution.longestPalindrome(null));// null
        System.out.println(solution.longestPalindrome(""));// ""
        System.out.println(solution.longestPalindrome("a"));// a
    }

    static class Solution {

        // 时间复杂度：O(n^2)，两层循环
        // 空间复杂度：O(n^2)，用了一个DP二维数组
        public String longestPalindrome(String s) {

            // 空字符串，只有一个字符的字符串，只有唯一的子串就是自身
            if (s == null || s.length() < 2) {
                return s;
            }

            int max = 0;
            int left = 0;
            int right = 0;

            // 动态规划 dp[i][j] 表示子串 [i,j]是否是回文
            boolean[][] dp = new boolean[s.length()][s.length()];

            // 结束位置指针后移
            for (int j = 0; j < s.length(); j++) {
                // 子串只有一个字符，是回文子串
                dp[j][j] = true;
                // 子串开始位置的指针后移
                for (int i = 0; i < j; i++) {
                    // 子串[i,j]是回文的充分必要条件是
                    // 开头和结尾字符一定相等 (s.charAt(i) == s.charAt(j))
                    // 并且如果子串[i+1,j-1]存在，那么也必然是回文
                    if ((s.charAt(i) == s.charAt(j)) && (j - 1 < i + 1 || dp[i + 1][j - 1])) {
                        // 记录子串[i,j]为回文
                        dp[i][j] = true;
                        // 如果当前子串长度比之前保存的回文子串长，那么记录当前子串为最长子串
                        if (j - i + 1 > max) {
                            max = j - i + 1;
                            left = i;
                            right = j;
                        }
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }
}
