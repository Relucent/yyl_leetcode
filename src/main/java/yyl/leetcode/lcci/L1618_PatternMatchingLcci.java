package yyl.leetcode.lcci;

/**
 * <h3>面试题 16.18. 模式匹配</h3><br>
 * 你有两个字符串，即pattern和value。 <br>
 * pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。<br>
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。<br>
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 
 * 提示：
 *     0 <= len(pattern) <= 1000
 *     0 <= len(value) <= 1000
 *     你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 * </pre>
 */
public class L1618_PatternMatchingLcci {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.patternMatching("", ""));// true
		System.out.println(solution.patternMatching("a", ""));// true
		System.out.println(solution.patternMatching("b", ""));// true
		System.out.println(solution.patternMatching("ab", ""));// false
		System.out.println(solution.patternMatching("abba", "dogcatcatdog"));// true
		System.out.println(solution.patternMatching("abba", "dogcatcatfish"));// false
		System.out.println(solution.patternMatching("aaaa", "dogcatcatdog"));// false
		System.out.println(solution.patternMatching("abba", "dogdogdogdog"));// true
	}

	// 算法思路
	// 假设字符串长度n， a的个数为x，匹配的长度为a；，b的个数为y，匹配的长度为b，那么可以得到二元一次方程：
	// x * a + y * b = n
	// 1、获得a、b个数：countA、countB
	// 2、枚举a、b代表的长度：lengthA、lengthB
	// 3、由a、b代表的长度，可以推出a、b代表的字符串：stringA、stringB
	// 4、再验证stringA、 stringB 是否匹配
	// 5、需要考虑pattern 和 value 的长度可 0的情况
	// 时间复杂度：O(N^2)，N是字符串的长度，实际复杂度在 1~O(N^2)之间
	// 空间复杂度：O(N)，N是字符串的长度
	static class Solution {
		public boolean patternMatching(String pattern, String value) {

			// 空描述字符匹配空字符串
			if (pattern.isEmpty() && value.isEmpty()) {
				return true;
			}
			if (pattern.isEmpty() && !value.isEmpty()) {
				return false;
			}

			int countA = 0;
			int countB = 0;
			for (int i = 0; i < pattern.length(); i++) {
				if (pattern.charAt(i) == 'a') {
					countA++;
				} else {
					countB++;
				}
			}

			// 只有一种字符
			if (countA == 0 || countB == 0) {
				return matchOnly(value, Math.max(countA, countB));
			}
			// 假设其中一个字符匹配空串的情况(需要排除value=""的情况)
			if ((matchOnly(value, countA) || matchOnly(value, countB)) && !value.isEmpty()) {
				return true;
			}
			// 遍历，a=a的长度
			for (int a = 1; a * countA < value.length(); a++) {
				// 不能被整除
				if ((value.length() - countA * a) % countB != 0) {
					continue;
				}
				// b的长度
				int b = (value.length() - countA * a) / countB;
				if (match(pattern, value, a, b)) {
					return true;
				}
			}
			return false;
		}

		// 只有一种字符情况的匹配
		private boolean matchOnly(String value, int countX) {
			// 只有一种字符，其代表空串即可满足
			if (value.isEmpty()) {
				return true;
			}
			// 不能整除
			if (value.length() % countX != 0) {
				return false;
			}
			int lengthX = value.length() / countX;
			String match = value.substring(0, lengthX);
			for (int i = lengthX; i < value.length(); i += lengthX) {
				if (!match.equals(value.substring(i, i + lengthX))) {
					return false;
				}
			}
			return true;
		}

		// 匹配
		private boolean match(String pattern, String value, int lengthA, int lengthB) {
			String stringA = null;
			String stringB = null;
			for (int i = 0, j = 0; i < pattern.length(); i++) {
				if (pattern.charAt(i) == 'a') {
					String substring = value.substring(j, j + lengthA);
					if (stringA == null) {
						stringA = substring;
					} else if (!stringA.equals(substring)) {
						return false;
					}
					j += lengthA;
				} else {
					String substring = value.substring(j, j + lengthB);
					if (stringB == null) {
						stringB = substring;
					} else if (!stringB.equals(substring)) {
						return false;
					}
					j += lengthB;
				}
			}
			return true;
		}
	}
}
