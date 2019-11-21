package yyl.leetcode.p042;

/**
 * <h3>接雨水</h3><br>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。<br>
 * 
 * <pre>
 * +│
 * 3│--------------■----------
 * 2│------■-□-□-□-■-■-□-■----
 * 1│--■-□-■-■-□-■-■-■-■-■-■--
 * 0└─────────────────────────>
 * </pre>
 * 
 * 如图：由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（□部分表示雨水） <br>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]<br>
 * 输出: 6<br>
 */
public class Trap {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));// 6
    }


    static class Solution {

        // 对于一个较低的坑来说，其储水量高度取决于左侧最高柱与右侧最高柱的较小高度
        // lower = height[(height[left] < height[right]) ? left : right]
        // 然后 if(height[left] < height[right]){left++;}else{right--}移动指针
        // 时间复杂度O(n)，空间复杂度O(1)
        public int trap(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int level = 0; // 记录当前水平面
            int result = 0;
            while (left < right) {
                int lower = height[(height[left] < height[right]) ? (left++) : (right--)];
                level = level > lower ? level : lower;
                result += level - lower;
            }
            return result;
        }
    }
}
