package yyl.leetcode.p14;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>并行课程 II</h3><br>
 * 给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 dependencies 中， dependencies[i] = [xi, yi] 表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。<br>
 * 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。<br>
 * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
 * 输出：3 
 * (2) ┐
 *     ├─> (1) ─> (4)
 * (3) ┘
 * 解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
 * 
 * 示例 2：
 * 输入：n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * 输出：4 
 * (2) ┐
 *     │
 * (3) ┼─> (1) ─> (5)
 *     │
 * (4) ┘
 * 解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
 * 
 * 示例 3： * 
 * 输入：n = 11, dependencies = [], k = 2
 * 输出：6
 * 
 * 提示：
 * 
 *     1 <= n <= 15
 *     1 <= k <= n
 *     0 <= dependencies.length <= n * (n-1) / 2
 *     dependencies[i].length == 2
 *     1 <= xi, yi <= n
 *     xi != yi
 *     所有先修关系都是不同的，也就是说 dependencies[i] != dependencies[j] 。
 *     题目输入的图是个有向无环图。
 * </pre>
 */
public class P1494_ParallelCoursesII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minNumberOfSemesters(4, new int[][] { { 2, 1 }, { 3, 1 }, { 1, 4 } }, 2));// 3
        System.out.println(solution.minNumberOfSemesters(5, new int[][] { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 1, 5 } }, 2));// 4
        System.out.println(solution.minNumberOfSemesters(8, new int[][] {}, 2));// 4
        System.out.println(solution.minNumberOfSemesters(10, new int[][] {}, 2));// 5
        System.out.println(solution.minNumberOfSemesters(11, new int[][] {}, 2));// 6
        System.out.println(solution.minNumberOfSemesters(16, new int[][] {}, 2));// 8
        System.out.println(solution.minNumberOfSemesters(5, new int[][] { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 1, 5 } }, 2));// 4
    }

    // 暴力 + 位压 + 优化
    static class Solution {
        public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
            AtomicInteger result = new AtomicInteger(Integer.MAX_VALUE);

            // 全部课程（用二进制表示，例如16个课程就是 1111111111111111）
            int mAll = 0;
            for (int i = 0; i < n; i++) {
                mAll |= (1 << i);
            }
            // 开头的课程（不依赖其他课程）
            int mHead = mAll;
            // 课程依赖前置
            int[] pres = new int[n];
            // 剩余课程（只记录带关联的）
            int mSurplus = 0;
            for (int[] dep : dependencies) {
                int px = dep[0] - 1;
                int py = dep[1] - 1;
                int bx = 1 << px;
                int by = 1 << py;
                pres[py] |= bx;
                mSurplus |= (bx | by);
                mHead &= (mHead ^ by);
            }
            // 孤岛课程数
            int isolated = Integer.bitCount(mAll) - Integer.bitCount(mSurplus);

            // 后续可选课程，options[i]的i是一种状态
            // 二进制 001表示第1个课程被选; 011 表示第1第2个课程被选（用二进制的位表示已选课程）
            int[] options = new int[(int) Math.pow(2, n)];
            for (int state = 0; state < (int) Math.pow(2, n); state++) {
                for (int p = 0; p < n; p++) {
                    // 比较，看state是否包含了pres[p]
                    if ((pres[p] & (state ^ pres[p])) == 0) {
                        options[state] |= (1 << p);
                    }
                }
                options[state] |= mHead;
            }
            dfs(0, mSurplus, options, isolated, k, 0, result);
            return result.get();
        }

        private void dfs(int mUsed, int mSurplus, int[] options, int isolated, int k, int depth, AtomicInteger result) {

            if (mSurplus == 0) {
                result.set(Math.min(result.get(), depth + (isolated % k == 0 ? isolated / k : isolated / k + 1)));
                return;
            }

            // 可选课程（可选并且未选的）
            int option = options[mUsed] & mSurplus;

            // k 问题（任选k个）
            int b = Integer.bitCount(option);
            if (b > k) {
                // 求掩码
                for (int next = option; next > 0; next = (next - 1) & option) {
                    if (Integer.bitCount(next) == k) {
                        dfs(mUsed | next, mSurplus & (mSurplus ^ next), options, isolated, k, depth + 1, result);
                    }
                }
            } else {
                if (isolated > 0) {
                    isolated = Math.max(isolated - (k - b), 0);
                }
                dfs(mUsed | option, mSurplus & (mSurplus ^ option), options, isolated, k, depth + 1, result);
            }
        }
    }
}
