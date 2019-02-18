package com.lchml.test.leetcode;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
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
