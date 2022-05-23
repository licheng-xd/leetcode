package com.lchml.leetcode;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * Created by lc on 2019/03/14.
 */
public class Question42 {

	public static void main(String[] args) {
		System.out.println(new Question42().trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
	}

	public int trap(int[] height) {
		int len = height.length;
		if (len < 3) {
			return 0;
		}
		int max = height[0];
		int maxIndex = 0;

		int preMax;
		int preMaxIndex;

		int sum = 0;
		// 正向
		for (int i=1; i<len; i++) {
			if (height[i] >= max) {
				preMax = max;
				max = height[i];
				preMaxIndex = maxIndex;
				maxIndex = i;

				for (int j=preMaxIndex+1; j<maxIndex; j++) {
					sum += (preMax - height[j]);
				}
			}
		}

		// 反向
		int maxIndexR = maxIndex;
		max = height[len - 1];
		maxIndex = len - 1;
		for (int i=len - 2; i>=maxIndexR; i--) {
			if (height[i] >= max) {
				preMax = max;
				max = height[i];
				preMaxIndex = maxIndex;
				maxIndex = i;

				for (int j=preMaxIndex-1; j>maxIndex; j--) {
					sum += (preMax - height[j]);
				}
			}
		}

		return sum;
	}

	public int trap2(int[] height) {
		int len = height.length;
		if (len < 3) {
			return 0;
		}
		int[] left = new int[len];
		int[] right = new int[len];
		for (int i=1; i<len; i++) {
			left[i] = Math.max(left[i-1], height[i-1]);
			right[len-1-i] = Math.max(right[len-i], height[len-i]);
		}

		// 计算每一格积水
		int sum = 0;
		for (int i = 0; i < len; i++) {
			if (left[i] > height[i] && right[i] > height[i]) {
				sum += Math.min(left[i], right[i]) - height[i];
			}
		}
		return sum;
	}
}
