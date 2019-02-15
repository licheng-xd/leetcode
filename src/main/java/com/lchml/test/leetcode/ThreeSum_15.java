package com.lchml.test.leetcode;

import java.util.*;

/**
 * Created by lc on 2019/02/14.
 */
public class ThreeSum_15 {

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		Set<Triple> retSet = new HashSet<>();
		List<List<Integer>> ret = new ArrayList<>();
		int len = nums.length;
		if (len < 3) {
			return ret;
		}
		for (int i=0; i<len-2; i++) {
			int l = i + 1;
			int r = len - 1;
			while (l < r) {
				if (nums[i] + nums[l] + nums[r] == 0) {
					Triple triple = new Triple(nums[i], nums[l], nums[r]);
					if (!retSet.contains(triple)) {
						retSet.add(triple);
						ret.add(triple.list());
					}
					l++;
					r--;
				} else if (nums[i] + nums[l] + nums[r] < 0) {
					l++;
				} else {
					r--;
				}
			}
		}
		return ret;
	}

	static class Triple {
		int a;
		int b;
		int c;

		public Triple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public int max() {
			return Math.max(Math.max(a, b), c);
		}

		public int min() {
			return Math.min(Math.min(a, b), c);
		}

		public List<Integer> list() {
			return Arrays.asList(a, b, c);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Triple triple = (Triple) o;
			return this.max() == triple.max() && this.min() == triple.min();
		}

		@Override
		public int hashCode() {
			return  a + b + c;
		}
	}

	public static void main(String[] args) {
		int[] input = {-1, 0, 1, 2, -1, -4};
		System.out.println(new ThreeSum_15().threeSum(input));
	}
}
