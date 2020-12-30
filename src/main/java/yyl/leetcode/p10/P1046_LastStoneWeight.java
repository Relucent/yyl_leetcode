package yyl.leetcode.p10;

import java.util.PriorityQueue;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>最后一块石头的重量</h3><br>
 * 有一堆石头，每块石头的重量都是正整数。<br>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：<br>
 * ├ 如果 x == y，那么两块石头都会被完全粉碎； <br>
 * └ 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 <br>
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。<br>
 * 
 * <pre>
 * 示例：
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= stones.length <= 30 <br>
 * └ 1 <= stones[i] <= 1000 <br>
 */

public class P1046_LastStoneWeight {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.lastStoneWeight(new int[] { 2, 7, 4, 1, 8, 1 }));
    }

    // 最大堆
    // 将所有石头的重量放入最大堆中。每次依次从队列中取出最重的两块石头 a 和 b ，必有 a≥b。如果 a>b ，则将新石头 a-b 放回到最大堆中；
    // 如果 a==b，两块石头完全被粉碎，因此不会产生新的石头。
    // 重复上述操作，直到剩下的石头少于 2 块。
    // 最终可能剩下 1 块石头，该石头的重量即为最大堆中剩下的元素，返回该元素；
    // 也可能没有石头剩下，此时最大堆为空，返回 0。
    // 时间复杂度：O(nlog⁡n)，其中 n 是石头数量。每次从队列中取出元素需要花费 O(log⁡n) 的时间，最多共需要粉碎 n−1 次石头。
    // 空间复杂度：O(n)。
    static class Solution {
        public int lastStoneWeight(int[] stones) {
            if (stones.length == 1) {
                return stones[0];
            }
            Queue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            for (int x : stones) {
                queue.offer(x);
            }
            while (queue.size() > 1) {
                int a = queue.poll();
                int b = queue.poll();
                if (a > b) {
                    queue.offer(a - b);
                }
            }
            return queue.isEmpty() ? 0 : queue.poll();
        }
    }
}
