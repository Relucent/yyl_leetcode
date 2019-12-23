package yyl.leetcode.p060;

/**
 * <h3>第k个排列</h3><br>
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。<br>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：<br>
 * "123"<br>
 * "132"<br>
 * "213"<br>
 * "231"<br>
 * "312"<br>
 * "321"<br>
 * 给定 n 和 k，返回第 k 个排列。<br>
 * 说明：<br>
 * 给定 n 的范围是 [1, 9]。<br>
 * 给定 k 的范围是[1, n!]。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 
 * 示例 2:
 * 输入: n = 4, k = 9 
 * 输出: "2314"
 * </pre>
 */
public class PermutationSequence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getPermutation(3, 5));// 312
    }

    // 数学法
    // n个数字有 n！种全排列，每种数字开头的全排列有 (n - 1)!种。
    // 所以用 k/((n-1)!) 就可以得到第 k个全排列是以第几个数字开头的。
    // 用 k%((n-1)!) 就可以得到第 k个全排列是某个数字开头的全排列中的第几个。
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    static class Solution {
        public String getPermutation(int n, int k) {
            StringBuilder nums = new StringBuilder("123456789");
            StringBuilder result = new StringBuilder(n);
            int[] group = new int[n];
            group[0] = 1;
            for (int i = 1; i < n; i++) {
                group[i] = group[i - 1] * i;
            }
            k = k - 1;// k是从1开始的，但是索引是从0开始的，所以k-1
            for (int i = n; i >= 1; i--) {
                int index = k / group[i - 1];
                k = k % group[i - 1];
                result.append(nums.charAt(index));
                nums.deleteCharAt(index);
            }
            return result.toString();
        }
    }

    // 回溯递归
    // 时间复杂度：O(n!)，实际是 O(3*(n!))，但是计算复杂度常数可以忽略
    // 空间复杂度：O(n)，存储结果数组
    static class Solution2 {
        public String getPermutation(int n, int k) {
            char[] chars = new char[n];
            for (int i = 0; i < n; i++) {
                chars[i] = (char) ('0' + i + 1);
            }
            permute(chars, 0, chars.length - 1, 0, k);
            return new String(chars);
        }

        private int permute(char[] chars, int begin, int end, int count, int k) {
            if (begin == end) {
                return count + 1;
            }
            for (int i = begin; i <= end; i++) {
                insert(chars, i, begin);
                count = permute(chars, begin + 1, end, count, k);
                if (count == k) {
                    return count;
                }
                insert(chars, begin, i);
            }
            return count;
        }

        private void insert(char[] chars, int source, int target) {
            char temp = chars[source];
            if (source < target) {
                for (int i = source; i < target; i++) {
                    chars[i] = chars[i + 1];
                }
                chars[target] = temp;
            } else if (source > target) {
                for (int i = source; i > target; i--) {
                    chars[i] = chars[i - 1];
                }
                chars[target] = temp;
            }
        }
    }
}
