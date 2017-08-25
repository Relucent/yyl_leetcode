package yyl.algorithms.v004;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.<br>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).<br>
 * Example 1:<br>
 * nums1 = [1, 3]<br>
 * nums2 = [2]<br>
 * The median is 2.0<br>
 * Example 2:<br>
 * nums1 = [1, 2]<br>
 * nums2 = [3, 4]<br>
 * The median is (2 + 3)/2 = 2.5<br>
 */
// 给定两个已经排序好的数组(可能为空)，找到所有元素的中位数。
// 中位数 (集合中一半数比它大，一半数比它小)
public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		for (TestSample sample : TestSample.values()) {
			double result = findMedianSortedArrays(sample.nums1(), sample.nums2());
			System.out.println("answer:" + result + "; expected:" + sample.expected());
		}
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int len1 = nums1 == null ? 0 : nums1.length;
		int len2 = nums2 == null ? 0 : nums2.length;

		if (len1 == 0 && len2 == 0) {
			return 0.0;
		}
		if (len1 == 0) {
			return len2 % 2 == 0 ? (nums2[len2 / 2] + nums2[len2 / 2 - 1]) / 2.0 : nums2[len2 / 2];
		}
		if (len2 == 0) {
			return len1 % 2 == 0 ? (nums1[len1 / 2] + nums1[len1 / 2 - 1]) / 2.0 : nums1[len1 / 2];
		}
		int total = len1 + len2;
		int count = 0;
		int index1 = 0;
		int index2 = 0;
		int median = 0;
		int preMedian = 0;

		//查找第 (total/2) 个数
		while (count <= total / 2) {
			if ((index1 < len1 && index2 < len2 && nums1[index1] <= nums2[index2]) || index2 >= len2) {
				preMedian = median;
				median = nums1[index1++];
			} else {
				preMedian = median;
				median = nums2[index2++];
			}
			count++;
		}
		return total % 2 != 0 ? median : (median + preMedian) / 2.0;
	}

}
