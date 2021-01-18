package yyl.leetcode.p07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>账户合并</h3><br>
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。<br>
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。<br>
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * </pre>
 * 
 * 提示：<br>
 * ├ accounts的长度将在[1，1000]的范围内。<br>
 * ├ accounts[i]的长度将在[1，10]的范围内。 <br>
 * └ accounts[i][j]的长度将在[1，30]的范围内。 <br>
 */
public class P0721_AccountsMerge {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> accounts = Arrays.asList(//
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"), //
                Arrays.asList("John", "johnnybravo@mail.com"), //
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"), //
                Arrays.asList("Mary", "mary@mail.com")//
        );
        List<List<String>> answer = solution.accountsMerge(accounts);
        Assert.assertEquals(Arrays.asList(//
                Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"), //
                Arrays.asList("John", "johnnybravo@mail.com"), //
                Arrays.asList("Mary", "mary@mail.com")//
        ), answer);
    }

    // 并查集
    // 两个账户需要合并，的条件是两个账户至少有一个共同的邮箱地址。这道题的实质是判断那些邮箱属于一个集合，可以使用并查集实现。
    // 时间复杂度：O(nlog⁡n)，其中 n 是不同邮箱地址的数量。
    // 空间复杂度：O(n)，其中 n 是不同邮箱地址的数量。空间复杂度主要取决于哈希表和并查集，每个哈希表存储的邮箱地址的数量为 n ，并查集的大小为 n。
    static class Solution {

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            Map<String, Node> nodes = new HashMap<>();

            int index = 0;
            for (List<String> account : accounts) {
                int size = account.size();
                String name = account.get(0);
                if (size == 1) {
                    continue;
                }
                for (int i = 1; i < size; i++) {
                    String email = account.get(i);
                    Node node = nodes.get(email);
                    if (node == null) {
                        nodes.put(email, new Node(name, index++));
                    }
                }
            }

            UnionFind uf = new UnionFind(nodes.size());
            for (List<String> account : accounts) {
                int size = account.size();
                if (size == 2) {
                    continue;
                }
                int x = nodes.get(account.get(1)).index;
                uf.union(x, x);
                for (int i = 2; i < size; i++) {
                    int y = nodes.get(account.get(i)).index;
                    uf.union(x, y);
                }
            }
            Map<Integer, List<String>> groups = new HashMap<>();
            for (Map.Entry<String, Node> entry : nodes.entrySet()) {
                String email = entry.getKey();
                Node node = entry.getValue();
                int x = node.index;
                int root = uf.find(x);
                List<String> group = groups.get(root);
                if (group == null) {
                    group = new ArrayList<>();
                    group.add(node.name);
                    groups.put(root, group);
                }
                group.add(email);
            }
            List<List<String>> answer = new ArrayList<>();
            for (List<String> group : groups.values()) {
                Collections.sort(group);
                answer.add(group);
            }
            return answer;
        }

        class Node {
            String name;
            int index;

            public Node(String name, int index) {
                this.name = name;
                this.index = index;
            }
        }

        class UnionFind {
            private int[] parent;

            UnionFind(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            private void union(int x, int y) {
                int xRoot = find(x);
                int yRoot = find(y);
                if (xRoot != yRoot) {
                    parent[yRoot] = xRoot;
                }
            }

            private int find(int x) {
                while (x != parent[x]) {
                    x = parent[x];
                }
                return x;
            }
        }
    }
}
