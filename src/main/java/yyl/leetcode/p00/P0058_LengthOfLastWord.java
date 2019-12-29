package yyl.leetcode.p00;

/**
 * <h3>最后一个单词的长度</h3><br>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。<br>
 * 如果不存在最后一个单词，请返回 0 。<br>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。<br>
 * 
 * <pre>
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 * </pre>
 */
public class P0058_LengthOfLastWord {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord("Hello World"));// 5
    }

    // 从字符串末尾开始向前遍历：先过滤掉末尾的空格，然后计算末尾单词的长度
    // 时间复杂度：O(N), N为结尾空格和结尾单词总体长度
    // 空间复杂度：O(1)
    static class Solution {
        public int lengthOfLastWord(String s) {
            int i = s.length() - 1;
            int j = 0;
            for (; i >= 0 && s.charAt(i) == ' '; i--);
            for (; i >= 0 && s.charAt(i) != ' '; i--, j++);
            return j;
        }
    }
}
