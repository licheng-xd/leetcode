package com.lchml.test.leetcode;

/**
 * Created by lc on 2019/02/14.
 */
public class RomanToInt_13 {
	public int romanToInt(String s) {
		int ret = 0;
		char[] chars = s.toCharArray();
		int len = chars.length - 1;
		while (len>=0) {
			switch (chars[len]) {
				case 'I': {
					ret += 1;
					len--;
					break;
				}
				case 'V': {
					if (len-1 >= 0 && chars[len -1] == 'I') {
						ret += 4;
						len -= 2;
					} else {
						ret += 5;
						len--;
					}
					break;
				}
				case 'X': {
					if (len-1 >= 0 && chars[len -1] == 'I') {
						ret += 9;
						len -= 2;
					} else {
						ret += 10;
						len--;
					}
					break;
				}
				case 'L': {
					if (len-1 >= 0 && chars[len -1] == 'X') {
						ret += 40;
						len -= 2;
					} else {
						ret += 50;
						len--;
					}
					break;
				}
				case 'C': {
					if (len-1 >= 0 && chars[len -1] == 'X') {
						ret += 90;
						len -= 2;
					} else {
						ret += 100;
						len--;
					}
					break;
				}
				case 'D': {
					if (len-1 >= 0 && chars[len -1] == 'C') {
						ret += 400;
						len -= 2;
					} else {
						ret += 500;
						len--;
					}
					break;
				}
				case 'M': {
					if (len-1 >= 0 && chars[len -1] == 'C') {
						ret += 900;
						len -= 2;
					} else {
						ret += 1000;
						len--;
					}
					break;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new RomanToInt_13().romanToInt("MCMXCIV"));
	}
}
