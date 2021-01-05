package yyl.leetcode.p08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>较大分组的位置</h3><br>
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。<br>
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。<br>
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。<br>
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。<br>
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。<br>
 * 
 * <pre>
示例 1：

输入：s = "abbxxxxzzy"
输出：[[3,6]]
解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。

示例 2：

输入：s = "abc"
输出：[]
解释："a","b" 和 "c" 均不是符合要求的较大分组。

示例 3：

输入：s = "abcdddeeeeaabbbcd"
输出：[[3,5],[6,9],[12,14]]
解释：较大分组为 "ddd", "eeee" 和 "bbb"

示例 4：

输入：s = "aba"
输出：[]
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= s.length <= 1000 <br>
 * └ s 仅含小写英文字母 <br>
 */

public class P0830_PositionsOfLargeGroups {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList(//
                Arrays.asList(3, 6)//
        ), solution.largeGroupPositions("abbxxxxzzy"));
        Assert.assertEquals(Arrays.asList(), solution.largeGroupPositions("abc"));
        Assert.assertEquals(Arrays.asList(//
                Arrays.asList(3, 5), //
                Arrays.asList(6, 9), //
                Arrays.asList(12, 14)//
        ), solution.largeGroupPositions("abcdddeeeeaabbbcd"));
        Assert.assertEquals(Arrays.asList(), solution.largeGroupPositions("aba"));
    }

    // 双指针，一次遍历
    // 左指针指向这个分组的首位置，右指针用来遍历，当s[l] != s[r]的时候，这个右指针指向与此分组不相同的首位置，那么这个分组的长度为 r-l。
    // 为了处理最后一个分组，需要遍历到n，而不是n-1。
    // 时间复杂度：O(n)，其中 n 是字符串的长度，需要遍历一次该数组。
    // 空间复杂度：O(1)。只需要常数的空间来保存若干变量（返回值不计入空间复杂度）。
    static class Solution {
        public List<List<Integer>> largeGroupPositions(String s) {
            List<List<Integer>> answer = new ArrayList<>();
            for (int l = 0, r = 0, n = s.length(); r <= n; r++) {
                if (r == n || s.charAt(l) != s.charAt(r)) {
                    if (r - l >= 3) {
                        answer.add(Arrays.asList(l, r - 1));
                    }
                    l = r;
                }
            }
            return answer;
        }
    }
}
