package yyl.leetcode.p11;

/**
 * <h3>IP 地址无效化</h3><br>
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。<br>
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"

 * 示例 2：
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 * </pre>
 * 
 * 提示： 给出的 address 是一个有效的 IPv4 地址<br>
 */
public class P1108_DefangingAnIpAddress {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.defangIPaddr("192.168.0.1"));
    }

    // 这个只需要遍历全部字符对.进行替换即可
    // 时间复杂度：O(n)，n是字符串长度
    // 空间复杂度：O(n)
    static class Solution {
        public String defangIPaddr(String address) {
            StringBuilder buffer = new StringBuilder();
            for (char c : address.toCharArray()) {
                if (c == '.') {
                    buffer.append("[.]");
                } else {
                    buffer.append(c);
                }
            }
            return buffer.toString();
        }
    }
}
