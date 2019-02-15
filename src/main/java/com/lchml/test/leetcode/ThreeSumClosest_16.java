package com.lchml.test.leetcode;

import java.util.*;

/**
 * Created by lc on 2019/02/14.
 */
public class ThreeSumClosest_16 {

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
		System.out.println(new ThreeSumClosest_16().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
	}
}
