package yyl.leetcode.p043;

/**
 * <h3>字符串相乘</h3><br>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。<br>
 * 示例 1:<br>
 * 输入: num1 = "2", num2 = "3"<br>
 * 输出: "6"<br>
 * 示例 2:<br>
 * 输入: num1 = "123", num2 = "456"<br>
 * 输出: "56088"<br>
 * 说明：<br>
 * num1 和 num2 的长度小于110。<br>
 * num1 和 num2 只包含数字 0-9。<br>
 * num1 和 num2 均不以零开头，除非是数字 0 本身。<br>
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。<br>
 */
public class Multiply {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.multiply("2", "3"));// 6
        System.out.println(solution.multiply("123", "456"));// 56088
        System.out.println(solution.multiply("789", "0"));// 0
        System.out.println(solution.multiply("99", "99"));// 9801
        System.out.println(solution.multiply("5", "10000"));// 50000
    }

    static class Solution {

        // 时间复杂度 O(m*n); m=num1.length(),n=num2.length()
        public String multiply(String num1, String num2) {

            // 任何一个数乘以0，结果都为0
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }

            // 两个数字相乘，结果的位数小于等于两个数字的位数相加(例如两个2位数相乘，最多是个4位数：99*99=9801)
            int len1 = num1.length();
            int len2 = num2.length();
            int len3 = len1 + len2;
            int[] num3 = new int[len3];
            for (int p1 = len1 - 1; p1 >= 0; p1--) {
                int v1 = num1.charAt(p1) - '0';
                for (int p2 = len2 - 1; p2 >= 0; p2--) {
                    int v2 = num2.charAt(p2) - '0';
                    int p3 = p1 + p2 + 1;
                    int v3 = num3[p3] + (v1 * v2);
                    num3[p3] = v3 % 10;
                    num3[p3 - 1] += v3 / 10;
                }
            }

            // 拼装结果
            StringBuilder buffer = new StringBuilder();
            // 忽略头部的0
            int p3 = 0;
            while (num3[p3] == 0) {
                p3++;
            }
            while (p3 < len3) {
                buffer.append(num3[p3++]);
            }
            // 已经排除乘数0的情况，所以不需要额外判断buffer.size()
            return buffer.toString();
        }
    }
}
