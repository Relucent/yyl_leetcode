package yyl.leetcode.p02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>计数质数</h3><br>
 * 统计所有小于非负整数 n 的质数的数量。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * 
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 * </pre>
 * 
 * 提示： 0 <= n <= 5 * 10^6<br>
 */
public class P0204_CountPrimes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.countPrimes(10));
        Assert.assertEquals(0, solution.countPrimes(0));
        Assert.assertEquals(0, solution.countPrimes(1));
    }

    // 枚举
    // 枚举每个数判断其是不是质数
    // 时间复杂度：O(n*sqrt(x))
    // 空间复杂度：O(1)
    static class Solution {
        public int countPrimes(int n) {
            int answer = 0;
            for (int i = 2; i < n; ++i) {
                answer += isPrime(i) ? 1 : 0;
            }
            return answer;
        }

        private boolean isPrime(int x) {
            for (int i = 2; i * i <= x; ++i) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 埃氏筛
    // 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。
    // 埃氏筛算法由希腊数学家厄拉多塞（Eratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。
    // 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数
    // 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 0，这样在运行结束的时候即能知道质数的个数。
    // 时间复杂度：O(n⁡*log(n))
    // 空间复杂度：O(n)
    public int countPrimes1(int n) {
        boolean[] isComposite = new boolean[n];
        int answer = 0;
        for (int i = 2; i < n; ++i) {
            if (isComposite[i] == false) {
                answer += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isComposite[j] = true;
                    }
                }
            }
        }
        return answer;
    }

    // 线性筛
    // 相较于埃氏筛，多维护一个 primes 数组表示当前得到的质数集合。 们从小到大遍历，如果当前的数 x 是质数，就将其加入 primes数组。
    // 与埃氏筛不同的是，「标记过程」不再仅当 x 为质数时才进行，而是对每个整数 x 都进行。保证了每个合数只会被其「最小的质因数」筛去，即每个合数被标记一次。
    // 时间复杂度：O(n)。（理论时间复杂度低，但是JAVA语言因为用到了数据结构 List，Integer的装箱拆箱，所以实际的运行时间可能比埃氏筛还长）
    // 空间复杂度：O(n)
    public int countPrimes2(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i]) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrime[i * primes.get(j)] = false;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }
}
