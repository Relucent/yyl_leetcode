package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>文本左右对齐</h3><br>
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。<br>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。<br>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。<br>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。<br>
 * 说明:<br>
 * 单词是指由非空格字符组成的字符序列。<br>
 * 每个单词的长度大于 0，小于等于 maxWidth。<br>
 * 输入单词数组 words 至少包含一个单词。<br>
 * 
 * <pre>
 * 示例1:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]

 * 示例 2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。       
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 
 * 示例 3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  " * 
 * ]
 * </pre>
 */
public class P0068_TextJustification {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> result = solution.fullJustify(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16);
        for (String line : result) {
            System.out.println("\"" + line + "\"");
        }
    }

    // 逐行处理，确定每一行能放下的单词数，然后补充空格
    // ├ 首先将给定的words字符串数组按照最大长度maxWidth分组
    // └ 得到新的分组列表之后，开始对每一组插入空格
    // __├ 不需要插入空格的情况 (一个单词占了整个长度)
    // __└ 需要插入空格的情况
    // ____├ 当前行不是最后一行
    // ____│_├ 当前行只有一个元素，则左排列，后面补空格 (#1)
    // ____│_└ 当前行不止一个元素
    // ____│___├ 几个元素之间的空格刚好能均匀分配，即空格数相等
    // ____│___└ 几个元素之间的空格不能均匀分配，题目要求左边的空格数要大于右边的，所以此处还得给左边的空格额外分配
    // ____└ 当前行是最后一行。
    // ______├ 当前行只有一个元素，则左排列，后面补空格 (#1)
    // ______└ 当前行不止一个元素，则左排列，元素只需要补一个空格，最后一个元素后填补其余的空格
    // 时间复杂度：O(N)，N表示单词数组长度(没有计算拼装空格的时间)
    // 空间复杂度：O(M)，M表示返回结果集合长度
    static class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>();
            int i = 0;
            while (i < words.length) {
                int j = i;
                int len = 0;
                // 同一行单词之间至少一个空格，所以空格数量至少是 (j-i)
                while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
                    len += words[j++].length();
                }
                // 这一行的空格数
                int m = maxWidth - len;
                StringBuilder builder = new StringBuilder();
                for (int k = i; k < j; k++) {
                    builder.append(words[k]);
                    // 需要插入空格的情况
                    if (m > 0) {
                        // 剩余元素个数
                        int o = j - k - 1;
                        // 单词后需要插入的空格数
                        int n = 0;
                        // 只剩下一个单词，那么剩余空格只能全部插入末尾
                        if (o == 0) {
                            n = m;
                        }
                        // 当前行不止一个元素
                        // 最后一行
                        else if (j == words.length) {
                            // 补一个空格
                            n = 1;
                        }
                        // 不是最后一行
                        else {
                            // 剩余空格分配数
                            n = m / o;
                            // 剩余空格不能均匀分配，那么多分配一个
                            if (m % o != 0) {
                                n = n + 1;
                            }
                        }
                        m -= n;
                        // 填充空格
                        while ((n--) > 0) {
                            builder.append(' ');
                        }
                    }
                }
                result.add(builder.toString());
                i = j;
            }
            return result;
        }
    }
}
