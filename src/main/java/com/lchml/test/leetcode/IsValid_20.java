package com.lchml.test.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lc on 2019/02/15.
 */
public class IsValid_20 {

	public boolean isValid(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}
		char[] chars = s.toCharArray();
		if (chars.length % 2 != 0) {
			return false;
		}
		String opens ="([{";
		String closes = ")]}";
		if (closes.indexOf(chars[0]) >= 0) {
			return false;
		}
		int i = 1;
		int len = chars.length;
		for (; i<len;) {
			if (closes.indexOf(chars[i]) >= 0) {
				if (closes.indexOf(chars[i]) != opens.indexOf(chars[i - 1])) {
					return false;
				} else {
					System.arraycopy(chars, i+1, chars, i-1, len - i -1);
					len -= 2;
					i--;
				}
			} else {
				i++;
			}
		}
		return len == 0;
	}

	public static void main(String[] args) {
		System.out.println(new IsValid_20().isValid("{[]}"));
		System.out.println(new IsValid_20().isValid("()[]{}"));
		System.out.println(new IsValid_20().isValid("(]"));
	}
}
