package yyl.leetcode.p00;

/**
 * <h3>跳跃游戏</h3><br>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。<br>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。<br>
 * 判断你是否能够到达最后一个位置。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * </pre>
 */
public class P0055_JumpGame {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canJump(new int[] {2, 3, 1, 1, 4}));// true
        System.out.println(solution.canJump(new int[] {3, 2, 1, 0, 4}));// false
    }


    // 计算可能跳跃的最大距离
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    static class Solution {
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            if (nums.length == 1) {
                return true;
            }
            int max = nums[0];
            for (int i = 1; i <= max && i < nums.length - 1; i++) {
                max = Math.max(max, i + nums[i]);
            }
            return max >= nums.length - 1;
        }
    }

    // 反向规划
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    static class Solution2 {
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            if (nums.length == 1) {
                return true;
            }
            int last = nums.length - 1;
            for (int i = last; i >= 0; i--) {
                if (i + nums[i] >= last) {
                    last = i;
                }
            }
            return last == 0;
        }
    }
}
