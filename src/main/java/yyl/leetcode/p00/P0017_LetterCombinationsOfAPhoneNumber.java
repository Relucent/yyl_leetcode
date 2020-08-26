package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>电话号码的字母组合</h3><br>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。<br>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。<br>
 * 
 * <pre>
 * (1    )(2abc )(3def )
 * (4ghi_)(5jkl )(6mno )
 * (7pqrs)(8tuv )(9wxyz)
 * (*+   )(0    )(#    )
 * </pre>
 * 
 * <pre>
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * </pre>
 * 
 * 说明: 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 <br>
 */
// Letter Combinations of a Phone Number
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
// Example:
// Input: "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
public class P0017_LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("01"));
        System.out.println(solution.letterCombinations("##"));
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations("345"));
    }

    // 回溯
    // 使用回溯算法用于寻找所有的可行解
    // 1、首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
    // 2、回溯过程中维护一个字符数组，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
    // 3、该字符串初始为空，每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。
    // 4、进行回退操作，遍历其余的字母排列。
    // 5、优化：哈希表可以使用数组来代替（字符从2开始，那么c-'2'即可得到映射的索引），字符串可以用字符数组代替。
    // 复杂度分析
    // 时间复杂度：O(3m*4n)，其中 m 是输入中对应 3个字母的数字个数（包括数字 2、3、4、5、6、8），n 是输入中对应 4个字母的数字个数（包括数字 7、9），m+n 是输入数字的总个数。不同的字母组合一共有 3m*4n种，需要遍历每一种字母组合。
    // 空间复杂度：O(m+n)，除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，可以看成常数，递归调用层数最大为m+n。
    static class Solution {

        private static final char[][] MAPPING = { //
                { 'a', 'b', 'c' }, // 2
                { 'd', 'e', 'f' }, // 3
                { 'g', 'h', 'i' }, // 4
                { 'j', 'k', 'l' }, // 5
                { 'm', 'n', 'o' }, // 6
                { 'p', 'q', 'r', 's' }, // 7
                { 't', 'u', 'v' }, // 8
                { 'w', 'x', 'y', 'z' },// 9
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
