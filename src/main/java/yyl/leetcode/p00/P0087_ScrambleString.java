package yyl.leetcode.p00;

/**
 * <h3>扰乱字符串</h3><br>
 * <br>
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。<br>
 * 
 * <pre>
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *  
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。<br>
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。<br>
 * 
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。<br>
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。<br>
 * 
 *     rgtae
 *    /     \
 *   rg     tae
 *  / \     /  \
 * r   g   ta   e
 *        / \
 *       t   a
 * 
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。<br>
 * </pre>
 * 
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 * </pre>
 */
// 难度：困难
public class P0087_ScrambleString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isScramble("great", "rgeat"));
        System.out.println(solution.isScramble("abcde", "caebd"));
    }

    // 暴力法， 题解思路：
    // 假设，字符串s1和s2是scramble的话，那么必然存在一个位置 i 将s1和s2 同时划分为长度为 i 和length-i的子字符串(s11,s12,s21,s22)
    // 这两对子串，一定满足 (s11和s21是scramble 并且 s12和s22也是scramble) 或者 (s11和s22是scramble 并且 s12和s21也是scramble)
    // 时间复杂度：O(N^3)
    // 空间复杂度：O(N)
    static class Solution {
        public boolean isScramble(String s1, String s2) {
            // 子串长度不相等，不满足要求
            if (s1.length() != s2.length()) {
                return false;
            }
            // 将字符串转换为字符数组来处理，避免每次生成新的字符串
            return isScramble(s1.toCharArray(), 0, s2.toCharArray(), 0, s1.length());
        }

        private boolean isScramble(char[] s1, int offset1, char[] s2, int offset2, int length) {
            // 遍历两个子串
            boolean equals = true;
            int[] letters = new int[26];
            for (int i = 0; i < length; i++) {
                letters[s1[offset1 + i] - 'a']++;
                letters[s2[offset2 + i] - 'a']--;
                if (s1[offset1 + i] != s2[offset2 + i]) {
                    equals = false;
                }
            }
            // 子串相等，满足要求
            if (equals) {
                return true;
            }
            // 如果S2中包含S1中没有的字符，那么不满足要求
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] != 0) {
                    return false;
                }
            }
            for (int i = 1; i < length; i++) {
                // s1的左子串与s2的左子串匹配&&s1的右子串与s2的右子串匹配
                if (isScramble(s1, offset1, s2, offset2, i) //
                        && isScramble(s1, offset1 + i, s2, offset2 + i, length - i)) {
                    return true;
                }
                // s1的左子串与s2的右子串匹配&&s1的右子串与s2的左子串匹配
                if (isScramble(s1, offset1, s2, offset2 + length - i, i) && isScramble(s1, offset1 + i, s2, offset2, length - i)) {
                    return true;
                }
            }
            return false;
        }
    }

    // 暴力法， 题解思路：
    // 假设，字符串s1和s2是scramble的话，那么必然存在一个位置 i 将s1和s2 同时划分为长度为 i 和length-i的子字符串(s11,s12,s21,s22)
    // 这两对子串，一定满足 (s11和s21是scramble 并且 s12和s22也是scramble) 或者 (s11和s22是scramble 并且 s12和s21也是scramble)
    // 这个算法，代码更简洁易懂一些。但是每次生成新的字符串，所以性能略有下降。
    // 时间复杂度：O(N^3)
    // 空间复杂度：O(N)
    static class Solution1 {
        public boolean isScramble(String s1, String s2) {
            // 字符串相等，一定满足要求
            if (s1.equals(s2)) {
                return true;
            }
            // 如果S2中包含S1中没有的字符，那么不满足要求
            int[] letters = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                letters[s1.charAt(i) - 'a']++;
                letters[s2.charAt(i) - 'a']--;
            }
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] != 0) {
                    return false;
                }
            }
            for (int i = 1; i < s1.length(); i++) {
                // s1的左子串与s2的左子串匹配&&s1的右子串与s2的右子串匹配
                if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                    return true;
                }
                // s1的左子串与s2的右子串匹配&&s1的右子串与s2的左子串匹配
                if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                    return true;
                }
            }
            return false;
        }
    }
}
