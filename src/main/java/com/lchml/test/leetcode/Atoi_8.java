package com.lchml.test.leetcode;

/**
 * Created by lc on 2018/05/14.
 */
public class Atoi_8 {

    public static void main(String[] args) {
        System.out.println(myAtoi("0-1"));
    }

    public static int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        boolean started = false;
        boolean positive = true;
        boolean numStarted = false;
        boolean firstNum = true;
        char[] ret = new char[chars.length];
        int j = 0;
        for (int i=0; i<chars.length; i++) {
            if (!started && chars[i] == ' ') {
                continue;
            }
            started = true;
            if ((chars[i] == '-' || chars[i] == '+') && !numStarted) {
                if (chars[i] == '-')
                    positive = false;
                numStarted = true;
                continue;
            }
            if (chars[i] >= '0' && chars[i] <= '9') {
                numStarted = true;
                if (firstNum && chars[i] == '0') {
                    continue;
                }
                firstNum = false;
                ret[j++] = chars[i];
            } else {
                break;
            }
        }
        if (j == 0) {
            return 0;
        }
        if (j > 10) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        char[] retFil = new char[j];
        System.arraycopy(ret, 0, retFil, 0, j);
        Long retLong = Long.valueOf(new String(retFil));
        retLong = positive ? retLong : -retLong;
        if (retLong > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (retLong < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return retLong.intValue();
    }
}
