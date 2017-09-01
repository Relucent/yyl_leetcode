package yyl.algorithms.v015;

import java.util.List;

import yyl.algorithms.v015.N3Sum.SolutionForkJoin;

public class Test02 {
	public static void main(String[] args) {

		int[] sample = TestSample.getSample();

		long l = System.currentTimeMillis();

		List<List<Integer>> result = new SolutionForkJoin().threeSum(sample);

		System.out.println(System.currentTimeMillis() - l);

		System.out.println(result.size());
	}

}
