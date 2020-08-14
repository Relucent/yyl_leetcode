package yyl.leetcode.lcci;

/**
 * <h3>面试题46. 把数字翻译成字符串</h3><br>
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示： 0 <= num < 2^31
 * </pre>
 */

public class L0046_BaShuZiFanYiChengZiFuChuanLcof {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.translateNum(12258));// 5
        System.out.println(solution.translateNum(25035321));// 6
        System.out.println(solution.translateNum(631));// 6
    }

    // 动态规划优化（数字求余）
    // 时间复杂度： O(N)
    // 空间复杂度：O(1)
    static class Solution {
        public int translateNum(int num) {
            int a = 1;
            int b = 1;
            int x;
            int y = num % 10;
            while (num > 0) {
                num = num / 10;
                x = num % 10;
                int c = (x == 1 || (x == 2 && y <= 5)) ? (a + b) : a;
                b = a;
                a = c;
                y = x;
            }
            return a;
        }
    }

    // 动态规划
    // 时间复杂度 O(N)
    // 空间复杂度 O(N)
    static class Solution2 {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int a = 1;
            int b = 1;
            for (int i = 2; i <= s.length(); i++) {
                String tmp = s.substring(i - 2, i);
                int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : b;
                a = b;
                b = c;
            }
            return b;
        }
    }
}
