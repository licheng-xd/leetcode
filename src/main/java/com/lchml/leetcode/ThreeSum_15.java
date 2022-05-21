package com.lchml.leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
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
