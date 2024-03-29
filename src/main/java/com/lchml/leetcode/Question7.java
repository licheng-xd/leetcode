package com.lchml.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * Created by lc on 2018/05/14.
 */
public class Question7 {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        if (x >= -9 && x <= 9) {
            return x;
        }
        boolean isPositive = x >= 0;
        long abs = Math.abs((long) x);
        if (abs > Integer.MAX_VALUE || abs < Integer.MIN_VALUE) {
            return 0;
        }
        char[] xchars = String.valueOf(abs).toCharArray();
        char[] rev = new char[xchars.length];
        int j = 0;
        for (int i=xchars.length-1; i>=0; i--) {
            rev[j++] = xchars[i];
        }
        Long retLong = Long.valueOf(new String(rev));
        if (!isPositive) {
            retLong = -retLong;
        }
        if (retLong > Integer.MAX_VALUE || retLong < Integer.MIN_VALUE) {
            return 0;
        }
        return retLong.intValue();
    }
}
