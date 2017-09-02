package yyl.algorithms.v022;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.x Given n
 * pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * <pre>
 * For example, given n = 3, a solution set is: 
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * </pre>
 */
//给定一个非负整数n，生成n对括号的所有合法排列
public class GenerateParentheses {

	public static void main(String[] args) {
		Solution solution = new Solution();
		List<String> result = solution.generateParenthesis(3);
		System.out.println(result);
	}

	static class Solution {
		public List<String> generateParenthesis(int n) {
			List<String> result = new ArrayList<>();
			generate(new char[n << 1], 0, n, n, result);
			return result;
		}

		//定义两个变量left和right分别表示剩余左右括号的个数
		private void generate(char[] temp, int pos, int leftNum, int rightNum, List<String> result) {
			if (leftNum == 0 && rightNum == 0) {
				result.add(new String(temp));
				return;
			}
			if (leftNum > 0) {
				temp[pos] = '(';
				generate(temp, pos + 1, leftNum - 1, rightNum, result);
			}
			if (rightNum > 0 && rightNum > leftNum) {
				temp[pos] = ')';
				generate(temp, pos + 1, leftNum, rightNum - 1, result);
			}
		}
	}

}
