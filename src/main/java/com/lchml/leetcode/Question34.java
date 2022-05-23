package com.lchml.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶： 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 *
 * Created by lc on 2022/5/21.
 */
public class Question34 {

    public static int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == target) {
                end = i;
                if (start == -1) {
                    start = i;
                }
            }
        }
        return new int[] {start, end};
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 2};
        int target = 2;
        int[] ret = binarySearch(nums, target);
        System.out.println(Arrays.toString(ret));
    }

    // 通过二分法定位target
    public static int[] binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                int start = middle, end = middle;
                int i=1;
                while (middle - i >= left || middle + i <= right) {
                    if (middle - i >= left && nums[middle - i] == target) {
                        start = middle - i;
                    }
                    if (middle + i <= right && nums[middle + i] == target) {
                        end = middle + i;
                    }
                    i++;
                }
                return new int[] {start, end};
            }
        }
        return new int[] {-1, -1};
    }
}
