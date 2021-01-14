package yyl.leetcode.p10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>可被 5 整除的二进制前缀</h3><br>
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。<br>
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * 
 * 示例 2：
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * 
 * 示例 3：
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * 
 * 示例 4：
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= A.length <= 30000 <br>
 * └ A[i] 为 0 或 1 <br>
 */
public class P1018_BinaryPrefixDivisibleBy5 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList(true, false, false), solution.prefixesDivBy5(new int[] { 0, 1, 1 }));
        Assert.assertEquals(Arrays.asList(false, false, false), solution.prefixesDivBy5(new int[] { 1, 1, 1 }));
        Assert.assertEquals(Arrays.asList(true, false, false, false, true, false), solution.prefixesDivBy5(new int[] { 0, 1, 1, 1, 1, 1 }));
        Assert.assertEquals(Arrays.asList(false, false, false, false, false), solution.prefixesDivBy5(new int[] { 1, 1, 1, 0, 1 }));
    }

    // 模拟
    // 依次计算每个 Ni 的值。对于每个 Ni 的值，判断其是否可以被 5 整除，即可得到答案。
    // 考虑到数组 A 可能很长，如果每次都保留 Ni 的值，则可能导致溢出。
    // 由于只需要知道每个 Ni 是否可以被 5 整除，如果被5整除，十进制的个位一定是0或者5，所以在计算过程中只需要保留个位数即可。
    // 时间复杂度：O(n)，其中 n 是数组 A 的长度，需要遍历一次数组。
    // 空间复杂度：O(1)。额外使用的空间为常数。
    static class Solution {
        public List<Boolean> prefixesDivBy5(int[] A) {
            List<Boolean> answer = new ArrayList<>(A.length);
            int ones = 0;
            for (int x : A) {
                ones = (ones * 2 + x) % 10;
                answer.add((ones == 0 || ones == 5) ? Boolean.TRUE : Boolean.FALSE);
            }
            return answer;
        }
    }

    // 模拟（取模）
    // 依次计算每个 Ni 的值。对于每个 Ni 的值，判断其是否可以被 5 整除，即可得到答案。
    // 由于只需要知道每个 Ni 是否可以被 5 整除，因此在计算过程中只需要保留余数即可。
    // 时间复杂度：O(n)，其中 n 是数组 A 的长度，需要遍历一次数组。
    // 空间复杂度：O(1)。额外使用的空间为常数。
    static class Solution1 {
        public List<Boolean> prefixesDivBy5(int[] A) {
            List<Boolean> answer = new ArrayList<>(A.length);
            int mod = 0;
            for (int x : A) {
                mod = (mod * 2 + x) % 5;
                answer.add(mod == 0 ? Boolean.TRUE : Boolean.FALSE);
            }
            return answer;
        }
    }
}
