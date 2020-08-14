package yyl.leetcode.p07;

import java.util.Arrays;

/**
 * <h3>每日温度</h3> <br>
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。<br>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。<br>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。<br>
 */
public class P0739_DailyTemperatures {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
    }

    // 单调栈
    // 维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
    // 时间复杂度：O(n)，其中 n 是温度列表的长度。正向遍历温度列表一遍，对于温度列表中的每个下标，最多有一次进栈和出栈的操作。
    // 空间复杂度：O(n)，其中 n 是温度列表的长度。需要维护一个单调栈存储温度列表中的下标。
    static class Solution {
        public int[] dailyTemperatures(int[] T) {
            // 定义栈
            int[] stack = new int[T.length];
            int top = -1;
            // 返回结果
            int[] result = new int[T.length];
            for (int i = 0; i < T.length; i++) {
                // (栈不为空 && 栈顶元素小于当前元素)
                while (top != -1 && T[stack[top]] < T[i]) {
                    int previousIndex = stack[top--];
                    result[previousIndex] = i - previousIndex;
                }
                // 当前索引入栈
                stack[++top] = i;
            }
            return result;
        }
    }

    // 暴力法
    // 空间复杂度：O(n)
    // 时间复杂度：O(∑(1,n))
    static class Solution2 {
        public int[] dailyTemperatures(int[] T) {
            int[] result = new int[T.length];
            for (int i = 0; i < T.length; i++) {
                result[i] = findGreater(i, T);
            }
            return result;
        }

        private int findGreater(int index, int[] T) {
            for (int i = index + 1; i < T.length; i++) {
                if (T[index] < T[i]) {
                    return i - index;
                }
            }
            return 0;
        }
    }
}
