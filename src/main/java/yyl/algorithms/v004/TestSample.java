package yyl.algorithms.v004;

/**
 * 样本数据
 */
enum TestSample {
	S1(new int[] { 1, 3 }, new int[] { 2 }, 2.0), //
	S2(new int[] {}, new int[] { 2, 3 }, 2.5),//
	;
	private final int[] nums1;
	private final int[] nums2;
	private final double expected;

	private TestSample(int[] nums1, int[] nums2, double expected) {
		this.nums1 = nums1;
		this.nums2 = nums2;
		this.expected = expected;
	}

	public int[] nums1() {
		return clone(nums1);
	}

	public int[] nums2() {
		return clone(nums2);
	}

	public double expected() {
		return expected;
	}

	private int[] clone(int[] input) {
		int[] clone = new int[input.length];
		System.arraycopy(input, 0, clone, 0, input.length);
		return clone;
	}
}
