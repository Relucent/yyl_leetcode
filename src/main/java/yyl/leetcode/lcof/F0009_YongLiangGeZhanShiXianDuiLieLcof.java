package yyl.leetcode.lcof;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h3>剑指 Offer 09. 用两个栈实现队列</h3><br>
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。<br>
 * (若队列中没有元素，deleteHead 操作返回 -1 )<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 
 * 提示：
 *     1 <= values <= 10000
 *     最多会对 appendTail、deleteHead 进行 10000 次调用
 * </pre>
 */
public class F0009_YongLiangGeZhanShiXianDuiLieLcof {

	public static void main(String[] args) {
		CQueue obj = new CQueue();
		obj.appendTail(3);
		System.out.println(obj.deleteHead());// 3
		System.out.println(obj.deleteHead());// -1
		System.out.println(obj.deleteHead());// -1
		obj.appendTail(5);
		obj.appendTail(2);
		System.out.println(obj.deleteHead());// 5
		System.out.println(obj.deleteHead());// 2
		System.out.println(obj.deleteHead());// -1
	}

	// 维护两个栈，第一个栈支持插入操作，第二个栈支持删除操作。
	// （队列的特性：先进先出；栈的特性：后进先出）
	// 根据栈先进后出的特性，我们每次往第一个栈里插入元素后，第一个栈的底部元素是最后插入的元素，第一个栈的顶部元素是下一个待删除的元素。
	// 执行删除操作，首先判断第二个栈是否为空，如果第二个栈为空，我们将第一个栈里的元素一个个弹出插入到第二个栈里，这样第二个栈里元素的顺序就是待删除的元素的顺序。之后直接弹出第二个栈的元素返回即可。
	// 时间复杂度：O(1)，插入操作时间复杂度O(1)，删除操作每个元素只会「至多被插入和弹出 stack2 一次」，因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。
	// 空间复杂度：O(n)，需要使用两个栈存储已有的元素。
	static class CQueue {

		// 用于插入操作的栈
		private Deque<Integer> stack1;
		// 用于删除操作的栈
		private Deque<Integer> stack2;

		public CQueue() {
			stack1 = new ArrayDeque<>();
			stack2 = new ArrayDeque<>();
		}

		public void appendTail(int value) {
			stack1.push(value);
		}

		public int deleteHead() {
			// 删除操作栈空，那么将插入操作栈的元素弹出添加到删除操作栈中
			if (stack2.isEmpty()) {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}
			return stack2.isEmpty() ? -1 : stack2.pop();
		}
	}
}
