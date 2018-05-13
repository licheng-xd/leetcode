package com.lchml.test.leetcode;

/**
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
