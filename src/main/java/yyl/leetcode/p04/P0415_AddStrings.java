package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>字符串相加</h3><br>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。<br>
 * 注意：<br>
 * num1 和num2 的长度都小于 5100.<br>
 * num1 和num2 都只包含数字 0-9.<br>
 * num1 和num2 都不包含任何前导零。<br>
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。<br>
 */
public class P0415_AddStrings {

	public static void main(String[] args) {
		Solution solution = new Solution();
		Assert.assertEquals("19134", solution.addStrings("12345", "6789"));
	}

	// 模拟法
	// 对两个大整数模拟「竖式加法」的过程，相同数位对齐，从低到高逐位相加，如果当前位和超过 10，则向高位进一位。
	// 时间复杂度：O(max⁡(len1,len2))，其中 len1=num1.length，len2=num2.length。竖式加法的次数取决于较大数的位数。
	// 空间复杂度：O(n)，使用数组存储结果。
	static class Solution {
		public String addStrings(String num1, String num2) {
			int add = 0;
			StringBuilder answer = new StringBuilder();
			for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || add != 0; i--, j--) {
				int x = (i >= 0 ? num1.charAt(i) - '0' : 0);
				int y = (j >= 0 ? num2.charAt(j) - '0' : 0);
				int sum = x + y + add;
				answer.append(sum % 10);
				add = sum / 10;
			}
			return answer.reverse().toString();
		}
	}
}
