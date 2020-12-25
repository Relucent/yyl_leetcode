package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>分发糖果</h3><br>
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。<br>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：<br>
 * ├ 每个孩子至少分配到 1 个糖果。<br>
 * └ 相邻的孩子中，评分高的孩子必须获得更多的糖果。<br>
 * 那么这样下来，老师至少需要准备多少颗糖果呢？<br>
 *
 * <pre>
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * </pre>
 */
public class P0135_Candy {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(5, solution.candy(new int[] { 1, 0, 2 }));
        Assert.assertEquals(4, solution.candy(new int[] { 1, 2, 2 }));
    }

    // 两次遍历
    // 「相邻的孩子中，评分高的孩子必须获得更多的糖果」拆分为两个规则，分别处理。
    // 左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
    // 右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多。
    // 遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量，每个人最终分得的糖果数量即为这两个数量的最大值。
    // 时间复杂度：O(n)，其中 n 是孩子的数量。需要遍历两次数组以分别计算满足左规则或右规则的最少糖果数量。
    // 空间复杂度：O(n)，其中 n 是孩子的数量。需要保存所有的左规则对应的糖果数量。
    static class Solution {
        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] lefts = new int[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    lefts[i] = lefts[i - 1] + 1;
                } else {
                    lefts[i] = 1;
                }
            }
            int answer = 0;
            int[] rights = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                    rights[i] = rights[i + 1] + 1;
                } else {
                    rights[i] = 1;
                }
                answer += Math.max(lefts[i], rights[i]);
            }
            return answer;
        }
    }
}
