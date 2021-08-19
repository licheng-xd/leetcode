package com.lchml.test.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 *
 * Created by lc on 2021/03/12.
 */
public class MinCut_132 {

	public static void main(String[] args) {
		isPalindrome("abccdadccef");
	}

	public static void isPalindrome(String s) {
		int n = s.length();
		boolean[][] g = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			Arrays.fill(g[i], true);
		}

		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j) {
				g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
			}
		}

		for (int i=0; i<g.length; i++) {
			System.out.println(Arrays.toString(g[i]));
		}
		System.out.println(g[2][8]);
	}

	public static int minCut2(String s) {
		int n = s.length();
		boolean[][] g = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			Arrays.fill(g[i], true);
		}

		for (int i=0; i<g.length; i++) {
			System.out.println(Arrays.toString(g[i]));
		}

		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j) {
				g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
			}
		}

		for (int i=0; i<g.length; i++) {
			System.out.println(Arrays.toString(g[i]));
		}

		int[] f = new int[n];
		Arrays.fill(f, Integer.MAX_VALUE);
		for (int i = 0; i < n; ++i) {
			if (g[0][i]) {
				f[i] = 0;
			} else {
				for (int j = 0; j < i; ++j) {
					if (g[j + 1][i]) {
						f[i] = Math.min(f[i], f[j] + 1);
					}
				}
			}
		}

		System.out.println(Arrays.toString(f));
		return f[n - 1];
	}


	public static int minCut(String s) {
		int len = s.length();
		if (len == 0 || len == 1) {
			System.out.println(s);
			return 0;
		}
		int[] se = findLongestPalindrome(s);
		int left = se[0];
		int right = se[1];
		if (left == 0) {
			if (right == len) {
				return 0;
			} else {
				System.out.println(s.substring(left, right) + "  " + s.substring(right, len));
				return 1 + minCut(s.substring(right, len));
			}
		} else {
			if (right == len) {
				System.out.println(s.substring(0, left) + "  " + s.substring(left, right));
				return 1 + minCut(s.substring(0, left));
			} else {
				System.out.println(s.substring(0, left) + "  " + s.substring(left, right) + "  " + s.substring(right, len));
				return 2 + minCut(s.substring(0, left)) + minCut(s.substring(right, len));
			}
		}
	}

	public static int[] findLongestPalindrome(String s) {
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
		return new int[]{maxStart, maxStart + max};
	}
}
