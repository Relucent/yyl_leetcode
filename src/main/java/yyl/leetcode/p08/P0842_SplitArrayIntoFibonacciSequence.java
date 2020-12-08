package yyl.leetcode.p08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>将数组拆分成斐波那契序列</h3><br>
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。<br>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：<br>
 * ├ 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；<br>
 * ├ F.length >= 3；<br>
 * └ 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。<br>
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。<br>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入："123456579"
 * 输出：[123,456,579]
 * 
 * 示例 2：
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 
 * 示例 3：
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 
 * 示例 4：
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 
 * 示例 5：
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= S.length <= 200<br>
 * └ 字符串 S 中只含有数字。<br>
 */
public class P0842_SplitArrayIntoFibonacciSequence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList(123, 456, 579), solution.splitIntoFibonacci("123456579"));
        Assert.assertEquals(Arrays.asList(1, 1, 2, 3, 5, 8, 13), solution.splitIntoFibonacci("11235813"));
        Assert.assertEquals(Arrays.asList(), solution.splitIntoFibonacci("112358130"));
        Assert.assertEquals(Arrays.asList(), solution.splitIntoFibonacci("0123"));
        Assert.assertEquals(Arrays.asList(11, 0, 11, 11), solution.splitIntoFibonacci("1101111"));
        Assert.assertEquals(Arrays.asList(), solution.splitIntoFibonacci("214748364721474836422147483641"));
    }

    // 回溯 + 剪枝
    // 根据斐波那契式序列的要求，从第 3 个数开始，每个数都等于前 2 个数的和，因此从第 3 个数开始，需要判断拆分出的数是否等于前 2 个数的和，只有满足要求时才进行拆分，否则不进行拆分。
    // 回溯过程中，还有三处可以进行剪枝操作：
    // ├ 拆分出的数如果不是 0，则不能以 0开头，因此如果字符串剩下的部分以 0开头，就不需要考虑拆分出长度大于 1的数；
    // ├ 拆分出的数必须符合 32 位有符号整数类型，即每个数必须在 [0,2^31−1] 的范围内；
    // └ 如果列表中至少有 2个数，并且拆分出的数已经大于最后 2 个数的和，就不需要继续尝试拆分了。
    // 当整个字符串拆分完毕时，如果列表中至少有 3 个数，则得到一个符合要求的斐波那契式序列，返回列表。
    // 如果没有找到符合要求的斐波那契式序列，则返回空列表。
    // 从第 3 个数开始，整个斐波那契数列是可以被唯一确定的，整个回溯过程只起到验证（而不是枚举）的作用。
    // 时间复杂度：O(nlog⁡2C)，其中 n 是字符串的长度，C 是题目规定的整数范围。
    // 空间复杂度：O(n)，其中 n 是字符串的长度。除了返回值以外，空间复杂度主要取决于回溯过程中的递归调用层数，最大为 n 。
    static class Solution {
        public List<Integer> splitIntoFibonacci(String S) {
            List<Integer> answer = new ArrayList<Integer>();
            backtrack(answer, S.toCharArray(), S.length(), 0, 0, 0);
            return answer;
        }

        private boolean backtrack(List<Integer> list, char[] chars, int length, int index, long sum, long previous) {
            if (index == length) {
                return list.size() >= 3;
            }

            long current = 0;
            for (int i = index; i < length; i++) {
                if (i > index && chars[index] == '0') {
                    return false;
                }
                current = current * 10 + (chars[i] - '0');
                if (current > Integer.MAX_VALUE) {
                    return false;
                }

                // 第三个数开始判断
                if (list.size() >= 2) {
                    // 小于前两个数的和，那么可以继续增加位数
                    if (current < sum) {
                        continue;
                    }
                    // 大于前两个数的和，继续增加位数更大了，所以直接返回
                    else if (current > sum) {
                        return false;
                    }
                    // else current == sum
                }

                list.add((int) current);
                if (backtrack(list, chars, length, i + 1, previous + current, current)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
            return false;
        }
    }
}
