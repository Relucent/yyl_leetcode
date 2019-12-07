package yyl.leetcode.p053;

/**
 * <h3>最大子序和</b3><br>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。<br>
 * 示例:<br>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],<br>
 * 输出: 6<br>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。<br>
 * 进阶:<br>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。<br>
 */
public class MaxSubArray {

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums));
    }

    // 扫描法(最优解)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    static class Solution {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            // 最大子序和
            int max = nums[0];
            // 前一组子序的最大数
            int currentMax = nums[0];
            for (int i = 1; i < nums.length; i++) {
                // 若 currentMax<0 可以推导出 currentMax + nums[i] < nums[i]
                // 那么当前元素的最大子序应该就是自己(不能加之前的元素)
                // 故 currentMax= currentMax< 0 ? nums[i] : currentMax + nums[i]
                // 然后取最大子序和
                max = Math.max(currentMax = currentMax < 0 ? nums[i] : currentMax + nums[i], max);
            }
            return max;
        }
    }

    // 动态规划
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    static class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int[] dp = new int[nums.length];
            int max = dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                // 若dp[i-1]小于0，则dp[i]加上前面的任意长度的序列和都会小于不加前面的序列（即自己本身一个元素是以自己为结尾的最大自序和）
                if (dp[i - 1] < 0) {
                    dp[i] = nums[i];
                } else {
                    dp[i] = dp[i - 1] + nums[i];
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    // 分治法求解(分治法不是最优算法)
    // 时间复杂度： O(NlogN)
    // 空间复杂度： O(logN) 递归
    static class Solution3 {

        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            return maxSubArray(nums, 0, nums.length - 1);
        }

        // [left,right]范围最大子序列和
        public int maxSubArray(int[] nums, int left, int right) {
            if (left == right) {
                return nums[left];
            }
            int middle = (left + right) / 2;
            // 左半区最大子序列和
            int leftMaxSub = maxSubArray(nums, left, middle);
            // 右半区最大子序列和
            int rightMaxSub = maxSubArray(nums, middle + 1, right);
            // 横跨左右半区最大子序列和
            int crossMaxSub = maxCrossSubArray(nums, left, middle, right);
            return Math.max(Math.max(leftMaxSub, rightMaxSub), crossMaxSub);
        }

        // 横跨左右半区最大子序列和
        public int maxCrossSubArray(int[] nums, int left, int middle, int right) {
            int leftSum = 0;
            int leftMaxSum = Integer.MIN_VALUE;
            for (int i = middle; i >= left; i--) {
                leftSum += nums[i];
                if (leftMaxSum < leftSum) {
                    leftMaxSum = leftSum;
                }
            }
            int rightSum = 0;
            int rightMaxSum = Integer.MIN_VALUE;
            for (int i = middle + 1; i <= right; i++) {
                rightSum += nums[i];
                if (rightMaxSum < rightSum) {
                    rightMaxSum = rightSum;
                }
            }
            return leftMaxSum + rightMaxSum;
        }
    }

}
