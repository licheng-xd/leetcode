package com.lchml.leetcode;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * Created by lc on 2019/03/20.
 */
public class Question32 {

	public int longestValidParentheses(String s) {
		char[] chars = s.toCharArray();
		return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
	}

	private static int calc(char[] chars, int i, int flag, int end, char cTem){
		int max = 0, sum = 0, currLen = 0,validLen = 0;
		for (;i != end; i += flag) {
			sum += (chars[i] == cTem ? 1 : -1);
			currLen ++;
			if (sum < 0) {
				max = max > validLen ? max : validLen;
				sum = 0;
				currLen = 0;
				validLen = 0;
			} else if(sum == 0) {
				validLen = currLen;
			}
		}
		return max > validLen ? max : validLen;
	}

	public static void main(String[] args) {
		System.out.println(new Question32().longestValidParentheses("()))))((())"));
	}
}
