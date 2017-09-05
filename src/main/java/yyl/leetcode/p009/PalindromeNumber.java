package yyl.leetcode.p009;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.<br>
 */
//判断数字是否是回文数字 
public class PalindromeNumber {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] samples = { 0, 1, 10, 123321, 1001, 123454321, -1001, 12345, 23456, 123210 };
		for (int sample : samples) {
			System.out.println(sample + " isPalindrome = " + solution.isPalindrome(sample));
		}
	}

	static class Solution {
		public boolean isPalindrome(int x) {
			if (x < 0) {
				return false;
			}
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
