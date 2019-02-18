package com.lchml.test.leetcode;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
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
