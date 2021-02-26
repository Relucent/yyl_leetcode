package yyl.leetcode.p11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>猜字谜</h3><br>
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。<br>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：<br>
 * ├ 单词 word 中包含谜面 puzzle 的第一个字母。<br>
 * ├ 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。<br>
 * └ 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。<br>
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。<br>
 * 
 * <pre>
 *  
 * 示例：
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"], 
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * </pre>
 * 
 * 提示：<br>
 * ├1 <= words.length <= 10^5 <br>
 * ├4 <= words[i].length <= 50 <br>
 * ├ 1 <= puzzles.length <= 10^4 <br>
 * ├ puzzles[i].length == 7 <br>
 * ├ words[i][j], puzzles[i][j] 都是小写英文字母。 <br>
 * └ 每个 puzzles[i] 所包含的字符都不重复。 <br>
 */

public class P1178_NumberOfValidWordsForEachPuzzle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = { "aaaa", "asas", "able", "ability", "actt", "actor", "access" };
        String[] puzzles = { "aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz" };
        List<Integer> expected = Arrays.asList(1, 1, 3, 2, 4, 0);
        List<Integer> actual = solution.findNumOfValidWords(words, puzzles);
        Assert.assertEquals(expected, actual);
    }

    // 二进制状态压缩 + 枚举二进制子集
    // 题目的判定条件其实是：1、谜底中一定有谜面的首字母，2、谜底中所有字母都在谜面中。
    // 如果使用暴力法，比较每一个谜底与谜面，时间复杂度会很高O(w*p) w是谜底字符数，p是谜面字符数。
    // 所以需要对谜底和谜面进行预处理，降低复杂度
    // 由于题目中规定 word 和 puzzle 均只包含小写字母，因此 Sw 和 Sp 的大小最多为 26，可以考虑使用一个长度为 26 的二进制数(JAVA 8 中 int 是32位的二进制) bw 和 bp 来表示这一集合。
    // 题目规定 puzzle 字母最多7个，所以如果word 的字母类型大于7，那么一定不是谜底，可以计数时候直接排除
    // 关键在于如何枚举判断一个二进制状态数字k的子集, 方法就是针对中的二进制为1的位开始进行减法，判断数字k的二进制子集
    // 「枚举二进制子集」是有固定算法的：int subset = bits; do{ subset=(subset-1)&bits;} while(subset!=bits);
    // 复杂度分析
    // 时间复杂度：(m|w| + n2^{|p|})，其中 m 和 n 分别是数组 words 和 puzzles 的长度，∣w∣ word 的最大长度 50，∣p∣ 是 puzzle 的最大长度 7。
    // 空间复杂度：O(m)，即为哈希映射需要使用的空间，其中最多只包含 m 个键值对。
    static class Solution {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

            Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
            for (String word : words) {
                int mask = 0;
                for (int j = 0; j < word.length(); j++) {
                    mask |= (1 << (word.charAt(j) - 'a'));
                }
                // 因为谜面字母不大于7，所以只有不大于7个的字符串才可能是谜底
                if (Integer.bitCount(mask) <= 7) {
                    frequency.compute(mask, (k, v) -> v == null ? 1 : v + 1);
                }
            }

            List<Integer> answer = new ArrayList<>();
            for (String puzzle : puzzles) {
                // 首字母的 mask
                int initialMask = (1 << (puzzle.charAt(0) - 'a'));
                int mask = 0;
                // mask 不包含 0首字母，所以从1开始
                for (int i = 1; i < 7; ++i) {
                    mask |= (1 << (puzzle.charAt(i) - 'a'));
                }
                int total = 0;
                // 枚举二进制子集
                int subset = mask;
                do {
                    int s = subset | initialMask;
                    if (frequency.containsKey(s)) {
                        total += frequency.get(s);
                    }
                    subset = (subset - 1) & mask;
                    // 此处不是 subset=0，是因为 0的时候，说明只包含一个首字，所以也需要计算
                } while (subset != mask);
                answer.add(total);
            }
            return answer;
        }
    }
}
