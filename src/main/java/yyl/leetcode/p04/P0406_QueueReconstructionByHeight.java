package yyl.leetcode.p04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>根据身高重建队列</h3><br>
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。<br>
 * 注意：<br>
 * 总人数少于1100人。<br>
 * 
 * <pre>
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * </pre>
 */
public class P0406_QueueReconstructionByHeight {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] sample = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
        int[][] expected = { { 5, 0 }, { 7, 0 }, { 5, 2 }, { 6, 1 }, { 4, 4 }, { 7, 1 } };
        int[][] actual = solution.reconstructQueue(sample);
        Assert.assertEquals(expected, actual);
    }

    // 从高到低考虑
    // 将每个人按照身高从大到小进行排序，按照排完序后的顺序，依次将每个人放入队列中
    // 因为后面的人不会对前面的人造成影响，可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置
    // 也就是说，当放入第 i 个人时，只需要将其插入队列中，使得他的前面恰好有 i_k 个人即可。
    // 时间复杂度：O(n2)，其中 n 是数组 people的长度。需要 O(nlog⁡n)的时间进行排序，随后需要 O(n^2)的时间遍历每一个人并将他们放入队列中。
    // 空间复杂度：O(logn)。
    static class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
                }
            });
            List<int[]> list = new ArrayList<>();
            for (int[] person : people) {
                list.add(person[1], person);
            }
            return list.toArray(new int[0][]);
        }
    }
}
