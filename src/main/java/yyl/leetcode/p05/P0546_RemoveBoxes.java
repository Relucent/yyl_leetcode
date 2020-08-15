package yyl.leetcode.p05;

import yyl.leetcode.util.Assert;

/**
 * <h3>移除盒子</h3><br>
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。<br>
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。<br>
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。<br>
 * 
 * <pre>
 * 示例：
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1] 
 * (移除连续的 3个2，得 3*3=9 分) ----> [1, 3, 3, 4, 3, 1] 
 * (移除连续的 1个4，得 1*1=1 分) ----> [1, 3, 3, 3, 1]
 * (移除连续的 3个3，得 3*3=9 分) ----> [1, 1]             
 * (移除连续的 2个1，得 2*2=4 分) ----> []
 * </pre>
 * 
 * 提示：<br>
 * 1 <= boxes.length <= 100<br>
 * 1 <= boxes[i] <= 100<br>
 */
public class P0546_RemoveBoxes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] boxes = { 1, 3, 2, 2, 2, 3, 4, 3, 1 };
        Assert.assertEquals(23, solution.removeBoxes(boxes));
    }

    // 动态规划+记忆搜索
    // 思路与算法
    // 并不能直接使用起始节点和结束节点决定最大分数，因为这个分数并不只依赖于子序列，也依赖于之前的移动对当前数组的影响，这可能让最终的子序列不是一个连续的子串。
    // 比如 {3,4,2,4,4}，最优的解法是先移除 2，这样3个 4会合并在一起，对答案的贡献是 3^2=9
    // 题解：用 f(l,r,k) 表示移除区间 [l,r] 加上该区间右边等于a[r]的 k 个元素组成的这个序列的最大积分。
    // 例如序列 {6,3,6,5,6,7,6,6,8,6}，l=1（下标从 1 开始），r=5，那么 f(l,r,3) 对应的元素就是 {[<6>,<3>,<6>,<5>,<6>],7,<6>,<6>,<8>,<6>} 中标记为<>的部分。
    // f(l,r,k) 的定义是移除这个红色的序列获得的最大积分。
    // （请注意此时我们约定 7 和 8 已经先被移除，所以在这个状态下我们可以认为最后四个 6 是连续的，也就是说实际上序列是这样的：{[6,3,6,5,6],6,6,6}）
    // 此时我们可以有这样一些策略来移除盒子：
    // ├ {[6,3,6,5,6],6,6,6}，删除后面的四个 6 ，再删除前面的这个区间，这样获得的分数为 f(1,4,0)+4^2
    // ├ {[6,3,6],[5],6,6,6,6}，删除一个 5，然后后面的 5个6一起删除，再删除前面的6、3，这样获得的分数是 f(1,3,4)+f(4,4,0)
    // └ {[6],[3,6,5],6,6,6,6}，删除 3、5、5之后再删除 5个6，这样获得的分数是 f(1,1,4)+f(2,4,0)
    // 时间复杂度：O(n^4)。最坏情况下每个 f(l,r,k) 被计算一次，每次状态转移需要 O(n) 的时间复杂度。
    // 空间复杂度：O(n^3)。dp数组的空间代价是 O(n3)，递归使用栈空间的代价为 O(n)。
    static class Solution {

        public int removeBoxes(int[] boxes) {
            int[][][] dp = new int[100][100][100];
            return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
        }

        public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
            // 左右边界反转了，只能拿到0分
            if (l > r) {
                return 0;
            }
            // 最少也会有一个盒子，获得积分为1。 如果dp数组中该位置不为0,则表明已经计算过了，可以直接使用，不用重新计算。
            if (dp[l][r][k] != 0) {
                return dp[l][r][k];
            }
            // 查找末尾重复盒子的个数，r是最后一个相同颜色的盒子
            while (l < r && boxes[r] == boxes[r - 1]) {
                r--;
                k++;
            }
            // 计算初始值（需要算上当前r，所以r+1）
            dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
            // i<r 即可，,等于的状态已经通过计算初始值计算出来了
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
                }
            }
            return dp[l][r][k];
        }
    }

    // 暴力法
    // 穷举所有可能（n>10的情况，会超时）
    // 时间复杂度：O(n!)
    // 空间复杂度：O(n!)
    static class Solution1 {
        public int removeBoxes(int[] boxes) {
            if (boxes.length == 0) {
                return 0;
            }
            int result = 0;
            for (int i = 0; i < boxes.length; i++) {
                // 获得连续的数字个数
                int j = i + 1;
                while (j < boxes.length && boxes[i] == boxes[j]) {
                    j++;
                }
                // 移除 [i,) 得分
                int score = (j - i) * (j - i);
                // 新数组
                int[] newboxes = new int[boxes.length - (j - i)];
                System.arraycopy(boxes, 0, newboxes, 0, i);
                System.arraycopy(boxes, j, newboxes, i, boxes.length - j);
                result = Math.max(result, removeBoxes(newboxes) + score);
            }
            return result;
        }
    }
}
