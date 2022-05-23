package com.lchml.leetcode;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * Created by lc on 2019/02/20.
 */
public class Question29 {

	public int divide(int dividend, int divisor) {
		if (dividend == 0) {
			return 0;
		}
		int over = (int) (((long) 2<<31 - 1) - 1);
		if (divisor == 0) {
			return over;
		}
		long dividendL = (long) dividend;
		long divisorL = (long) divisor;
		boolean positive = true;
		if ((dividendL > 0 && divisorL < 0) || (dividendL < 0 && divisorL > 0)) {
			positive = false;
		}
		dividendL = Math.abs(dividendL);
		divisorL = Math.abs(divisorL);
		if (divisorL > dividendL) {
			return 0;
		}
		if (divisorL == dividendL) {
			return positive ? 1 : -1;
		}
		long divisorLOrigin = divisorL;
		int step = 0;
		while (divisorL << 1 < dividendL) {
			divisorL  = divisorL << 1;
			step++;
		}
		long sum = 1L << step;
		long left = dividendL - divisorL;
		if (left >= divisorLOrigin) {
			sum = sum + divide((int) left, (int) divisorLOrigin);
		}
		if (positive && sum > over) {
			return over;
		}
		if (!positive && sum < over + 1) {
			return over + 1;
		}
		return (int) (positive ? sum : 0 - sum);
	}

	public static void main(String[] args) {
		System.out.println(new Question29().divide(-17, -3));
	}
}
