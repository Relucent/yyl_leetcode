package yyl.leetcode.p09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import yyl.leetcode.util.Assert;

/**
 * <h3>最接近原点的 K 个点
 * <h3><br>
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。<br>
 * （这里，平面上两点之间的距离是欧几里德距离。）<br>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释： 
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * </pre>
 */
public class P0973_KClosestPointsToOrigin {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[][] { { -2, 2 } }, solution.kClosest(new int[][] { { 1, 3 }, { -2, 2 } }, 1));
        Assert.assertEquals(new int[][] { { 3, 3 }, { -2, 4 } }, solution.kClosest(new int[][] { { 3, 3 }, { 5, -1 }, { -2, 4 } }, 2));
    }

    // 优先队列
    // 使用一个优先队列（大根堆）实时维护前 K个最小的距离平方。（可以省略开方的计算Math.sqrt）
    // 时间复杂度：O(nlog⁡K)，其中 n 是数组 points 的长度。由于优先队列维护的是前 K 个距离最小的点，因此弹出和插入操作的单次时间复杂度均为 O(log⁡K)。在最坏情况下，数组里 n 个点都会插入，因此时间复杂度为 O(nlog⁡K)。
    // 空间复杂度：O(K)，因为优先队列中最多有 K 个点。
    static class Solution {
        public int[][] kClosest(int[][] points, int K) {
            class Element {
                private final int[] point;
                private final double distance;

                Element(int[] point, int distance) {
                    this.point = point;
                    this.distance = distance;
                }
            }
            PriorityQueue<Element> queue = new PriorityQueue<>((e1, e2) -> {
                return -Double.compare(e1.distance, e2.distance);
            });

            for (int i = 0; i < K; ++i) {
                int[] point = points[i];
                int distance = point[0] * point[0] + point[1] * point[1];
                queue.offer(new Element(point, distance));
            }

            for (int i = K; i < points.length; i++) {
                int[] point = points[i];
                int distance = point[0] * point[0] + point[1] * point[1];
                Element head = queue.peek();
                if (distance < head.distance) {
                    queue.poll();
                    queue.offer(new Element(point, distance));
                }
            }

            int[][] answer = new int[K][];
            for (int i = K - 1; i >= 0; i--) {
                answer[i] = queue.poll().point;
            }
            return answer;
        }
    }

    // 排序
    // 将每个点到原点的欧几里得距离的平方从小到大排序后，取出前 K 个即可。
    // 时间复杂度：O(nlog⁡n)，其中 n 是数组 points 的长度。算法的时间复杂度即排序的时间复杂度。
    // 空间复杂度：O(log⁡n)，排序所需额外的空间复杂度为 O(log⁡n)。
    static class Solution1 {
        public int[][] kClosest(int[][] points, int K) {
            Arrays.sort(points, new Comparator<int[]>() {
                public int compare(int[] point1, int[] point2) {
                    return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
                }
            });
            return Arrays.copyOfRange(points, 0, K);
        }
    }
}
