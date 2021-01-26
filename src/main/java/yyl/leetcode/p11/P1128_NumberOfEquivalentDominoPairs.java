package yyl.leetcode.p11;

import yyl.leetcode.util.Assert;

/**
 * <h3>等价多米诺骨牌对的数量</h3> 给你一个由一些多米诺骨牌组成的列表 dominoes。<br>
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。<br>
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。<br>
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。<br>
 * 
 * <pre>
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= dominoes.length <= 40000<br>
 * └ 1 <= dominoes[i][j] <= 9<br>
 */
public class P1128_NumberOfEquivalentDominoPairs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Assert.assertEquals(1, solution.numEquivDominoPairs(new int[][] { { 1, 2 }, { 2, 1 }, { 3, 4 }, { 5, 6 } }));
        // Assert.assertEquals(3, solution.numEquivDominoPairs(new int[][] { { 1, 2 }, { 1, 2 }, { 2, 1 }, { 3, 4 }, { 5, 6 } }));

        Assert.assertEquals(3, solution.numEquivDominoPairs(new int[][] { { 1, 2 }, { 1, 2 }, { 2, 1 } }));
    }

    // 哈希表
    // 把有序数对封装成类，每一个多米诺骨牌就对应了类的一个对象
    // 在遍历的过程中使用哈希表记录出现的数对的数量。
    // 假设某一类「等价」的对象的总数为 N ，这一类中任意取出 2 个的组合数 C=(N)(N-1)/2就是这一类对总的等价的骨牌对 (i, j) 的数量」。
    // 组合公式 C=N!/((N-M)!*M!)，因为M=2，所以 N!/((N-M)!*M!) = (N*(N-1)*(N-2)*....1)/(((N-2)(N-3)...1)!*2!)=(N)(N-1)/2
    // 时间复杂度：O(N)，其中 N 是输入数组的长度。
    // 空间复杂度：O(A)，这里 A是哈希表中键的总数，本题中是常量。
    static class Solution {
        public int numEquivDominoPairs(int[][] dominoes) {
            // 记录出现的数对的数量 （因为题目中的数字在 1-9之间，所以可以用数组代替HASH）
            int[][] freq = new int[10][10];
            for (int[] dominoe : dominoes) {
                int a = dominoe[0];
                int b = dominoe[1];
                if (b > a) {
                    b = dominoe[0];
                    a = dominoe[1];
                }
                freq[a][b]++;
            }
            int answer = 0;
            for (int[] count : freq) {
                for (int x : count) {
                    // 根据组合数公式 C = (x * (x - 1)) / 2 计算等价骨牌能够组成的组合数，再求和（另：如果x=1的情况，C=0，不用特殊处理）
                    answer += x * (x - 1) / 2;
                }
            }
            return answer;
        }
    }

    // 哈希表 + 状态压缩 (性能最优)
    // 为了使得「等价」更易于比较，可以让较小的数排在前面。
    // 状态压缩 ：把两个有序整数用一个整数表示（哈希表的键和值都是整数，并且键表示的两位整数的最大值是 99）
    // 时间复杂度：O(N)，其中 N 是输入数组的长度。
    // 空间复杂度：O(1)，只需要常数的空间存储若干变量。
    static class Solution1 {
        public int numEquivDominoPairs(int[][] dominoes) {
            int[] freq = new int[100];
            for (int[] dominoe : dominoes) {
                int a = dominoe[0];
                int b = dominoe[1];
                int x = a < b ? a * 10 + b : b * 10 + a;
                freq[x]++;
            }
            int answer = 0;
            for (int x : freq) {
                answer += x * (x - 1) / 2;
            }
            return answer;
        }
    }

    // 二元组表示 + 计数
    // 为了使得「等价」更易于比较，可以让较小的数排在前面。
    // 状态压缩 ：把两个有序整数用一个整数表示（哈希表的键和值都是整数，并且键表示的两位整数的最大值是 99）
    // 在遍历的时候用加法：每遍历到一个在哈希表（数组）中已经存在的骨牌，就给计数器加上此时这个骨牌在哈希表中已经记录的数量，因为当前这个骨牌和已经存在的骨牌中的每一个都等价，然后在给对应的哈希表（数组）中已经存在的骨牌数 +1。
    // 时间复杂度：O(N)，其中 N 是输入数组的长度；但是当N较大时候，求和的次数会较多，性能反而不如最后计算组合的算法（因为Hash表长度100，常数级别）
    // 空间复杂度：O(1)，只需要常数的空间存储若干变量。
    static class Solution2 {
        public int numEquivDominoPairs(int[][] dominoes) {
            int answer = 0;
            int[] freq = new int[100];
            for (int[] dominoe : dominoes) {
                int a = dominoe[0];
                int b = dominoe[1];
                int x = a < b ? a * 10 + b : b * 10 + a;
                answer += freq[x];
                freq[x]++;
            }
            return answer;
        }
    }
}
