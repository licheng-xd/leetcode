package com.lchml.leetcode;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * Created by lc on 2019/02/20.
 */
public class Question27 {
	public int removeElement(int[] nums, int val) {
		int len = nums.length;
		if (len > 0) {
			for (int i=0; i<len;) {
				if (nums[i] == val) {
					if (i + 1 < len) {
						System.arraycopy(nums, i + 1, nums, i, len - i - 1);
					}
					len -= 1;
				} else {
					i++;
				}
			}
		}
		return len;
	}

	public static void main(String[] args) {
		System.out.println(new Question27().removeElement(new int[] {3,2,2,3}, 3));
	}
}
