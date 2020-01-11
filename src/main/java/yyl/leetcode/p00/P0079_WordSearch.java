package yyl.leetcode.p00;

/**
 * <h3>单词搜索</h3><br>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。<br>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。<br>
 * 同一个单元格内的字母不允许被重复使用。<br>
 * 
 * <pre>
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * </pre>
 */
public class P0079_WordSearch {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {//
                {'A', 'B', 'C', 'E'}, //
                {'S', 'F', 'C', 'S'}, //
                {'A', 'D', 'E', 'E'}//
        };
        System.out.println(solution.exist(board, "ABCCED"));
        System.out.println(solution.exist(board, "SEE"));
        System.out.println(solution.exist(board, "ABCB"));
    }

    // 回溯+剪枝
    // 首先查找到首字母，然后从该位置向4个方向遍历
    // 时间复杂度：O(N*M)，N为二维网格的元素个数，M为字符串长度
    // 空间复杂度：O(N)，N为二维网格的元素个数
    static class Solution {
        public boolean exist(char[][] board, String word) {
            if (word.isEmpty()) {
                return true;
            }
            if (board.length == 0 || board[0].length == 0) {
                return false;
            }
            int m = board.length;
            int n = board[0].length;
            if (m * n < word.length()) {
                return false;
            }
            boolean[][] visited = new boolean[m][n];
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    if (search(board, m, n, y, x, visited, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean search(char[][] board, int m, int n, int y, int x, boolean[][] visited, String word, int p) {
            if (p == word.length()) {
                return true;
            }
            if (y < 0 || x < 0 || y >= m || x >= n || visited[y][x] || board[y][x] != word.charAt(p)) {
                return false;
            }
            visited[y][x] = true;
            if (search(board, m, n, y - 1, x, visited, word, p + 1)// up
                    || search(board, m, n, y + 1, x, visited, word, p + 1)// down
                    || search(board, m, n, y, x - 1, visited, word, p + 1)// left
                    || search(board, m, n, y, x + 1, visited, word, p + 1)// right
            ) {
                return true;
            }
            visited[y][x] = false;
            return false;
        }
    }
}
