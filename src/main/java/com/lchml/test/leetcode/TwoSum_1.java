package com.lchml.test.leetcode;

/**
 * Created by lc on 2018/05/13.
 */
public class TwoSum_1 {
    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
