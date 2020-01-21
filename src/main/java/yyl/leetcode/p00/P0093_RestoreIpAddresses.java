package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>复原IP地址</h3><br>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。<br>
 * 
 * <pre>
 * 示例: 
 * 输入: "25525511135" 
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * </pre>
 */
public class P0093_RestoreIpAddresses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));
        System.out.println(solution.restoreIpAddresses("010010"));
    }

    // 暴力法 + 约束规划
    // 检查点可能的所有位置，并只保留有效的部分。
    // 因为IP有4段，如果遍历每个位置，在最坏的情况下，会有 11×10×9=990次检查
    // 但是，其实数字最多只能有3位，所以只有 3×3×3=27种情况
    // 尝试每一段可能的长度(循环1到3)，将符合要求的加入结果
    // 1、这4段长度等于字符串长度（刚好用尽）
    // 2、每一段数字都小于255
    // 时间复杂度：O(1)，常数级别
    // 空间复杂度：O(1)，常数级别
    static class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> result = new ArrayList<>();
            int length = s == null ? 0 : s.length();
            if (length < 4 || 12 < length) {
                return result;
            }
            for (int k1 = 1; k1 < 4; ++k1) {
                for (int k2 = 1; k2 < 4; ++k2) {
                    for (int k3 = 1; k3 < 4; ++k3) {
                        int k4 = length - (k1 + k2 + k3);
                        if (k4 < 1 || k4 > 3) {
                            continue;
                        }
                        int offset = 0;
                        int n1 = Integer.parseInt(s.substring(offset, offset += k1), 10);
                        int n2 = Integer.parseInt(s.substring(offset, offset += k2), 10);
                        int n3 = Integer.parseInt(s.substring(offset, offset += k3), 10);
                        int n4 = Integer.parseInt(s.substring(offset), 10);
                        if (n1 > 255 || n2 > 255 || n3 > 255 || n4 > 255) {
                            continue;
                        }
                        String ip = n1 + "." + n2 + "." + n3 + "." + n4;

                        // 正常情况，IP地址长度=输入字符+3，因为加了3个点
                        // 但是如果字符串包含0，拆分时候可能出现 01,02 这种不规范的IP段落
                        // 这时候和原长度会不一样，需要排除
                        if (ip.length() != length + 3) {
                            continue;
                        }
                        result.add(ip);
                    }
                }
            }
            return result;
        }
    }
}
