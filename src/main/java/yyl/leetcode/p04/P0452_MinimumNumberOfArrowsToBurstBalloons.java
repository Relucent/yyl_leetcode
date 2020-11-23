package yyl.leetcode.p04;

import java.util.Arrays;
import java.util.Comparator;

import yyl.leetcode.util.Assert;

/**
 * <h3>用最少数量的箭引爆气球</h3><br>
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。<br>
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。<br>
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * 
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * 
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * </pre>
 * 
 * 提示：<br>
 * 0 <= points.length <= 104<br>
 * points[i].length == 2<br>
 * -231 <= xstart < xend <= 231 - 1<br>
 */

public class P0452_MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.findMinArrowShots(new int[][] { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } }));
        Assert.assertEquals(4, solution.findMinArrowShots(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } }));
        Assert.assertEquals(2, solution.findMinArrowShots(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } }));
        Assert.assertEquals(1, solution.findMinArrowShots(new int[][] { { 1, 2 } }));
        Assert.assertEquals(1, solution.findMinArrowShots(new int[][] { { 2, 3 }, { 2, 3 } }));
    }

    // 排序 + 贪心算法
    // 一定存在一种最优（射出的箭数最小）的方法，使得每一支箭的射出位置都恰好对应着某一个气球的右边界。
    // 对于其中的任意一支箭，将这支箭的位置移动到它对应的「原本引爆的气球中最靠左的右边界位置」，那么这些原本引爆的气球仍然被引爆。
    // 首先对气球结束的位置进行排序，从一个气球开始，要用的箭为一根，用end记录气球结束坐标，如果第二个气球的开始坐标小于end，说明两个气球有重叠部分，箭的数量不用增加，end更新为遍历到的气球结束坐标的最小值（因为已经排序了，所以必然是开始那个气球的结束坐标比较小，不用更新）。
    // 时间复杂度：O(nlog⁡n)，其中 n是数组 points的长度。排序的时间复杂度为 O(nlog⁡n)，对所有气球进行遍历并计算答案的时间复杂度为 O(n)。
    // 空间复杂度：O(log⁡n)，即为排序需要使用的栈空间。
    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // 没有气球
            int n = points.length;
            if (n == 0) {
                return 0;
            }
            // 对气球结束的位置进行排序
            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return Integer.compare(a[1], b[1]);
                }
            });
            // 贪心算法
            int answer = 1;
            int end = points[0][1];
            for (int i = 0; i < n; i++) {
                if (end < points[i][0]) {
                    answer++;
                    end = points[i][1];
                }
            }
            return answer;
        }
    }

}
