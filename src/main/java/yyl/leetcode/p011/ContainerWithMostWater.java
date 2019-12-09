package yyl.leetcode.p011;

/**
 * <h3>盛最多水的容器</h3><br>
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。 <br>
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。 <br>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。<br>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。<br>
 * 
 * <pre>
 * +│-------------------
 * 8│---■---------■-----
 * 7│---■~~~~~~~~~■~~~■-
 * 6│---■-■-------■---■-
 * 5│---■-■---■---■---■-
 * 4│---■-■---■-■-■---■-
 * 3│---■-■---■-■-■-■-■-
 * 2│---■-■-■-■-■-■-■-■-
 * 1│-■-■-■-■-■-■-■-■-■-
 * 0└────────────────────>
 * -0~1~2~3~4~5~6~7~8~9+
 * 图中“■”代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（水平面表示“~”）的最大值为 49（7×7）。
 * </pre>
 * 
 * 示例:<br>
 * 输入: [1,8,6,2,5,4,8,3,7]<br>
 * 输出: 49<br>
 * <br>
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). <br>
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). <br>
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.<br>
 * Note: You may not slant the container and n is at least 2. <br>
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {2, 3, 4, 5, 18, 17, 6};
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1001; i++)
            System.out.println(solution.maxArea(height));

        System.out.println(System.currentTimeMillis() - l);
    }

    static class Solution {
        // 双指针法
        // 1.首先假设我们找到能取最大容积的纵线为 i , j (假定i<j)，那么得到的最大容积 C = min(ai,aj)*(j-i)
        // 2.在 j的右端没有一条线会比它高(因为如果存在 k(j<k&&ak>aj,那么由i,k构成的容器的容积也会大于 C'> C
        // 3.同理，在i的左边也不会有比它高的线
        // 所以我们从两头向中间靠拢，同时更新候选值；在收缩区间的时候优先从 较小的边开始收缩
        // 时间复杂度 ：O(N2)
        // 空间复杂度 ：O(1)
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while (left < right) {
                max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
                if (height[left] < height[right]) {
                    int i = left;
                    while (i < right && height[i] <= height[left]) {
                        i++;
                    }
                    left = i;
                } else {
                    int i = right;
                    while (i > left && height[i] <= height[right]) {
                        i--;
                    }
                    right = i;
                }
            }
            return max;
        }
    }

    // 暴力法
    // 简单地考虑每对可能出现的线段组合并找出这些情况之下的最大面积
    // 时间复杂度 ：O(N^2)
    // 空间复杂度 ：O(1)
    static class Solution2 {
        public int maxArea(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    // 一只水桶能装多少水取决于它最短的那块木板，所以高度取 Math.min(a,b)
                    max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
                }
            }
            return max;
        }
    }
}
