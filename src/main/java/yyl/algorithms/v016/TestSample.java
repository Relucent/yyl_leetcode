package yyl.algorithms.v016;

/**
 * 样本数据
 */
enum TestSample {
	S0(new int[] { 1, 1, 1 }, 0, 3), //
	S1(new int[] { -1, 2, 1, -4 }, 1, 2), //
	S2(new int[] { -55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33 }, 0, 0), //
	S3(new int[] { 0, 2, 1, -3 }, 1, 0),//
	;

	private final int[] nums;
	private final int target;
	private final int expected;

	TestSample(int[] nums, int target, int expected) {
		this.nums = nums;
		this.target = target;
		this.expected = expected;
	}

	public int[] nums() {
		int[] clone = new int[nums.length];
		System.arraycopy(nums, 0, clone, 0, nums.length);
		return clone;
	}

	public int target() {
		return target;
	}

	public int expected() {
		return expected;
	}
}
