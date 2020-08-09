package yyl.leetcode.p11;

/**
 * <h3>交替打印FooBar</h3><bre> 我们提供一个类：
 * 
 * <pre>
 * class FooBar {
 * 	public void foo() {
 * 		for (int i = 0; i < n; i++) {
 * 			print("foo");
 * 		}
 * 	}
 * 
 * 	public void bar() {
 * 		for (int i = 0; i < n; i++) {
 * 			print("bar");
 * 		}
 * 	}
 * }
 * </pre>
 * 
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。<bre> 请设计修改程序，以确保 "foobar" 被输出 n 次。<bre>
 * 
 * <pre>
 * 示例 1:
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 
 * 示例 2:
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 * </pre>
 */
public class P1115_PrintFoobarAlternately {

	public static void main(String[] args) {
		Runnable printFoo = () -> System.out.print("foo");
		Runnable printBar = () -> System.out.print("bar");
		FooBar sample = new FooBar(2);
		new Thread(() -> {
			try {
				sample.foo(printFoo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				sample.bar(printBar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	static class FooBar {

		private final int n;
		private Object lock = new Object();
		private int signal = 0;

		public FooBar(int n) {
			this.n = n;
		}

		public void foo(Runnable printFoo) throws InterruptedException {
			for (int i = 0; i < n; i++) {
				synchronized (lock) {
					while (signal != 0) {
						lock.wait();
					}
					// printFoo.run() outputs "foo". Do not change or remove this line.
					printFoo.run();
					signal = 1;
					lock.notifyAll();
				}
			}
		}

		public void bar(Runnable printBar) throws InterruptedException {
			for (int i = 0; i < n; i++) {
				synchronized (lock) {
					while (signal != 1) {
						lock.wait();
					}
					// printBar.run() outputs "bar". Do not change or remove this line.
					printBar.run();
					signal = 0;
					lock.notifyAll();
				}
			}
		}
	}
}
