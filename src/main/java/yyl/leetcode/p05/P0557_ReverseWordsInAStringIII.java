package yyl.leetcode.p05;

import yyl.leetcode.util.Assert;

/**
 * <h3>反转字符串中的单词 III</h3><br>
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。<br>
 * 
 * <pre>
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * </pre>
 * 
 * 提示： 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。<br>
 */
public class P0557_ReverseWordsInAStringIII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc", solution.reverseWords("Let's take LeetCode contest"));
    }

    // 迭代+原地翻转
    // 根据空格判断单词，遇到空格（或者到达末尾），说明是一个单词的结束，那么进行原地翻转。
    // 需要注意的是，单词不包含后面空格，所以单词最后位置是空格位置-1
    // 时间复杂度：O(N)。其中 N 为字符串的长度。字符串中的每个字符要么在 O(1) 的时间内被交换到相应的位置，要么因为是空格而保持不动。
    // 空间复杂度：O(1)。因为不需要开辟额外的数组。
    static class Solution {
        public String reverseWords(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();
            // 遍历字符串字符
            for (int i = 0; i < n; i++) {
                // 单词开始
                int start = i;
                // 单词结束
                while (i < n && chars[i] != ' ') {
                    i++;
                }
                // 原地翻转单词
                reverse(chars, start, i - 1);
            }
            return new String(chars);
        }

        private void reverse(char[] chars, int left, int right) {
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
    }
}
