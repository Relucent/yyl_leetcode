package yyl.algorithms.v015;

import java.util.List;

public class Test01 {
	public static void main(String[] args) {

		int[] sample = TestSample.getSample();

		long l = System.currentTimeMillis();

		List<List<Integer>> result = new N3Sum.Solution().threeSum(sample);

		System.out.println(System.currentTimeMillis() - l);

		System.out.println(result.size());
	}

}
