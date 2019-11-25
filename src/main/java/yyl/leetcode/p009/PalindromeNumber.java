package yyl.leetcode.p009;

/**
 * <h3>回文数</h3><br>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。<br>
 * 示例 1: 输入:121,输出:true <br>
 * 示例 2: 输入:-121,输出:false <br>
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。<br>
 * 示例 3: 输入:10,输出:false <br>
 * 解释: 从右向左读, 为 01。因此它不是一个回文数。<br>
 * <br>
 * Determine whether an integer is a palindrome. Do this without extra space.<br>
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] samples = {0, 1, 10, 123321, 1001, 123454321, -1001, 12345, 23456, 123210};
        for (int sample : samples) {
            System.out.println(sample + " isPalindrome = " + solution.isPalindrome(sample));
        }
    }

    static class Solution {

        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            // 取巧的解法，使用为了避免int溢出使用long
            long reverse = 0;
            int y = x;
            while (y > 0) {
                reverse = reverse * 10 + (y % 10);
                y = y / 10;
            }
            return reverse == x;
        }
    }

    static class Solution2 {
        public static boolean isPalindrome2(int x) {
            if (x < 0 || (x != 0 && x % 10 == 0)) {
                return false;
            }
            int y = 0;
            while (y < x) {
                y = y * 10 + (x % 10);
                x = x / 10;
            }
            return x == y || x == y / 10;
        }
    }
}
