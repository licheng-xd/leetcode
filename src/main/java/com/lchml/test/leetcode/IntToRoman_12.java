package com.lchml.test.leetcode;

/**
 * Created by lc on 2019/02/14.
 */
public class IntToRoman_12 {

	public String intToRoman(int num) {
		String ret = "";
		for (int i=1; num/i > 0; i*=10) {
			int tmp = ((num/i)*i - (num/(i*10))*(i*10)) / i;
			if (tmp > 0) {
				ret = getChar(tmp, i) + ret;
			}
		}
		return ret;
	}

	public String getChar(int count, int i) {
		String ret = "";
		if (count == 0) {
			return ret;
		}
		String BASE;
		String BASE5;
		String BASE10;
		if (i == 1) {
			BASE = "I";
			BASE5 = "V";
			BASE10 = "X";
		} else if (i == 10) {
			BASE = "X";
			BASE5 = "L";
			BASE10 = "C";
		} else if (i == 100) {
			BASE = "C";
			BASE5 = "D";
			BASE10 = "M";
		} else {
			BASE = "M";
			BASE5 = "?";
			BASE10 = "?";
		}
		if (count < 4) {
			for (int j=0; j<count; j++) {
				ret = ret + BASE;
			}
		} else if (count == 4) {
			ret = BASE + BASE5;
		} else if (count < 9) {
			ret = BASE5;
			for (int j=0; j<count-5; j++) {
				ret = ret + BASE;
			}
		} else {
			ret = BASE + BASE10;
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new IntToRoman_12().intToRoman(58));
	}
}
