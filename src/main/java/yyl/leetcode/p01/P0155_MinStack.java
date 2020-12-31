package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>最小栈</h3><br>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。<br>
 * ├ push(x) —— 将元素 x 推入栈中。<br>
 * ├ pop() —— 删除栈顶的元素。<br>
 * ├ top() —— 获取栈顶元素。<br>
 * └ getMin() —— 检索栈中的最小元素。<br>
 * 
 * <pre>
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * </pre>
 * 
 * 提示： pop、top 和 getMin 操作总是在 非空栈 上调用。<br>
 */
public class P0155_MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        Assert.assertEquals(-3, stack.getMin());
        stack.pop();
        Assert.assertEquals(0, stack.top());
        Assert.assertEquals(-2, stack.getMin());
    }

    // 单链表
    // 栈结构的特性：“先进后出”
    // 自定义栈，使用链表结构模拟栈结，链表每个节点存储当前对应的最小值。
    // ├ 当一个元素要入栈时，取当前栈顶存储的最小值，与当前元素比较得出最小值；
    // └ 当一个元素要出栈时，将头指向下一个元素；
    // 时间复杂度：O(1)。因为栈的插入、删除与读取操作都是 O(1)。
    // 空间复杂度：O(n)，其中 n 为总操作数。最坏情况下，会连续插入 n 个元素，此时栈占用的空间为 O(n)。
    static class MinStack {

        private Node head;

        /** initialize your data structure here. */
        public MinStack() {
        }

        public void push(int x) {
            if (head == null) {
                head = new Node(x, x, null);
            } else {
                head = new Node(x, Math.min(head.min, x), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.value;
        }

        public int getMin() {
            return head.min;
        }

        class Node {
            private int value;
            private int min;
            private Node next;

            private Node(int value, int min, Node next) {
                this.value = value;
                this.min = min;
                this.next = next;
            }
        }
    }

    // Your MinStack object will be instantiated and called as such:
    // MinStack obj = new MinStack();
    // obj.push(x);
    // obj.pop();
    // int param_3 = obj.top();
    // int param_4 = obj.getMin();
}
