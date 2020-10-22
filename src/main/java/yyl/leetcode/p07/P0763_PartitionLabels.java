package yyl.leetcode.p07;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>划分字母区间</h3><br>
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * </pre>
 * 
 * 提示：<br>
 * S的长度在[1, 500]之间。<br>
 * S只包含小写字母 'a' 到 'z' 。<br>
 */
public class P0763_PartitionLabels {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }

    // 贪心算法 + 双指针
    // 同一个字母只能出现在同一个片段，可以得出同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
    // 1、遍历字符串，得到每个字母最后一次出现的下标位置。
    // 2、遍历字符串，遍历的同时维护当前片段的开始下标 start和结束下标 end 初始时 start=end=0
    // 3、对于每个访问到的字母 c ，得到当前字母的最后一次出现的下标位置 endC，则当前片段的结束下标一定不会小于 end​，因此令 end=max⁡(end,endC)
    // 4、访问到下标 end 时，当前片段访问结束，当前片段的下标范围是 [start,end]，长度为 end−start+1
    // 5、重复上述过程，直到遍历完字符串。
    // 时间复杂度：O(n)，其中 n 是字符串的长度。需要遍历字符串两次，第一次遍历时记录每个字母最后一次出现的下标位置，第二次遍历时进行字符串的划分。
    // 空间复杂度：O(1)，字符串只包含小写字母，因此 空间复杂度是常量(26)
    static class Solution {
        public List<Integer> partitionLabels(String S) {
            List<Integer> answer = new ArrayList<>();
            int[] last = new int[26];
            for (int i = 0; i < S.length(); i++) {
                last[S.charAt(i) - 'a'] = i;
            }
            int start = 0;
            int end = 0;
            for (int i = 0; i < S.length(); i++) {
                end = Math.max(end, last[S.charAt(i) - 'a']);
                if (i == end) {
                    answer.add(end - start + 1);
                    start = end + 1;
                }
            }
            return answer;
        }
    }
}
