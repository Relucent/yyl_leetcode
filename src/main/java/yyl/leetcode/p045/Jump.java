package yyl.leetcode.p045;

/**
 * <h3>跳跃游戏 II</h3><br>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。<br>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。<br>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。<br>
 * 示例:<br>
 * 输入: [2,3,1,1,4]<br>
 * 输出: 2<br>
 * 解释: 跳到最后一个位置的最小跳跃数是 2。<br>
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。<br>
 * 说明:<br>
 * 假设你总是可以到达数组的最后一个位置。<br>
 */
public class Jump {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println(solution.jump(new int[] {2, 3, 1, 1, 4}));// 2
        // System.out.println(solution.jump(new int[] {3, 2, 1}));// 1
        System.out.println(solution.jump(new int[] {9, 7, 9, 4, 8, 1, 6, 1, 5, 6, 2, 1, 7, 9, 0}));// 2
    }

    // 题解说明
    // # 索引:___________|__0|__1|__2|__4|...|__n|
    // # 数字:___________|__a|__b|__c|__d|...|__m|
    // # 能跳到的下一个位置引:|0+a|1+b|2+c|3+d|...|n+m|
    // 当前位置，能跳到的索引为 [(当前位置+1),(当前位置+当前位置数字)]
    // 在这个范围内，下一个位置应该选择索引位置+索引数字)最大的，也就是跳的最远的

    static class Solution {

        // 这个算法的时间复杂度是 O(N)
        public int jump(int[] nums) {

            // 当前跳跃位置
            int offset = 0;
            // 跳跃数量记数
            int count = 0;

            // 最后一个位置是 nums.length - 1
            while (offset < nums.length - 1) {

                count++;

                // 当前位置可以直接跳到末尾
                if (offset + nums[offset] >= nums.length - 1) {
                    return count;
                }

                int next = -1;// 下次的位置
                int nextPower = -1;// 下一步可到达的最远位置

                // 当前位置下次可以跳到的最大位置是 Math.min(nums.length-1,offset + nums[offset])
                for (int i = offset + 1, j = Math.min(nums.length - 1, offset + nums[offset]); i <= j; i++) {
                    int power = i + nums[i];
                    if (power > nextPower) {
                        nextPower = power;
                        next = i;
                    }
                }

                // 永远跳不到最后的情况 (因为题目假设总是可以到达数组的最后一个位置，所以这个判断也可以省掉)
                if (next == -1) {
                    return -1;
                }
                offset = next;
            }
            return count;
        }
    }
}
