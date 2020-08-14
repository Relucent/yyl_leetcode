package yyl.leetcode.p01;

/**
 * <h3>打家劫舍</h3> <br>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。<br>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。 
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 提示：
 *     0 <= nums.length <= 100
 *     0 <= nums[i] <= 400
 * </pre>
 */
public class P0198_HouseRobber {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.rob(new int[] { 1, 2, 3, 1 }));
        System.out.println(solution.rob(new int[] { 2, 7, 9, 3, 1 }));
    }

    // 动态规划
    // 思路
    // 如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。
    // 如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
    // 如果房屋数量大于两间，有两个选项：
    // 1、偷窃第 i 间房屋，那么就不能偷窃第 i-1间房屋，偷窃总金额为前 i-1间房屋的最高总金额与第 i间房屋的金额之和。
    // 2、不偷窃第 i 间房屋，偷窃总金额为前 i-1间房屋的最高总金额。
    // 用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
    // dp[i]=max(dp[i−2]+nums[i],dp[i−1])
    // 边界条件为：
    // dp[0]=nums[0] 只有一间房屋，则偷窃该房屋
    // dp[1]=max(nums[0],nums[1]) ​只有两间房屋，选择其中金额较高的房屋进行偷窃
    // 复杂度分析
    // 时间复杂度：O(n) ，其中n是数组长度。只需要对数组遍历一次。
    // 空间复杂度：O(n)
    static class Solution {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[nums.length - 1];
        }
    }

    // 动态规划+滚动数组(优化)
    // 思路
    // 每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，每个时刻只需要存储前两间房屋的最高总金额。
    // 复杂度分析
    // 时间复杂度：O(n) ，其中n是数组长度。只需要对数组遍历一次。
    // 空间复杂度：O(1)
    static class Solution1 {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int first = nums[0];
            int second = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }
}
