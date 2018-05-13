package com.lchml.test.leetcode;

/**
 * Created by lc on 2018/05/13.
 */
public class MedianSortedArrays_4 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{5, 6}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int idx1 = 0, idx2 = 0;
        int total = nums1.length + nums2.length;
        int half = total/2;
        int[] nums = new int[half+1];
        int odd = total%2;
        int cur = 0;
        while (cur < nums.length) {
            if (idx2 >= nums2.length) {
                nums[cur] = nums1[idx1];
                idx1++;
            } else if (idx1 >= nums1.length) {
                nums[cur] = nums2[idx2];
                idx2++;
            } else {
                if (nums1[idx1] > nums2[idx2]) {
                    nums[cur] = nums2[idx2];
                    idx2++;
                } else {
                    nums[cur] = nums1[idx1];
                    idx1++;
                }
            }
            cur++;
        }
        cur--;

        if (odd == 1) {
            return nums[cur];
        } else {
            return ((double) (nums[cur-1] + nums[cur])) / 2;
        }
    }
}
