package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>宝石与石头</h3><br>
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。<br>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * </pre>
 * 
 * 注意:<br>
 * S 和 J 最多含有50个字母。<br>
 * J 中的字符不重复。<br>
 */
public class P0771_JewelsAndStones {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.numJewelsInStones("aA", "aAAbbbb"));
        Assert.assertEquals(0, solution.numJewelsInStones("z", "ZZ"));
    }

    // 哈希集合
    // 使用哈希集合存储字符串 J 中的宝石， 然后遍历字符串 S，对于其中的每个字符，如果其在哈希集合中，则是宝石。
    // 时间复杂度：O(m+n)，其中 m 是字符串 J 的长度，n 是字符串 S 的长度。遍历字符串 J 将其中的字符存储到哈希集合中，时间复杂度是 O(m)，然后遍历字符串 S，对于 SSS 中的每个字符在 O(1)的时间内判断当前字符是否是宝石，时间复杂度是 O(n)，因此总时间复杂度是 O(m+n)。
    // 空间复杂度：O(m)，其中 m是字符串 J的长度。使用哈希集合存储字符串 J中的字符。
    static class Solution {
        public int numJewelsInStones(String J, String S) {
            boolean[] hash = new boolean[128];
            for (int i = 0; i < J.length(); i++) {
                hash[J.charAt(i)] = true;
            }
            int answer = 0;
            for (int i = 0; i < S.length(); i++) {
                if (hash[S.charAt(i)]) {
                    answer++;
                }
            }
            return answer;
        }
    }
}
