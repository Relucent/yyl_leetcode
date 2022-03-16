package yyl.leetcode.p04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>全 O(1) 的数据结构</h3><br>
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。<br>
 * 实现 AllOne 类：<br>
 * AllOne() 初始化数据结构的对象。<br>
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。<br>
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。<br>
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。<br>
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。<br>
 * 
 * <pre>
 * 示例：
 * 
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * 
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 * 
 * 提示：
 *  1 <= key.length <= 10
 *  key 由小写英文字母组成
 *  测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 *  最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 10^4 次
 * </pre>
 */
public class P0432_AllOoneDataStructure {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        Assert.assertEquals("hello", allOne.getMaxKey());
        Assert.assertEquals("hello", allOne.getMinKey());
        allOne.inc("leet");
        Assert.assertEquals("hello", allOne.getMaxKey());
        Assert.assertEquals("leet", allOne.getMinKey());
        allOne.dec("hello");
        allOne.dec("hello");
        Assert.assertEquals("leet", allOne.getMaxKey());
        Assert.assertEquals("leet", allOne.getMinKey());
        allOne.dec("leet");
        Assert.assertEquals("", allOne.getMaxKey());
        Assert.assertEquals("", allOne.getMinKey());
    }

    // 哈希表+双向链表
    // 维护一个计数有序的双向链表，并且有另外一个哈希表将字符串映射到结点指针
    // 具体实现细节为：
    // 1、双向链表：按计数大小递增（递减）有序，内部存一个集合
    // 2、插入操作：若不存在，直接在链表首部插入即可；若存在，则根据哈希表定位结点取出插入下一个结点（若不存在则创建）
    // 3、删除操作：与插入操作同理，删除后为 0 则删除结点
    // 4、取计数最大、最小的字符串：在双向链表首尾取即可
    // 时间复杂度：O(1)
    private static class AllOne {

        private final Map<String, Node> map;
        private final Node head;

        public AllOne() {
            this.map = new HashMap<>();
            this.head = new Node();
            this.head.set.add("");
            this.head.count = 0;
            this.head.next = head;
            this.head.previous = head;
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node current = map.get(key);
                Node next = null;
                int count = current.count + 1;
                if (current.next.count == count) {
                    next = current.next;
                    next.set.add(key);
                } else {
                    next = new Node();
                    next.set.add(key);
                    next.previous = current;
                    next.next = current.next;
                    next.count = count;
                    current.next.previous = next;
                    current.next = next;
                }
                current.set.remove(key);
                map.put(key, next);
                if (current.set.isEmpty()) {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
            } else {
                if (head.next.count == 1) {
                    head.next.set.add(key);
                    map.put(key, head.next);
                } else {
                    Node next = new Node();
                    next.set.add(key);
                    next.previous = head;
                    next.next = head.next;
                    next.count = 1;
                    head.next.previous = next;
                    head.next = next;
                    map.put(key, next);
                }
            }
        }

        public void dec(String key) {
            Node current = map.get(key);
            if (current.count == 1) {
                current.set.remove(key);
                map.remove(key);
                if (current.set.isEmpty()) {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
            } else {
                Node previous = null;
                int count = current.count - 1;
                if (current.previous.count == count) {
                    previous = current.previous;
                    previous.set.add(key);
                } else {
                    previous = new Node();
                    previous.set.add(key);
                    previous.previous = current.previous;
                    previous.next = current;
                    previous.count = count;
                    current.previous.next = previous;
                    current.previous = previous;
                }
                current.set.remove(key);
                map.put(key, previous);
                if (current.set.isEmpty()) {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
            }
        }

        public String getMaxKey() {
            for (String s : head.previous.set) {
                return s;
            }
            return "";
        }

        public String getMinKey() {
            for (String s : head.next.set) {
                return s;
            }
            return "";
        }

        private static class Node {
            public Set<String> set = new HashSet<>();
            public int count;
            public Node previous;
            public Node next;
        }
    }
}
