package yyl.leetcode.p00;

/**
 * <h3>最小覆盖子串</h3><br>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。<br>
 * 
 * <pre>
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S中不存这样的子串，则返回空字符串 ""。
 * 如果 S中存在这样的子串，我们保证它是唯一的答案。
 * </pre>
 */
public class P0076_MinimumWindowSubstring {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    // 双指针滑动窗口
    // 1. 初始化左右指针 left = right = 0，把索引区间 [left, right] 称为一个「窗口」。
    // 2. 不断地增加 right指针 扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
    // 3. 停止增加 right，转而不断增加 left指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T中的所有字符了）。
    // 4. 重复第 2和第 3步，直到 right到达字符串 S的尽头。
    // 5. 取符合条件并且长度最小的结果
    // 判断窗口中字符串是否符合要求会很影响算法效率，如果一个一个去比较会很慢，所以使用两个哈希表当作计数器解决。
    // 因为题目中字符串的字符都是是字母，所以可以用数组(int[])来代替哈希表(map<char,int>)
    // 时间复杂度：O(N)，N表示字符串长度
    // 空间复杂度：O(1)，常数级空间复杂度
    static class Solution {
        public String minWindow(String s, String t) {
            int[] need = new int[128];
            int needLength = t.length();
            int[] window = new int[128]; // 窗口
            char[] chars = s.toCharArray();
            int left = 0;
            int right = 0;
            int matchCount = 0;// 记录匹配的字符个数
            int minStart = -1;// 最小匹配子串位置
            int minLength = Integer.MAX_VALUE;// 最小匹配子串长度
            for (int i = 0; i < needLength; i++) {
                need[t.charAt(i)]++;
            }
            while (right < chars.length) {

                // 增加 right指针扩大窗口 [left, right]
                {
                    char c = chars[right];
                    if (need[c] > 0) {
                        window[c]++;
                        if (window[c] <= need[c]) {
                            matchCount++;
                        }
                    }
                    right++;
                }

                // 窗口中的字符串符合要求（增加 left指针，缩小窗口）
                while (matchCount == needLength) {
                    // 新的匹配，比记录的最小匹配长度小
                    if (right - left < minLength) {
                        minStart = left;
                        minLength = right - left;
                    }
                    char c = chars[left];
                    if (need[c] > 0) {
                        if (window[c] <= need[c]) {
                            matchCount--;
                        }
                        window[c]--;
                    }
                    left++;
                }
            }
            return minStart == -1 ? "" : new String(chars, minStart, minLength);
        }
    }
}
