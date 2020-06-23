package yyl.leetcode.p00;

/**
 * <h3>二进制求和</b3><br>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。<br>
 * 输入为非空字符串且只包含数字 1 和 0。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * </pre>
 */
public class P0067_AddBinary {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.addBinary("1010", "1011"));
		System.out.println(solution.addBinary("1111", "1111"));
		System.out.println(solution.addBinary(//
				"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
				"110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"//
		));
	}

	// 模拟二进制加法，判断一下进位情况即可
	// 时间复杂度：O(max(n,m))，n和m分别为字符串a和b的长度
	// 空间复杂度：O(max(n,m))
	static class Solution {
		public String addBinary(String a, String b) {
			char[] result = new char[Math.max(b.length(), a.length()) + 1];
			for (int i = result.length - 1, j = a.length() - 1, k = b.length() - 1; i > 0; i--, j--, k--) {
				int sum = 0;
				if (result[i] == '1') {
					sum++;
				}
				if (j >= 0 && a.charAt(j) == '1') {
					sum++;
				}
				if (k >= 0 && b.charAt(k) == '1') {
					sum++;
				}
				result[i] = sum % 2 == 0 ? '0' : '1';
				result[i - 1] = sum < 2 ? '0' : '1';
			}
			return result[0] == '0' ? new String(result, 1, result.length - 1) : new String(result, 0, result.length);
		}
	}

	// 工具类
	// 先将 a 和 b 转化成十进制数，求和后再转化为二进制数。
	// 考虑数值很大的情况，需要使用BigInteger
	static class Solution2 {
		public String addBinary(String a, String b) {
			return new java.math.BigInteger(a, 2).add(new java.math.BigInteger(b, 2)).toString(2);
		}
	}
}
