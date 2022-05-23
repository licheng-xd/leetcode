package com.lchml.leetcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * Created by lc on 2019/02/14.
 */
public class Question16 {

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int len = nums.length;
		if (len < 3) {
			return 0;
		}
		int ret = 0;
		int min = Integer.MAX_VALUE;
		for (int i=0; i<len-2; i++) {
			int l = i + 1;
			int r = len - 1;
			while (l < r) {
				int dis = Math.abs(nums[i] + nums[l] + nums[r] - target);
				if (dis == 0) {
					return target;
				}
				if (dis < min) {
					min = dis;
					ret = nums[i] + nums[l] + nums[r];
				}
				if (nums[i] + nums[l] + nums[r] - target > 0) {
					r--;
				} else {
					l++;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Question16().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
	}
}
