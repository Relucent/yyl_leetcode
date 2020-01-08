package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>颜色分类</h3><br>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。<br>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。<br>
 * 注意:<br>
 * 不能使用代码库中的排序函数来解决这道题。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]进阶：
 * </pre>
 * 
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。<br>
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。<br>
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？<br>
 * </pre>
 */
public class P0075_SortColors {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sample = {2, 0, 2, 1, 1, 0};
        solution.sortColors(sample);
        System.out.println(Arrays.toString(sample));
    }

    // 双指针扫描法
    // 因为元素只有0,1,2三种，所以排序后数组会分为三个区域
    // 1. 数字0统一放在最左区域，在左指针的左边。
    // 2. 数字1统一放在中间区域，在左、右指针之间。
    // 3. 数字2统一放在最右区域，在右指针的右边。
    // 进行扫描
    // 1. 如果遇到数字0，将0与左指针数值交换，左指针++，扫描指针++
    // 2. 如果遇到数字1，扫描指针++ (左右指针不需要变化，因为最后左右指针之间的数就是1)
    // 3. 如果遇到数字2，将2与右指针数值交换，右指针--(因为右指针数值可能为0，这时候还需要继续交换，所以扫描指针不变)
    // 时间复杂度：O(N)
    // 空间复杂度：O(1)
    static class Solution {
        public void sortColors(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            for (int i = 0; i <= right;) {
                // 遇到0：交换数据，左指针++，扫描指针++
                if (nums[i] == 0) {
                    swap(nums, i++, left++);
                }
                // 遇到1：扫描指针++
                else if (nums[i] == 1) {
                    i++;
                }
                // 遇到2：交换数据，右指针--
                else if (nums[i] == 2) {
                    swap(nums, i, right--);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    // 计数排序
    // 时间复杂度：O(N)，由于不需要复杂的判断，虽然时间复杂度一样，但是实际性能比双指针扫描法要快
    // 空间复杂度：O(1)，只有3个元素，常数级辅助空间
    static class Solution2 {
        public void sortColors(int[] nums) {
            int[] counts = new int[3];
            for (int i = 0; i < nums.length; i++) {
                counts[nums[i]]++;
            }
            Arrays.fill(nums, 0, counts[0], 0);
            Arrays.fill(nums, counts[0], counts[0] + counts[1], 1);
            Arrays.fill(nums, counts[0] + counts[1], nums.length, 2);
        }
    }
}
