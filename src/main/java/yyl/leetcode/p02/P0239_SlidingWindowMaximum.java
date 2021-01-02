package yyl.leetcode.p02;

import java.util.ArrayDeque;
import java.util.Deque;

import yyl.leetcode.util.Assert;

/**
 * <h3>滑动窗口最大值</h3><br>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。<br>
 * 返回滑动窗口中的最大值。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= nums.length <= 105<br>
 * ├ -104 <= nums[i] <= 104<br>
 * └ 1 <= k <= nums.length<br>
 */

public class P0239_SlidingWindowMaximum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 3, 3, 5, 5, 6, 7 }, solution.maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3));
        Assert.assertEquals(new int[] { 1 }, solution.maxSlidingWindow(new int[] { 1 }, 1));
        Assert.assertEquals(new int[] { 1, -1 }, solution.maxSlidingWindow(new int[] { 1, -1 }, 1));
        Assert.assertEquals(new int[] { 11 }, solution.maxSlidingWindow(new int[] { 9, 11 }, 2));
        Assert.assertEquals(new int[] { 4 }, solution.maxSlidingWindow(new int[] { 4, -2 }, 2));
        Assert.assertEquals(new int[] { 7, 4 }, solution.maxSlidingWindow(new int[] { 7, 2, 4 }, 2));
    }

    // 单调队列
    // 思路
    // ├由于需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j ，其中 i 在 j 的左侧（i<j），并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]），那么
    // ├ 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。
    // ├ 因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除。
    // └ 因此我们可以使用一个队列存储所有还没有被移除的下标。在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。
    // 算法
    // ├ 遍历数组，当滑动窗口向右移动时，把一个新的元素放入队列中。
    // ├ 为了保持队列的性质，将新的元素与队尾的元素相比较，如果前者大于等于后者，那么队尾的元素就可以被永久地移除，将其弹出队列。
    // ├ └ 需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
    // └ 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
    // 为了可以同时弹出队首和队尾的元素，需要使用双端队列，满足这种单调性的双端队列一般称作「单调队列」。
    // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。每一个下标恰好被放入队列一次，并且最多被弹出队列一次，因此时间复杂度为 O(n)。
    // 空间复杂度：O(k)。使用的数据结构是双向的，因此「不断从队首弹出元素」保证了队列中最多不会有超过 k+1 个元素，因此队列使用的空间为 O(k)。
    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (k < 2) {
                return nums;
            }
            int[] answer = new int[nums.length - (k - 1)];
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                if (i >= (k - 1)) {
                    answer[i - (k - 1)] = nums[deque.peekFirst()];
                }
            }
            return answer;
        }
    }

    // 暴力法
    // 循环遍历，每次计算滑动窗口最大值，该方法时间复杂度较高，解题时会超出时间限制。
    // 时间复杂度：O(nk)，n 是 nums数组长度， k 是的滑动窗口尺寸。
    // 空间复杂度：O(n+1-k)
    static class Solution1 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] answer = new int[n + 1 - k];
            for (int i = 0; i < answer.length; i++) {
                int max = nums[i];
                for (int j = 1; j < k; j++) {
                    max = Math.max(max, nums[i + j]);
                }
                answer[i] = max;
            }
            return answer;
        }
    }
}
