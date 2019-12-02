package yyl.leetcode.p017;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>电话号码的字母组合</h3><br>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。<br>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。<br>
 * 示例:
 * 
 * <pre>
 * (1    )(2abc )(3def )
 * (4ghi_)(5jkl )(6mno )
 * (7pqrs)(8tuv )(9wxyz)
 * (*+   )(0    )(#    )
 * </pre>
 * 
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].<br>
 * Given a digit string, return all possible letter combinations that the number could represent.<br>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.<br>
 * Input:Digit string "23"<br>
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].<br>
 * 
 * <pre>
 * (1    )(2abc )(3def )
 * (4ghi_)(5jkl )(6mno )
 * (7pqrs)(8tuv )(9wxyz)
 * (*+   )(0    )(#    )
 * </pre>
 */
// 给定一个数字字符串，返回该数字所代表的所有可能的字母组合。
// 数字到字母的映射(就像电话按钮一样)
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("01"));
        System.out.println(solution.letterCombinations("##"));
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations("345"));
    }

    static class Solution {

        private static final char[][] MAPPING = { //
                {'a', 'b', 'c'}, // 2
                {'d', 'e', 'f'}, // 3
                {'g', 'h', 'i'}, // 4
                {'j', 'k', 'l'}, // 5
                {'m', 'n', 'o'}, // 6
                {'p', 'q', 'r', 's'}, // 7
                {'t', 'u', 'v'}, // 8
                {'w', 'x', 'y', 'z'},// 9
        };

        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits.isEmpty()) {
                return result;
            }
            char[] chars = digits.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] < '2' || chars[i] > '9') {
                    return result;
                }
                chars[i] -= '2';
            }
            backtrack(chars, 0, result);
            return result;
        }

        private static void backtrack(char[] chars, int pos, List<String> result) {
            if (pos == chars.length - 1) {
                char i = chars[pos];
                for (char c : MAPPING[i]) {
                    chars[pos] = c;
                    result.add(new String(chars));
                }
                chars[pos] = i;
            } else {
                char i = chars[pos];
                for (char c : MAPPING[i]) {
                    chars[pos] = c;
                    backtrack(chars, pos + 1, result);
                }
                chars[pos] = i;
            }
        }
    }
}
