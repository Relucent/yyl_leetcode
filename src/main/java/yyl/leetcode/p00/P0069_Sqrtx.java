package yyl.leetcode.p00;

/**
 * <h3>X的平方根</h3><br>
 * 实现 int sqrt(int x) 函数。<br>
 * 计算并返回 x 的平方根，其中 x 是非负整数。<br>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * </pre>
 */
public class P0069_Sqrtx {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(0));// 0
        System.out.println(solution.mySqrt(1));// 1
        System.out.println(solution.mySqrt(4));// 2
        System.out.println(solution.mySqrt(10));// 3
        System.out.println(solution.mySqrt(2147395599));// 46339
        System.out.println(solution.mySqrt(Integer.MAX_VALUE/* 2147483647 */));// 46340
    }


    // 牛顿法求解平方根
    // 牛顿迭代法（Newton's method）又称为牛顿-拉夫逊（拉弗森）方法（Newton-Raphson method），它是牛顿在17世纪提出的一种在实数域和复数域上近似求解方程的方法。
    // 求 x 的平方根，首先猜测一个值z=x/2，然后根据迭代公式 z{n+1} = (z{n} + x/z{n})/2，算出z2，再将z2代公式的右边算出z3
    // 通过反复迭代，z 趋近于x的平方根 ，当 z^2与x 的差的绝对值小于某个值，即认为找到了精确的平方根。
    // 牛顿迭代法一般不讨论其时间复杂度，迭代法的总的计算量由迭代次数决定，而迭代次数与阈值、初始值、收敛后的值有关。
    // 开平方所需的迭代次数随被开方数的大小变化：
    // 当被开方数范围在 1~1000之间时，迭代次数在1~9之间。
    // 时间复杂度：O(1)，时间复杂度与精度有关，一般情况可以认为是常数级别。
    // 空间复杂度：O(1)
    static class Solution {
        public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            }
            double z = x;
            while (Math.abs(z * z - x) >= 0.05) {
                z = (z + x / z) / 2;
            }
            return (int) z;
        }
    }

    // 牛顿法求解平方根
    // 牛顿迭代法（Newton's method）又称为牛顿-拉夫逊（拉弗森）方法（Newton-Raphson method），它是牛顿在17世纪提出的一种在实数域和复数域上近似求解方程的方法。
    // 假设欲求x的平方根，首先猜测一个值z=x/2，然后根据迭代公式 z{n+1} = (z{n} + x/z{n})/2，算出z2，再将z2代公式的右边算出z3，然后继续迭代计算，直到 z{n}和z{n+1}的差的绝对值小于某个值，即认为找到了精确的平方根。
    // 这个算法比上一个算法少了一个平方运算，并且更为求的解更加精确
    // 牛顿迭代法一般不讨论其时间复杂度，迭代法的总的计算量由迭代次数决定，而迭代次数与阈值、初始值、收敛后的值有关。
    // 根据牛顿法的原理可知，迭代的次数越多，近似值越逼近真实值，当然我们会通过设置精度来限制它的迭代次数。
    // 当被开方数为 范围为 0~2147483647(Integer.Integer.MAX_VALUE)时，迭代次数范围为 1~20 次。。
    // 时间复杂度：O(1)，时间复杂度与精度有关，一般情况可以认为是常数级别<br>
    // 空间复杂度：O(1)
    static class Solution1 {
        public int mySqrt(int x) {
            if (x < 0) {
                return 0;
            }
            if (x == 0 || x == 1) {
                return x;
            }
            double e = 1e-15;
            double z = x;
            double y = (z + x / z) / 2;
            while (Math.abs(z - y) > e) {
                z = y;
                y = (z + x / z) / 2;
            }
            return (int) z;
        }
    }

    // 穷举法(时间复杂度很高，不推荐使用)
    // 对x求平方根，其解必定在(0,x)之间，从0开始遍历，暴力穷举。
    // 运算过程中涉及到乘法运算时同样需要强转为long类型防止越界
    // 时间复杂度是：O(sqrt(x))
    // 空间复杂度是：O(1)。
    static class Solution2 {
        public int mySqrt(int x) {
            for (long i = 0; i <= x; i++) {
                if (i * i == x) {
                    return (int) i;
                }
                if (i * i > x) {
                    return (int) (i - 1);
                }
            }
            return 0;
        }
    }

    // 二分法：穷举法的优化，使用中间数作为(例如x/2)作为猜测值来运算
    // 如果结果大了，说明解在区间(0，x/2)之间，如果结果小了，说明解在区间(x/2，x)，以此类推
    // 运算过程中涉及到乘法运算时同样需要强转为long类型防止越界
    // 时间复杂度是：O(log(x))
    // 空间复杂度是：O(1)。
    static class Solution3 {
        public int mySqrt(int x) {
            long left = 0;
            long right = (x / 2) + 1;
            while (left <= right) {
                long mid = (left + right) / 2;
                long square = mid * mid;
                if (square == x) {
                    return (int) mid;
                } else if (square < x) {
                    left = mid + 1;
                } else { // sqrt > x
                    right = mid - 1;
                }
            }
            return (int) right;
        }
    }
}
