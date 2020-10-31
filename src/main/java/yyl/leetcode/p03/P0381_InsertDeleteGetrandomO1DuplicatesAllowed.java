package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h3>O(1) 时间插入、删除和获取随机元素 - 允许重复</h3><br>
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。<br>
 * 注意: 允许出现重复元素。<br>
 * 1、 insert(val)：向集合中插入元素 val。<br>
 * 2、 remove(val)：当 val 存在时，从集合中移除一个 val。<br>
 * 3、 getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。<br>
 * 
 * <pre>
 * 示例:
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * 
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * 
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * 
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * 
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * 
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * 
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 * </pre>
 */
public class P0381_InsertDeleteGetrandomO1DuplicatesAllowed {

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        obj.insert(1);
        obj.insert(1);
        obj.insert(2);
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(1));
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
    }

    // 哈希表
    // 使用哈希表存储元素的索引集合，存储、获取、删除索引的时间复杂度为O(1)
    // 列表中的随机删除并不是 O(1)的，但是删除最后元素可以达到 O(1)，因此，在删除元素时，可以将被删的元素与列表中最后一个元素交换位置，随后便可以在 O(1) 时间内，从列表中去除该元素。
    // 时间复杂度：O(1)。
    // 空间复杂度：O(N)，其中 N 为集合中的元素数目。

    static class RandomizedCollection {

        private final Map<Integer, Set<Integer>> idx;
        private final List<Integer> values;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            idx = new HashMap<>();
            values = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            values.add(val);
            Set<Integer> set = idx.get(val);
            if (set == null) {
                idx.put(val, set = new HashSet<>());
            }
            set.add(values.size() - 1);
            return set.size() == 1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            Set<Integer> set = idx.get(val);
            if (set == null) {
                return false;
            }
            int lastIndex = values.size() - 1;
            int lastValue = values.get(lastIndex);
            Set<Integer> lastSet = idx.get(lastValue);
            int index = set.iterator().next();
            values.set(index, lastValue);
            set.remove(index);
            lastSet.remove(lastIndex);
            values.remove(lastIndex);
            if (index != lastIndex) {
                lastSet.add(index);
            }
            if (set.isEmpty()) {
                idx.remove(val);
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            int size = values.size();
            if (size == 0) {
                return -1;
            }
            return values.get(ThreadLocalRandom.current().nextInt(size));
        }
    }
}
