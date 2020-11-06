package yyl.leetcode.p13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import yyl.leetcode.util.Assert;

/**
 * <h3>根据数字二进制下 1 的数目排序</h3><br>
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。<br>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。<br>
 * 请你返回排序后的数组。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * 
 * 示例 2：
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * 
 * 示例 3：
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * 
 * 示例 4：
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * 
 * 示例 5：
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 * </pre>
 * 
 * 提示： <br>
 * 1 <= arr.length <= 500 <br>
 * 0 <= arr[i] <= 10^4 <br>
 */
public class P1356_SortIntegersByTheNumberOf1Bits {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 0, 1, 2, 4, 8, 3, 5, 6, 7 }, //
                solution.sortByBits(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }));
        Assert.assertEquals(new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 }, //
                solution.sortByBits(new int[] { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 }));
        Assert.assertEquals(new int[] { 10000, 10000 }, //
                solution.sortByBits(new int[] { 10000, 10000 }));
        Assert.assertEquals(new int[] { 2, 3, 5, 17, 7, 11, 13, 19 }, //
                solution.sortByBits(new int[] { 2, 3, 5, 7, 11, 13, 17, 19 }));
        Assert.assertEquals(new int[] { 10, 100, 10000, 1000 }, //
                solution.sortByBits(new int[] { 10, 100, 1000, 10000 }));
    }

    // 位运算 + 排序规则
    // 对每个十进制的数转二进制的时候统计一下 1 的个数，然后调用系统自带的排序函数，然后改写排序规则即可。
    // 时间复杂度：O(nlog⁡{n})，其中 n 为整数数组 arr 的长度。
    // 空间复杂度：O(n)，其中 n 为整数数组 arr 的长度。
    static class Solution {
        public int[] sortByBits(int[] arr) {
            List<Integer> list = new ArrayList<>();
            int[] bit = new int[10001];
            for (int num : arr) {
                bit[num] = Integer.bitCount(num);
                list.add(num);
            }
            Collections.sort(list, (x, y) -> {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            });
            int[] answer = new int[arr.length];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }

    // 递推预处理
    // 预先计算[0,10000]范围内的 每个数的bit1个数，定义 bit[i] 为数字 i 二进制表示下数字 1 的个数，则可以列出递推式： bit[i]=bit[i>>1]+(i&1)
    // 线性预处理 bit 数组然后去排序即可。
    // 时间复杂度：O(nlog⁡n)，其中 n 为整数数组 arr 的长度。
    // 空间复杂度：O(n)，其中 n 为整数数组 arr 的长度。
    static class Solution1 {
        public int[] sortByBits(int[] arr) {
            int[] bit = new int[10001];
            for (int i = 1; i <= 10000; ++i) {
                bit[i] = bit[i >> 1] + (i & 1);
            }
            return IntStream.of(arr).boxed().sorted((x, y) -> {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }).mapToInt(Integer::intValue).toArray();

        }
    }

    // 桶子法
    // 创建32个桶，分别存储不同bit1的数字
    // 时间复杂度：O(nlog⁡n)， 快速排序的时间复杂度O(nlog⁡n)，遍历数组的时间复杂度 O(n)。
    // 空间复杂度：O(n)。
    static class Solution2 {
        public int[] sortByBits(int[] arr) {
            @SuppressWarnings("unchecked")
            List<Integer>[] buckets = new ArrayList[32];
            Arrays.sort(arr);
            for (int num : arr) {
                int count = Integer.bitCount(num);
                if (buckets[count] == null) {
                    buckets[count] = new ArrayList<Integer>();
                }
                buckets[count].add(num);
            }
            int[] answer = new int[arr.length];
            int index = 0;
            for (List<Integer> bucket : buckets) {
                if (bucket != null) {
                    for (Integer val : bucket) {
                        answer[index++] = val;
                    }
                }
            }
            return answer;
        }
    }

    // 转换法
    // 根据题意，数字范围为[0,1000]，而int类型最多32位，所以可以对原始数据进行处理
    // 先根据 1的个数 和 当前数值，生成一个新的数字，来 存储 每一个数字 的 1的个数 和 本身的值
    // 接下来，将 存储的数字，还原成最初始的数字，并根据 1的个数 和 当前数值 排序
    // 时间复杂度：O(nlog⁡n)。
    // 空间复杂度：O(n)。
    static class Solution3 {
        public int[] sortByBits(int[] arr) {
            long[] stand = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                stand[i] = arr[i] + Integer.bitCount(arr[i]) * 100000;
            }
            Arrays.sort(stand);
            int[] answer = new int[stand.length];
            for (int i = 0; i < stand.length; i++) {
                answer[i] = (int) (stand[i] % 100000);
            }
            return answer;
        }
    }
}
