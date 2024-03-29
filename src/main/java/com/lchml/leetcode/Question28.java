package com.lchml.leetcode;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * Created by lc on 2019/02/20.
 */
public class Question28 {

	public int strStr(String haystack, String needle) {
		char[] src = haystack.toCharArray();
		char[] tgt = needle.toCharArray();
		if (tgt.length == 0) {
			return 0;
		}
		if (tgt.length == src.length) {
			return haystack.equals(needle) ? 0 : -1;
		}
		for (int i=0; i<src.length - tgt.length + 1; i++) {
			int j=0;
			for (;j<tgt.length; j++) {
				if (src[i + j] != tgt[j]) {
					break;
				}
			}
			if (j == tgt.length) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new Question28().strStr("mississippi", "pi"));
	}
}
