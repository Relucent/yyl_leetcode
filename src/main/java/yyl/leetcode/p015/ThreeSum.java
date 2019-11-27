package yyl.leetcode.p015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>三数之和</h3> <br>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。<br>
 * 注意：答案中不可以包含重复的三元组。<br>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，<br>
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ] <br>
 * <br>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.<br>
 * Note: The solution set must not contain duplicate triplets.<br>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],<br>
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    static class Solution {

        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();

            // 不够三个数
            if (nums.length < 3) {
                return result;
            }

            // 排序
            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {

                // 去重: 这个数字和前面的一样直接忽略
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int target = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 两个数的和
                    int sum = nums[left] + nums[right];

                    // 找到结果
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 去重
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                    // 和比目标大，那么右侧左移动
                    else if (sum > target) {
                        right--;
                    }
                    // 和比目标小，那么左侧左移动
                    else { // sum < target
                        left++;
                    }
                }
            }
            return result;
        }
    }
}
