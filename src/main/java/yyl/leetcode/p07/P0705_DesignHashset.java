package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>设计哈希集合</h3><br>
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。v 实现 MyHashSet 类：<br>
 * ├ void add(key) 向哈希集合中插入值 key 。<br>
 * ├ bool contains(key) 返回哈希集合中是否存在这个值 key 。<br>
 * └ void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。<br>
 * 
 * <pre>
 * 示例：
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * 
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * </pre>
 * 
 * 提示：<br>
 * ├ 0 <= key <= 10^6<br>
 * └ 最多调用 104 次 add、remove 和 contains 。<br>
 */
// Your MyHashSet object will be instantiated and called as such:
// MyHashSet obj = new MyHashSet();
// obj.add(key);
// obj.remove(key);
// boolean param_3 = obj.contains(key);
public class P0705_DesignHashset {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1); // set = [1]
        myHashSet.add(2); // set = [1, 2]
        Assert.assertTrue(myHashSet.contains(1)); // 返回 True
        Assert.assertFalse(myHashSet.contains(3)); // 返回 False ，（未找到）
        myHashSet.add(2); // set = [1, 2]
        Assert.assertTrue(myHashSet.contains(2)); // 返回 True
        myHashSet.remove(2); // set = [1]
        Assert.assertFalse(myHashSet.contains(2)); // 返回 False ，（已移除）
    }

    // 二进制+分桶数组解法
    // 实现一个类似「bitmap」数据结构，由于数据范围为 0 <= key <= 10^6，最多需要的 int 数量不会超过 40000 （32 * 40000 = 1280000 > 1000000)
    // 因此我们可以建立一个 buckets 数组，数组装载的 int 类型数值。
    // 先对 key 进行 key / 32，确定当前 key 所在桶的位置
    // 再对 key 进行 key % 32，确定当前 key 所在桶中的哪一位
    // 时间复杂度：O(1)
    // 空间复杂度：O(1)
    static class MyHashSet {

        private final int[] buckets;

        /** Initialize your data structure here. */
        public MyHashSet() {
            buckets = new int[40000];
        }

        public void add(int key) {
            setValue(key, true);
        }

        public void remove(int key) {
            setValue(key, false);
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return getValue(key);
        }

        private void setValue(int key, boolean value) {
            int bucketIdx = key / 32;
            int bitIdx = key % 32;
            buckets[bucketIdx] = value ? (buckets[bucketIdx] | (1 << bitIdx)) : (buckets[bucketIdx] & ~(1 << bitIdx));
        }

        private boolean getValue(int key) {
            int bucketIdx = key / 32;
            int bitIdx = key % 32;
            return ((buckets[bucketIdx] >> bitIdx) & 1) == 1;
        }
    }

    // 简单数组解法
    // 由于题目给出了 0 <= key <= 10^6 数据范围，同时限定了 key 只能是 int。
    // 我们可以直接使用一个 Boolean 数组记录某个 key 是否存在，key 直接对应 Boolean 的下标
    // 时间复杂度：O(1)
    // 空间复杂度：O(1)
    class MyHashSet1 {

        private final boolean[] nodes;

        public MyHashSet1() {
            nodes = new boolean[1000009];
        }

        public void add(int key) {
            nodes[key] = true;
        }

        public void remove(int key) {
            nodes[key] = false;
        }

        public boolean contains(int key) {
            return nodes[key];
        }
    }
}
