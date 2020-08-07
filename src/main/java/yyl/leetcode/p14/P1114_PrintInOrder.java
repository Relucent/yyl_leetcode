package yyl.leetcode.p14;

/**
 * <h3>按序打印</h3><br>
 * 我们提供了一个类：
 * 
 * <pre>
 * public class Foo {
 * 	public void one() {
 * 		print("one");
 * 	}
 * 
 * 	public void two() {
 * 		print("two");
 * 	}
 * 
 * 	public void three() {
 * 		print("three");
 * 	}
 * }
 * </pre>
 * 
 * 三个不同的线程将会共用一个 Foo 实例。 <br>
 * 线程 A 将会调用 one() 方法 <br>
 * 线程 B 将会调用 two() 方法 <br>
 * 线程 C 将会调用 three() 方法<br>
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释: 
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 
 * 示例 2:
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释: 
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 * </pre>
 */
public class P1114_PrintInOrder {

	public static void main(String[] args) {
		final Foo foo = new Foo();
		Runnable[] runnables = { //
				() -> foo.first(() -> System.out.print("one")), //
				() -> foo.second(() -> System.out.print("two")), //
				() -> foo.third(() -> System.out.print("three")), //
		};
		int[] ids = { 0, 2, 1 };
		for (int id : ids) {
			new Thread(runnables[id]).start();
		}
	}

	// 思路：利用 wait() 和 notifyAll() 保证顺序
	static class Foo {

		private final Object lock = new Object();
		private int signal = 1;

		public Foo() {
		}

		public void first(Runnable printFirst) {
			synchronized (lock) {
				while (signal != 1) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				// printFirst.run() outputs "first". Do not change or remove this line.
				printFirst.run();
				signal = 2;
				lock.notifyAll();
			}
		}

		public void second(Runnable printSecond) {
			synchronized (lock) {
				while (signal != 2) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				// printSecond.run() outputs "second". Do not change or remove this line.
				printSecond.run();
				signal = 3;
				lock.notifyAll();
			}
		}

		public void third(Runnable printThird) {
			synchronized (lock) {
				while (signal != 3) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}

				// printThird.run() outputs "third". Do not change or remove this line.
				printThird.run();
				signal = 1;
				lock.notifyAll();
			}
		}
	}
}
