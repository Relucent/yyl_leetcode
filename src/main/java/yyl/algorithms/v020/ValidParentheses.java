package yyl.algorithms.v020;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.<br>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.<br>
 */
// 给一串只包含有'(', ')', '{', '}', '[' 和']',的字符串，要求判定是否是合法的。
public class ValidParentheses {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.isValid("()[]{}"));//true
		System.out.println(solution.isValid("(()"));//false
		System.out.println(solution.isValid("(]"));//false
		System.out.println(solution.isValid("(1)"));//false
		System.out.println(solution.isValid("[[({})]]"));//true
		System.out.println(solution.isValid("{}][}}{[))){}{}){(}]))})[({"));//false
		System.out.println(solution.isValid("[(({})}]")); //false
	}

	static class Solution {
		public boolean isValid(String s) {
			char[] stack = new char[s.length()];
			int k = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				switch (c) {
				case '(':
				case '[':
				case '{':
					stack[k++] = c;
					break;
				case ')':
					if (k == 0 || stack[--k] != '(') {
						return false;
					}
					break;
				case ']':
					if (k == 0 || stack[--k] != '[') {
						return false;
					}
					break;
				case '}':
					if (k == 0 || stack[--k] != '{') {
						return false;
					}
					break;
				default:
					return false;
				}
			}
			return k == 0;
		}
	}
}
