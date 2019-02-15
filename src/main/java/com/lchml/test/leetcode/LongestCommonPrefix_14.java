package com.lchml.test.leetcode;

/**
 * Created by lc on 2019/02/14.
 */
public class LongestCommonPrefix_14 {

	public String longestCommonPrefix(String[] strs) {
		int len = strs.length;
		if (len == 0) {
			return "";
		}
		if (len == 1) {
			return strs[0];
		}
		int index = 0;
		for (; index<strs[0].length(); index++) {
			for (int i = 1; i < len; i++) {
				if (index >= strs[i].length() || strs[0].charAt(index) != strs[i].charAt(index)) {
					return strs[0].substring(0, index);
				}
			}
		}
		return strs[0];
	}


	public static void main(String[] args) {
		System.out.println(new LongestCommonPrefix_14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
	}
}
