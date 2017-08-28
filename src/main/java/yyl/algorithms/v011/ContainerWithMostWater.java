package yyl.algorithms.v011;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). <br>
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). <br>
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.<br>
 * Note: You may not slant the container and n is at least 2. <br>
 */
//给定n个非负整数a1,a2,...,an，其中每个代表一个点坐标(i,ai)。 
//n个垂直线段例如线段的两个端点在(i,ai)和(i,0)。 
//找到两个线段，与x轴形成一个容器，使其包含最多的水。 
//备注：你不必倾倒容器，n至少是2
public class ContainerWithMostWater {

	public static void main(String[] args) {
		int[] height = { 2, 3, 4, 5, 18, 17, 6 };
		System.out.println(maxArea(height));
	}

	//1.首先假设我们找到能取最大容积的纵线为 i , j (假定i<j)，那么得到的最大容积 C = min(ai,aj)*(j-i)
	//2.在 j的右端没有一条线会比它高(因为如果存在 k(j<k&&ak>aj,那么由i,k构成的容器的容积也会大于 C'> C
	//3.同理，在i的左边也不会有比它高的线
	//所以我们从两头向中间靠拢，同时更新候选值；在收缩区间的时候优先从 较小的边开始收缩
	public static int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int maxArea = 0;
		while (left < right) {
			maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
			if (height[left] < height[right]) {
				int i = left;
				while (i < right && height[i] <= height[left]) {
					i++;
				}
				left = i;
			} else {
				int i = right;
				while (i > left && height[i] <= height[right]) {
					i--;
				}
				right = i;
			}
		}
		return maxArea;
	}

	@Deprecated
	public static int maxArea2(int[] height) {
		int area = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
			}
		}
		return area;
	}
}
