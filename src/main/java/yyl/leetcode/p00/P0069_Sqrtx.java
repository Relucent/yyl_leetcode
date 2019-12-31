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
        Solution3 solution = new Solution3();
        System.out.println(solution.mySqrt(1));
        System.out.println(solution.mySqrt(4));
        System.out.println(solution.mySqrt(10));
        System.out.println(solution.mySqrt(2147395599));
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
    // 时间复杂度是：O(log(x/2))
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
