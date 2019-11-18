package yyl.leetcode.p038;


/**
 * <h3>报数</h3><br>
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：<br>
 * 1. 1<br>
 * 2. 11<br>
 * 3. 21<br>
 * 4. 1211<br>
 * 5. 111221<br>
 * 1 被读作 "one 1" ("一个一") , 即 11。<br>
 * 11 被读作 "two 1s" ("两个一"）, 即 21。<br>
 * 21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。<br>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。<br>
 * 注意：整数顺序将表示为一个字符串。<br>
 * 示例 1:<br>
 * 输入: 1<br>
 * 输出: "1"<br>
 * 示例 2:<br>
 * 输入: 4<br>
 * 输出: "1211"<br>
 */
public class CountAndSay {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(1));// 1
        System.out.println(solution.countAndSay(4));// 1211
    }

    static class Solution {
        public String countAndSay(int n) {
            String result = "1";
            for (int i = 1; i < n; i++) {
                StringBuilder buufer = new StringBuilder();
                int end = result.length();
                for (int j = 0; j < end;) {
                    char ch = result.charAt(j);
                    int k = j + 1;
                    while (k < end && ch == result.charAt(k)) {
                        k++;
                    }
                    buufer.append(k - j).append(ch);
                    j = k;
                }
                result = buufer.toString();
            }
            return result;
        }
    }
}
