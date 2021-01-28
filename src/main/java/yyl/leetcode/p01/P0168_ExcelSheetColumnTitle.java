package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>Excel表列名称</h3><br>
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。<br>
 * 
 * <pre>
 * 例如，
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB 
 *     ...
 * </pre>
 * 
 * <pre>
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 * 
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 * 
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 * </pre>
 */
public class P0168_ExcelSheetColumnTitle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("A", solution.convertToTitle(1));
        Assert.assertEquals("AB", solution.convertToTitle(28));
        Assert.assertEquals("ZY", solution.convertToTitle(701));
        Assert.assertEquals("AA", solution.convertToTitle(27));
        Assert.assertEquals("AZ", solution.convertToTitle(52));
    }

    // 进制转换
    // 一共26个字母，题目类似于26进制
    // 因为 Excel 取值范围为 1~26，故可将 26 进制 逻辑上的 个位、十位、百位…均减 1 映射到 0~25 (A-Z)
    // 时间复杂度：O(m)，m 是十进制的位数
    // 空间复杂度：O(1)，不考虑返回结果所占空间
    static class Solution {
        public String convertToTitle(int n) {
            char[] buffer = new char[33];
            int charPos = 33;
            while (n != 0) {
                n--;
                buffer[--charPos] = (char) ((n % 26) + 'A');
                n /= 26;
            }
            return new String(buffer, charPos, (33 - charPos));
        }
    }
}
