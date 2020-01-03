package yyl.leetcode.p00;

/**
 * <h3>简化路径</h3><br>
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。<br>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径<br>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 
 * 示例 2：
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 
 * 示例 3：
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。

示例 4：
 * 输入："/a/./b/../../c/"
 * 输出："/c"

 * 示例 5：
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"

 * 示例 6：
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * </pre>
 */
public class P0071_SimplifyPath {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] samples = {//
                "/home/", //
                "/../", //
                "/home//foo/", //
                "/a/./b/../../c/", //
                "/a/../../b/../c//.//", //
                "/a//b////c/d//././/..", //
                "/../../../", //
                "/...", //
                "/", //
                "chaos/1/../...//", //
                "../../..", //
                "..",//
        };
        for (String sample : samples) {
            System.out.println(sample + "   =>  " + solution.simplifyPath(sample));
        }
    }

    // 遍历法(优化)
    // 直接对字节数组进行操作，并且减少回溯，需要注意首字母非 '/'的情况(防止下标越界异常)
    // 时间复杂度：O(N)，N为输入字符串长度
    // 空间复杂度：O(N)
    static class Solution {
        public String simplifyPath(String path) {
            if (path == null || path.length() == 0) {
                return path;
            }
            char[] raw = path.toCharArray();
            char[] result = new char[raw.length];
            int end = 0;
            for (int i = 0; i < raw.length;) {
                while (i < raw.length && raw[i] == '/') {
                    i++;
                }
                if (i == raw.length) {
                    break;
                }
                int length = 1;
                while (i + length < raw.length && raw[i + length] != '/') {
                    length++;
                }
                // ~/./
                if (length == 1 && raw[i] == '.') {
                    // ignore
                }
                // ~/../
                else if (length == 2 && raw[i] == '.' && raw[i + 1] == '.') {
                    while (end > 0 && result[end - 1] != '/') {
                        end--;
                    }
                    end = Math.max(0, end - 1);
                }
                // ~
                else {
                    // 首字母不是 '/'
                    if (i == 0) {
                        System.arraycopy(raw, i, result, end, length);
                        end += length;
                    } else {
                        System.arraycopy(raw, i - 1, result, end, length + 1);
                        end += length + 1;
                    }
                }
                i += length;
            }
            if (end <= 0) {
                return "/";
            }
            return new String(result, 0, end);
        }
    }

    // 遍历回溯法
    // 直接遍历，拼装路径字符串，遇到特殊字符进行判断，符合一定条件回退路径。
    // 创建 StringBuider ，用于拼装新路径
    // 遍历路径字符时，如果没有遇到 '/' ,直接将遍历到的字符添加到StringBuider中即可。
    // 如果到达末尾或遇到 '/' 字符时，需要进行处理，分四种情况考虑
    // 1. '//' 多个连续斜杠需要用一个斜杠替换，忽略这个字符
    // 2. '/./' 一个点（.）表示当前目录本身，忽略这个/.路径，需要从末尾删除一个字符(.)
    // 3. '/../' 目录切换到上一级，回退路径(从末尾删除路径到上一个斜杠)。
    // 4. 其他情况，不做处理，直接将字符添加到 StringBuider中
    // 因为只遍历了一次，并且回溯次数有限(最差情况，回溯字符总数=N)，所以时间复杂度为 O(2N)，计算时间复杂度忽略常数。
    // 时间复杂度：O(N)，N为输入字符串长度
    // 空间复杂度：O(N)
    static class Solution2 {
        public String simplifyPath(String path) {
            if (path == null || path.length() == 0) {
                return path;
            }
            StringBuilder builder = new StringBuilder(path.length());
            builder.append('/');
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) != '/' || backspace(builder)) {
                    builder.append(path.charAt(i));
                }
            }
            backspace(builder);
            if (builder.length() > 1 && builder.charAt(builder.length() - 1) == '/') {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }

        private boolean backspace(StringBuilder builder) {
            int i = builder.length() - 1;
            if (i >= 0 && builder.charAt(i) == '/') {
                return true;
            }
            if (i >= 1 && builder.charAt(i) == '.' && builder.charAt(i - 1) == '/') {
                builder.delete(i, builder.length());
                return true;
            }
            if (i >= 2 && builder.charAt(i) == '.' && builder.charAt(i - 1) == '.' && builder.charAt(i - 2) == '/') {
                i -= 3;
                while (i != -1 && builder.charAt(i) != '/') {
                    i--;
                }
                builder.delete(i + 1, builder.length());
                if (builder.length() == 0) {
                    builder.append('/');
                }
                return true;
            }
            return false;
        }
    }
}
