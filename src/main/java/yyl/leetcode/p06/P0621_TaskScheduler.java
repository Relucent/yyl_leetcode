package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>任务调度器</h3><br>
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。<br>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。<br>
 * 你需要计算完成所有任务所需要的 最短时间 。<br>
 * 
 * <pre>
 *示例 1：
 *输入：tasks = ["A","A","A","B","B","B"], n = 2
 *输出：8
 *解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 *     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
 *
 *示例 2：
 *输入：tasks = ["A","A","A","B","B","B"], n = 0
 *输出：6
 *解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 *["A","A","A","B","B","B"]
 *["A","B","A","B","A","B"]
 *["B","B","B","A","A","A"]
 *...
 *诸如此类
 *
 *示例 3：
 *输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 *输出：16
 *解释：一种可能的解决方案是：
 *     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= task.length <= 104<br>
 * ├ tasks[i] 是大写英文字母<br>
 * └ n 的取值范围为 [0, 100]<br>
 */
public class P0621_TaskScheduler {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(8, solution.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2));
        Assert.assertEquals(6, solution.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 0));
        Assert.assertEquals(16, solution.leastInterval(new char[] { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' }, 2));
    }

    // 数学法
    // 首先考虑所有任务种类中执行次数最多的那一种，记它为 A，的执行次数为 maxExec。
    // 可以使得总时间最小的排布方法，对应的总时间为：(maxExec−1)(n+1)+1。
    // 如果还有其它也需要执行 maxExec 次的任务需要将它们依次排布成列 +1。
    // <----- 宽为 n+1的矩阵 ---->
    // ABCDFG#
    // ABCDEF#
    // ABCDEF#
    // ABCDEFG
    // ABCXXXX
    // 如果需要执行 maxExec数量为 maxCount，那么类似地可以得到对应的总时间为：
    // (maxExec−1)(n+1)+maxCount
    // 如果列数超过了 n+1，那么就算没有这些待命状态，任意两个相邻任务的执行间隔肯定也会至少为 n。
    // 执行的时间不会少于务的总数 |task|
    // 所以执行时间为： max((maxExec−1)(n+1)+maxCount,_len(task))
    // 时间复杂度：O(|task|+|Σ|)，其中 |Σ|是数组 task 中出现任务的种类，在本题中|Σ|为26。
    // 空间复杂度：O|∣Σ|)。
    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            // 每种任务的数量
            int[] freq = new int[26];
            // 最多的执行次数
            int maxExec = 0;
            for (char task : tasks) {
                maxExec = Math.max(maxExec, ++freq[task - 'A']);
            }
            // 具有最多执行次数的任务数量
            int maxCount = 0;
            for (int count : freq) {
                if (count == maxExec) {
                    maxCount++;
                }
            }
            return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
        }
    }
}
