package com.lchml.leetcode;

import java.util.Arrays;

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 链接：https://leetcode.cn/problems/search-insert-position
 *
 * Created by lc on 2022/5/21.
 */
public class Question35 {

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (right + left) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        if (target > nums[right]) {
            return right + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 5, 6};
        int target = 0;
        int ret = searchInsert(nums, target);
        System.out.println(ret);
    }
}
