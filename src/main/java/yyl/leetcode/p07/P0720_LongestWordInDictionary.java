package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>词典中最长的单词</h3><br>
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。<br>
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 
 * 示例 2：
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply" 
 * 
 * 提示：
 *  1 <= words.length <= 1000
 *  1 <= words[i].length <= 30
 *  所有输入的字符串 words[i] 都只包含小写字母。
 * </pre>
 */
public class P0720_LongestWordInDictionary {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("world", solution.longestWord(new String[] { "w", "wo", "wor", "worl", "world" }));
        Assert.assertEquals("apple", solution.longestWord(new String[] { "a", "banana", "app", "appl", "ap", "apply", "apple" }));
    }

    // 思路：先将所有单词存入字典树。对于每个单词，在字典树中检查它的全部前缀是否存在。
    // 时间复杂度：O(∑{0≤i<n}li)​，其中 n 是数组 words 的长度，l是单词的长度 ，
    // 空间复杂度：O(∑{0≤i<n}li)​
    static class Solution {

        private Trie trie = new Trie();

        public String longestWord(String[] words) {
            for (String word : words) {
                trie.insert(word);
            }
            String answer = "";
            for (String word : words) {
                if (trie.checkPrefix(word)) {
                    if (word.length() > answer.length() || (word.length() == answer.length() && word.compareTo(answer) < 0)) {
                        answer = word;
                    }
                }
            }
            return answer;
        }

        // 前缀树
        static class Trie {
            private boolean leaf;
            private Trie[] children;

            public Trie() {
                leaf = false;
                children = new Trie[26];
            }

            // 插入字符串
            public void insert(String word) {
                Trie node = this;
                for (int i = 0; i < word.length(); i++) {
                    int idx = word.charAt(i) - 'a';
                    if (node.children[idx] == null) {
                        node.children[idx] = new Trie();
                    }
                    node = node.children[idx];
                }
                node.leaf = true;
            }

            // 检查word单词的所有前缀是否都在字典树中
            public boolean checkPrefix(String word) {
                Trie node = this;
                for (int i = 0; i < word.length(); i++) {
                    int idx = word.charAt(i) - 'a';
                    if (node.children[idx] == null || node.children[idx].leaf == false) {
                        return false;
                    }
                    node = node.children[idx];
                }
                return true;
            }
        }
    }
}
