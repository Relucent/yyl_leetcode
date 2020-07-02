package yyl.leetcode.p03;

import java.util.Arrays;

/**
 * <h3>有序矩阵中第K小的元素</h3><br>
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。<br>
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。<br>
 * 
 * <pre>
 * 示例：
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * </pre>
 * 
 * 提示： 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。<br>
 */
public class P0378_KthSmallestElementInASortedMatrix {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		int k = 8;
		System.out.println(solution.kthSmallest(matrix, k));
	}

	// 二分查找
	// 思路
	// 这个矩阵内的元素是从左上到右下递增的，左上角元素是下界，右下角元素是上界。
	// 每次根据一个猜测值划定出一个值域，并最终保证结果肯定在这个值域中
	// 对值域进行二分查找：
	// 1、「猜测」的答案mid，计算矩阵中有多少数不大于 mid。
	// 2、如果数量不多于 k ，那么说明最终答案 x 不小于 mid；
	// 3、如果数量少于 k ，那么说明最终答案 x 大于 mid。
	// 4、调整值域范围，一步步锁定目标值
	// 最终「猜测」的答案一定是在矩阵中，因为：
	// 终止条件是 left=right， 这left和right的初值就是矩阵最小和最大值，答案必然在[left,right]范围里面。
	// 考虑最后一次移动left的情景：小于mid的元素不足k个，然后 left=mid+1，如果 mid+1 正好是第k个元素，之后left就再移动了，因为 mid+1的将大于等于k个，之后right不断左移，逼近left。
	// 时间复杂度：O(nlog⁡(r−l))，二分查找进行次数为 O(log(r−l))，每次操作时间复杂度为 O(n)。
	// 空间复杂度：O(1)
	static class Solution {
		public int kthSmallest(int[][] matrix, int k) {
			int n = matrix.length;
			int left = matrix[0][0];
			int right = matrix[n - 1][n - 1];
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (check(matrix, mid, k, n)) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			return left;
		}

		private boolean check(int[][] matrix, int mid, int k, int n) {
			int x = n - 1;
			int y = 0;
			int num = 0;
			while (0 <= x && y < n) {
				if (matrix[y][x] <= mid) {
					num += x + 1;
					y++;
				} else {
					x--;
				}
			}
			return num >= k;
		}
	}

	// 排序法
	// 将这个二维数组另存为为一维数组，并对该一维数组进行排序。最后这个一维数组中的第 k个数即为答案。
	// 时间复杂度：O(n^2*log{n})，对 n^2个数排序。
	// 空间复杂度：O(n^2)，需要一维数组存储这 n^2个数。
	static class Solution1 {
		public int kthSmallest(int[][] matrix, int k) {
			int n = matrix.length;
			int[] temp = new int[n * n];
			int i = 0;
			for (int[] row : matrix) {
				for (int num : row) {
					temp[i++] = num;
				}
			}
			Arrays.sort(temp);
			return temp[k - 1];
		}
	}
}
