package com.lchml.test.leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * Created by lc on 2018/05/13.
 */
public class Palindrome_5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abbac"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        int maxStart = 0;
        for (int i=1; i<chars.length; i++) {
            // 奇数
            int left = i-1;
            int right = i+1;
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
            }
            left++;
            right--;
            if (right - left + 1 > max) {
                max = right - left + 1;
                maxStart = left;
            }

            // 偶数
            left = i-1;
            right = i;
            while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                left--;
                right++;
            }
            left++;
            right--;
            if (right - left + 1 > max) {
                max = right - left + 1;
                maxStart = left;
            }
        }
        return s.substring(maxStart, maxStart + max);
    }
}
