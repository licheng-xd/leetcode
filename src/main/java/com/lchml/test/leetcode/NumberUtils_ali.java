package com.lchml.test.leetcode;

import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * 数值相关的工具类。alibaba笔试题
 *
 * Created by lc on 2019/02/14.
 */
public class NumberUtils_ali {

	/**
	 * 利用输入的非负长整型数组，拼接出最大的正整数。
	 *
	 * <p>具体要求如下：</p>
	 *
	 * <ul>
	 *     <li>输入的整数都是非负整数。</li>
	 *     <li>单个整数不能被拆分。</li>
	 *     <li>拼接的结果可以保证不会超出长整数范围。</li>
	 * </ul>
	 *
	 * <p>例如，输入<code>{2, 51, 0, 9}</code>，返回<code>95120</code>。</p>
	 *
	 * @param numbers 非负的长整型数组
	 * @return 由输入拼接而成的最大正整数
	 */
	public static long largestNumber(long[] numbers) {
		Set<Num> set = new TreeSet<>();
		for (long n : numbers) {
			set.add(new Num(n));
		}
		int maxLen = String.valueOf(Double.MAX_VALUE).length();
		StringBuilder retStr = new StringBuilder();
		for (Num num : set) {
			if (retStr.length() + num.s.length() > maxLen) {
				break;
			}
			retStr.append(num.s);
		}
		return Double.valueOf(retStr.toString()).longValue();
	}

	static class Num implements Comparable<Num> {
		String s;

		Num(long n) {
			this.s = String.valueOf(n);
		}

		@Override
		public int compareTo(Num o) {
			int i = 0;
			int j = 0;
			for (; i<Math.max(s.length(), o.s.length()); i++, j++) {
				if (s.length() != o.s.length()) {
					if (i == s.length()) {
						i = 0;
					}
					if (j == o.s.length()) {
						j = 0;
					}
				}
				int a = Integer.valueOf(s.substring(i, i+1));
				int b = Integer.valueOf(o.s.substring(j, j+1));
				if (a > b) {
					return -1;
				} else if (a < b) {
					return 1;
				}
			}
			return 1;
		}
	}

	@Test
	public void testLargestNumber() {
		System.out.println(largestNumber(new long[] {2, 51, 0, 9}));
	}

	@Test
	public void testLargestNumber2() {
		System.out.println(largestNumber(new long[] {Long.MAX_VALUE, 51, 0, 9}));
	}

	@Test
	public void testLargestNumber7() {
		System.out.println(largestNumber(new long[] {Long.MAX_VALUE/10, 51, 0, 9}));
	}

	@Test
	public void testLargestNumber8() {
		System.out.println(largestNumber(new long[] {Long.MAX_VALUE/10, 51, 0, 8}));
	}

	@Test
	public void testLargestNumber3() {
		System.out.println(largestNumber(new long[] {0, 0, 0, 0}));
	}

	@Test
	public void testLargestNumber4() {
		System.out.println(largestNumber(new long[] {1, 0, 0, 0}));
	}

	@Test
	public void testLargestNumber5() {
		System.out.println(largestNumber(new long[] {765, 7654}));
	}


	@Test
	public void testLargestNumber6() {
		System.out.println(largestNumber(new long[] {765, 7658}));
	}


}
