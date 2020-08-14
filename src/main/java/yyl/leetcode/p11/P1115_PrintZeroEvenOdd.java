package yyl.leetcode.p11;

/**
 * <h3>打印零与奇偶数</h3><br>
 * 假设有这么一个类：
 * 
 * <pre>
 * 
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * </pre>
 * 
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：<br>
 * 线程 A 将调用 zero()，它只输出 0。<br>
 * 线程 B 将调用 even()，它只输出偶数。<br>
 * 线程 C 将调用 odd()，它只输出奇数。<br>
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 
 * 示例 2：
 * 输入：n = 5
 * 输出："0102030405"
 * </pre>
 */
public class P1115_PrintZeroEvenOdd {

    static final class IntConsumer {
        public void accept(int x) {
            System.out.print(x);
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer printNumber = new IntConsumer();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class ZeroEvenOdd {
        private int n;
        private int x = 0;
        private boolean zero = true;
        private Object lock = new Object();

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    try {
                        while (!zero && x <= n) {
                            lock.wait();
                        }
                        if (x > n) {
                            return;
                        }
                        if (x < n) {
                            printNumber.accept(0);
                        }
                    } finally {
                        zero = false;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    try {
                        while ((zero || x % 2 == 0) && x <= n) {
                            lock.wait();
                        }
                        x++;
                        if (x > n) {
                            return;
                        }
                        printNumber.accept(x);
                    } finally {
                        zero = true;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    try {
                        while ((zero || x % 2 == 1) && x <= n) {
                            lock.wait();
                        }
                        x++;
                        if (x > n) {
                            return;
                        }
                        printNumber.accept(x);
                    } finally {
                        zero = true;
                        lock.notifyAll();
                    }
                }
            }
        }
    }
}
