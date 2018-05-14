package com.lchml.test.leetcode;

/**
 * Created by lc on 2018/05/14.
 */
public class IntReverse_7 {

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
