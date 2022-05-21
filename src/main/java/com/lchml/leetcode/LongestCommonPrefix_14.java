package com.lchml.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
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
