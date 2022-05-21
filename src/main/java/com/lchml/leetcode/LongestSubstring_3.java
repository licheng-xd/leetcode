package com.lchml.leetcode;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * Created by lc on 2018/05/13.
 */
public class LongestSubstring_3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("ancsjdtkcadsd"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int idx1 = 0;
        int idx2 = 0;
        String sub = "";
        int maxLength = 0;
        for (; idx2<chars.length; idx2++) {
            if (sub.contains(String.valueOf(chars[idx2]))) {
                idx1 = idx1 + sub.indexOf(chars[idx2]) + 1;
            }
            sub = s.substring(idx1, idx2+1);
            maxLength = sub.length() > maxLength ? sub.length() : maxLength;
        }
        return maxLength;
    }
}
