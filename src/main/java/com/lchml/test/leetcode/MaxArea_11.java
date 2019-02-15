package com.lchml.test.leetcode;

/**
 * Created by lc on 2019/02/14.
 */
public class MaxArea_11 {

	public int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;
		if (right == 0) {
			return 0;
		}
		int ret = 0;
		for (;left<right;) {
			if (height[left] < height[right]) {
				ret = Math.max(ret, (right - left) * height[left]);
				left++;
			} else {
				ret = Math.max(ret, (right - left) * height[right]);
				right--;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] input = {1,8,6,2,5,4,8,3,7};
		System.out.println(new MaxArea_11().maxArea(input));
	}
}
