package yyl.leetcode.p057;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>插入区间</h3><br>
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。<br>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * </pre>
 */
public class InterleavingInterval {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})));
    }

    // 1. 第一个 while循环，若当前区间的end值 小于新区间的start值，那么这两个区间不重叠，直接往结果添加区间数组
    // 2. 第二个 while循环，如果出现重合(如果重合，当前区间的start值一定小于等于新区间的end值)，更新新区间的start和end值，最后将新区间加入结果
    // 3. 第三个 while循环加入后面剩下的区间
    // 时间复杂度：O(N)，一次遍历
    // 空间复杂度：O(N)，存储返回结果
    static class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int i = 0;
            for (; i < intervals.length && intervals[i][1] < newInterval[0]; i++) {
                result.add(intervals[i]);
            }
            for (; i < intervals.length && intervals[i][0] <= newInterval[1]; i++) {
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
            result.add(newInterval);
            for (; i < intervals.length; i++) {
                result.add(intervals[i]);
            }
            return result.toArray(new int[result.size()][]);
        }
    }
}
