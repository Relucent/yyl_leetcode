package yyl.leetcode.p01;

/**
 * <h3>验证回文串</h3><br>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。<br>
 * 说明：本题中，我们将空字符串定义为有效的回文串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * </pre>
 */

public class P0125_ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome("race a car"));
        System.out.println(solution.isPalindrome("0P"));
    }

    // 双指针
    // 初始时，左右指针分别指向 s的两侧，随后我们不断地将这两个指针相向移动，每次移动一步，并判断这两个指针指向的字符是否相同。当这两个指针相遇时，就说明 s是回文串。
    // 遇到非字母和数字字符的时候需要略过，最后比较字符相等时候忽略字母的大小写
    // 时间复杂度：O(|s|)，其中|s| 是字符串 s 的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                char cLeft = s.charAt(left);
                char cRight = s.charAt(right);
                if (!Character.isLetterOrDigit(cLeft)) {
                    left++;
                    continue;
                }
                if (!Character.isLetterOrDigit(cRight)) {
                    right--;
                    continue;
                }
                if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
