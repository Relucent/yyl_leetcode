package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>格雷编码</h3><br>
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。<br>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 * </pre>
 */
public class P0089_GrayCode {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.grayCode(3));
    }

    // 公式法
    // 利用格雷码公式求解
    // 时间复杂度：O(2^N)，格雷编码序列的个数
    // 空间复杂度：O(2^N)，存储返回的结果
    static class Solution {
        public List<Integer> grayCode(int n) {
            List<Integer> result = new ArrayList<>();
            int size = 1 << n;
            for (int i = 0; i < size; i++) {
                int graycode = i ^ (i >> 1);
                result.add(graycode);
            }
            return result;
        }
    }

    // 找规律，逆序插入法
    // 格雷编码序列的位数等于N, 个数等于 2^N
    // 当n=0时，格雷编码序列为[0]
    // 当n=1时，格雷编码序列为[0,1]
    // 当n=2时，格雷编码序列为[00,01,11,10]
    // 当n=3时，格雷编码序列为[000,001,011,010,110,111,101,100]
    // 可以发现规律
    // |N0|N1|N2|N3|
    // |--|--|--|--|
    // |0|0|00|000|
    // |/|1|01|001|
    // |/|/|11|011|
    // |/|/|10|010|
    // |/|/| /|110|
    // |/|/| /|111|
    // |/|/| /|101|
    // |/|/| /|100|
    // 新的序列前后部分是对称的，每次N + 1的序列，是N的序列基础上高位插入 0或者1。
    // 其中如果高位是0，则数值不变，如果高位是1则后面的数和之前的对称（顺序相反）
    // 可以根据这个规律，首先序列中初始化 0，然后开始遍历N次，为序列增加新的编码
    // 新编码规律为，高位1，后面位数根据之前序列逆向插入
    // 时间复杂度：O(2^N)，格雷编码序列的个数
    // 空间复杂度：O(2^N)，存储返回的结果
    static class Solution1 {
        public List<Integer> grayCode(int n) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            for (int i = 0; i < n; i++) {
                // 获得高位1
                int hightBit = 1 << i;
                // 后部分(高位是1)和之前的部分是逆序的，所以逆向插入
                for (int j = result.size() - 1; j >= 0; j--) {
                    result.add(result.get(j) | hightBit);
                }
            }
            return result;
        }
    }
}
