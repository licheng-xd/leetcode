package com.lchml.leetcode;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 *
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 *
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 *
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * Created by lc on 2019/02/14.
 */
public class Question12 {

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
		System.out.println(new Question12().intToRoman(58));
	}
}
