package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h3>计算右侧小于当前元素的个数</h3><br>
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： <br>
 * counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0] 
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 * </pre>
 */
public class P0315_CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 5, 2, 6, 1 };
        System.out.println(solution.countSmaller(nums));// 2,1,1,0
    }

    // 插入排序+查找
    // 创建一个排序的列表，从后向前遍历将nums[i]插入到列表中，插入点位置之就是小于nums[i]的元素的数量
    // 时间复杂度 : O(N^2)，遍历的时间复杂度O(N)，插入排序的时间复杂度 O(N)~O(N^2)
    // 空间复杂度 : O(N)
    static class Solution {
        public List<Integer> countSmaller(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            List<Integer> sortedList = new ArrayList<>();
            Integer[] answer = new Integer[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                answer[i] = insert(sortedList, nums[i]);
            }
            return Arrays.asList(answer);
        }

        private int insert(List<Integer> sortedList, int num) {
            int left = 0;
            int right = sortedList.size();// 注意不需要 -1，因为后面取left
            while (left < right) {
                int mid = (left + right) / 2;
                if (sortedList.get(mid) >= num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            sortedList.add(left, num);
            return left;
        }
    }

    // 暴力法
    // 暴力模拟法思路非常简单，就是每次都从末尾找比num[i]小的数并计数，然后放到结果数组即可。
    // 时间复杂度 : O(N^2)
    // 空间复杂度 : O(N)
    static class Solution1 {
        public List<Integer> countSmaller(int[] nums) {
            Integer[] answer = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int count = 0;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] > nums[j]) {
                        count++;
                    }
                }
                answer[i] = count;
            }
            return Arrays.asList(answer);
        }
    }

    // 插入排序+查找
    // 从后向前遍历nums[i]，对[i,n]部分进行排序，排序之后，插入点位置之前的元素都小于nums[i]，根据插入点可以直接计算出小于nums[i]的元素的数量
    // 时间复杂度 : O(N^2)，遍历的时间复杂度O(N)，插入排序的时间复杂度 O(N)~O(N^2)
    // 空间复杂度 : O(N)
    static class Solution2 {
        public List<Integer> countSmaller(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            Integer[] answer = new Integer[nums.length];
            answer[nums.length - 1] = 0;
            for (int i = nums.length - 2; i >= 0; i--) {
                answer[i] = sort(nums, i);
            }
            return Arrays.asList(answer);
        }

        private int sort(int[] nums, int i) {
            int item = nums[i];
            int k = nums.length - 1;
            while (k > i && nums[k] >= item) {
                k--;
            }
            for (int j = i; j < k; j++) {
                nums[j] = nums[j + 1];
            }
            nums[k] = item;
            return k - i;
        }
    }

    // 前缀桶
    // 数组中出现的数字称作「值域」，创建一个桶（数组）来表示这个「值域」，「值域」中每个数字占用一个桶。
    // 可以用这个桶来存储小于当前数的元素个数；从后向前遍历数据，并动态维护桶。
    // 通过桶可以很快的查找到小于当前元素的元素个数；
    // 因为数字可能很大，不可能直接创建一个很大的数组来，所以需要进行优化，不存在的数不放到桶里，需要一个数字和桶号的映射。
    // 暴力模拟法思路非常简单，就是每次都从末尾找比num[i]小的数并计数，然后放到结果数组即可。
    // 时间复杂度 : O(N*log{N})~O(N^2)
    // 空间复杂度 : O(N)
    static class Solution3 {
        public List<Integer> countSmaller(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num);
            }
            int[] bucket = new int[set.size()];
            int[] numToBucket = new int[set.size()];
            int index = 0;
            for (int num : set) {
                numToBucket[index++] = num;
            }
            Arrays.sort(numToBucket);

            Integer[] answer = new Integer[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                int num = nums[i];
                int bucketIndex = Arrays.binarySearch(numToBucket, num);
                int count = 0;
                for (int p = 0; p < bucketIndex; p++) {
                    count += bucket[p];
                }
                answer[i] = count;
                bucket[bucketIndex]++;
            }
            return Arrays.asList(answer);
        }
    }

    // 树状数组(官方解法)
    // # 预备知识
    // 「树状数组」是一种可以动态维护序列前缀和的数据结构，它的功能是：
    // 1、单点更新 update(i, v)： 把序列 i 位置的数加上一个值 v，本题 v=1
    // 2、区间查询 query(i)： 查询序列 [1⋯i] 区间的区间和，即 i 位置的前缀和
    // 修改和查询的时间代价都是 O(log⁡n)，其中 n 为需要维护前缀和的序列的长度。
    // 使用树状数组中可以快速求出原始数组nums中小于或等于当前index的数的和
    // 时间复杂度：O(N*log{N})，这里离散化使用哈希表去重，然后再对去重的数组进行排序，时间代价为 O(nlog{n})；初始化树状数组的时间代价是 O(n)；通过值获取离散化id的操作单次时间代价为 O(log{⁡n})；总的时间代价为 O(n*log⁡{n})
    // 空间复杂度：O(n)，树状数组的空间代价都是 O(n)
    static class Solution4 {

        public List<Integer> countSmaller(int[] nums) {

            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num);
            }
            int size = set.size();
            int[] idx = new int[size];
            int index = 0;
            for (int num : set) {
                idx[index++] = num;
            }
            Arrays.sort(idx);

            BinaryIndexedTree bit = new BinaryIndexedTree(idx.length + 5);

            Integer[] answer = new Integer[nums.length];
            for (int i = nums.length - 1; i >= 0; --i) {
                int pos = Arrays.binarySearch(idx, nums[i]) + 1;
                answer[i] = bit.query(pos - 1);
                bit.update(pos, 1);
            }
            return Arrays.asList(answer);
        }

        private class BinaryIndexedTree {

            private int[] values;

            BinaryIndexedTree(int length) {
                values = new int[length + 5];
            }

            private void update(int pos, int val) {
                while (pos < values.length) {
                    values[pos] += val;
                    pos += lowBit(pos);
                }
            }

            private int query(int pos) {
                int ret = 0;
                while (pos > 0) {
                    ret += values[pos];
                    pos -= lowBit(pos);
                }
                return ret;
            }

            private int lowBit(int x) {
                return x & (-x);
            }
        }
    }
}
