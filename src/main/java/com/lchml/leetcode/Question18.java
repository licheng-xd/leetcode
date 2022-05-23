package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * Created by lc on 2019/02/14.
 */
public class Question18 {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int n = nums.length;
		if(n < 4) {
			return res;
		}
		int t, tt, f, l;
		for(int i = 0; i < n - 3; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			t = target - nums[i];
			for(int j = i + 1; j < n -2; j++) {
				tt = t - nums[j];
				if(j > i + 1 && nums[j] == nums[j-1]) {
					continue;
				}
				f = j + 1;
				l = n - 1;
				while(f < l) {
					if(nums[f] + nums[l] == tt) {
						List<Integer> li = new ArrayList<>();
						li.add(nums[i]);
						li.add(nums[j]);
						li.add(nums[f]);
						li.add(nums[l]);
						res.add(li);
						while(f < l && nums[f] == nums[f+1]) {
							f++;
						}
						f++;
						l--;
					}else if(nums[f] + nums[l] < tt) {
						f++;
					}else {
						l--;
					}
				}
			}
		}
		return res;
	}


	public static void main(String[] args) {
		int[] input = {1, 0, -1, 0, -2, 2};
		System.out.println(new Question18().fourSum(input, 0));
	}
}
