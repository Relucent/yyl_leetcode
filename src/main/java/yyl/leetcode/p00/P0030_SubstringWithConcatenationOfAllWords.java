package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>字符串中具有所有单词串联的子串</h3><br>
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。<br>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。<br>
 * 示例 1：<br>
 * 输入：<br>
 * s = "barfoothefoobarman",words = ["foo","bar"]<br>
 * 输出：[0,9]<br>
 * 解释： 从索引 0 和 9 开始的子串分别是 "barfoo"和 "foobar"<br>
 * 示例 2：<br>
 * 输入：<br>
 * s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]<br>
 * 输出：[]<br>
 */
public class P0030_SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}));
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"}));
        System.out.println(solution.findSubstring("", new String[] {"hello"}));
        System.out.println(solution.findSubstring("hello", null));
    }

    static class Solution {

        public List<Integer> findSubstring(String s, String[] words) {

            // 返回结果
            List<Integer> result = new ArrayList<Integer>();

            // 排除部分异常
            if (s == null || s.isEmpty() || words == null || words.length == 0 || words[0].isEmpty()) {
                return result;
            }

            // 单词个数
            int wordsSize = words.length;

            // 单词的长度(因为单词长度相同,取第一个单词长度就行)
            int wordLength = words[0].length();

            // 所有单词的总长度
            int allWordLength = wordLength * wordsSize;

            // 单词的计数 O(N)
            Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
            for (String word : words) {
                Integer count = wordCountMap.get(word);
                if (count == null) {
                    wordCountMap.put(word, 1);
                } else {
                    wordCountMap.put(word, count + 1);
                }
            }
            // 对s串进行扫描 O(N)
            for (int index = 0, end = s.length() - allWordLength; index <= end; index++) {
                // 拷贝wordCountMap
                Map<String, Integer> tempWordCountMap = new HashMap<String, Integer>();
                for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                    tempWordCountMap.put(entry.getKey(), entry.getValue());
                }
                // 遍历词 O(L)
                T1: for (int left = index, right = left + allWordLength; left < right; left += wordLength) {
                    String fragment = s.substring(left, left + wordLength);
                    Integer count = tempWordCountMap.get(fragment);
                    if (count == null) {
                        break T1;
                    }
                    count = count - 1;
                    if (count == 0) {
                        tempWordCountMap.remove(fragment);
                    } else {
                        tempWordCountMap.put(fragment, count);
                    }
                }
                if (tempWordCountMap.isEmpty()) {
                    result.add(index);
                }
            }
            return result;
        }
    }
}
