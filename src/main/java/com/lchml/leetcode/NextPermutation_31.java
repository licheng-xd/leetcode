package com.lchml.leetcode;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * Created by lc on 2019/03/16.
 */
public class NextPermutation_31 {

	public void nextPermutation(int[] nums) {
		int len = nums.length;
		if (len > 1) {
			int i=len-2;
			for (; i>=0; i--) {
				if (nums[i] < nums[i + 1]) {
					// 和后面剩下的里面最小的换
					int min = nums[i + 1];
					int swapIndex = i + 1;
					for (int j=i+2; j<len; j++) {
						if (nums[j] < min && nums[j] > nums[i]) {
							min = nums[j];
							swapIndex = j;
						}
					}
					int tmp = nums[i];
					nums[i] = nums[swapIndex];
					nums[swapIndex] = tmp;
					break;
				}
			}

			// 剩下i到len-1排序
			Arrays.sort(nums, i + 1, len);
		}
	}

	public static void main(String[] args) {
		int[] in = new int[] {1, 2, 3};
		new NextPermutation_31().nextPermutation(in);
		System.out.println(Arrays.toString(in));
	}
}
