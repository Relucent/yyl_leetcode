package yyl.leetcode.p01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>杨辉三角</h3><br>
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。<br>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * </pre>
 */
public class P0118_PascalsTriangle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 1));
        expected.add(Arrays.asList(1, 2, 1));
        expected.add(Arrays.asList(1, 3, 3, 1));
        expected.add(Arrays.asList(1, 4, 6, 4, 1));
        List<List<Integer>> actual = solution.generate(5);
        Assert.assertEquals(expected, actual);
    }

    // 迭代(根据定义直接求解)
    // 从第二行开始，将当前行 List<Integer>对象中的首位元素跟末位元素添加整数1，首位跟末位之间的元素为与当前行元素索引相同的上一行元素值，跟此时上一行相同索引元素的前一个的和。
    // 时间复杂度：O(numRows^2)。
    // 空间复杂度：O(1)。不考虑返回值的空间占用。
    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> answer = new ArrayList<>();
            if (numRows == 0) {
                return answer;
            }
            answer.add(Arrays.asList(1));
            for (int i = 1; i < numRows; i++) {
                List<Integer> previousRow = answer.get(i - 1);
                List<Integer> currentRow = new ArrayList<>(i);
                currentRow.add(1);
                for (int j = 1; j < i; j++) {
                    currentRow.add(previousRow.get(j - 1) + previousRow.get(j));
                }
                currentRow.add(1);
                answer.add(currentRow);
            }
            return answer;
        }
    }
}
