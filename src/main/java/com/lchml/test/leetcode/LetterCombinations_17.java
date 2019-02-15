package com.lchml.test.leetcode;

import java.util.*;

/**
 * Created by lc on 2019/02/14.
 */
public class LetterCombinations_17 {

	static Map<Character, List<String>> map = new HashMap<>();

	public void init() {
		map.put('2', Arrays.asList("a", "b", "c"));
		map.put('3', Arrays.asList("d", "e", "f"));
		map.put('4', Arrays.asList("g", "h", "i"));
		map.put('5', Arrays.asList("j", "k", "l"));
		map.put('6', Arrays.asList("m", "n", "o"));
		map.put('7', Arrays.asList("p", "q", "r", "s"));
		map.put('8', Arrays.asList("t", "u", "v"));
		map.put('9', Arrays.asList("w", "x", "y", "z"));
	}

	public List<String> letterCombinations(String digits) {
		init();
		List<String> ret = new ArrayList<>();
		if (digits == null || digits.isEmpty()) {
			return ret;
		}

		char[] chars = digits.toCharArray();
		int i = chars.length - 1;
		for (String s : map.get(chars[i])) {
			String str = s + getNext(i);
		}
		// TODO

		return ret;
	}

	public List<String> getNext(int i) {
		if (i > 0) {
			return getNext(i);
		}
		return map.get(i);
	}



	public static void main(String[] args) {
		List list = new ArrayList<String>();
		list.add("abc");
		list.add("123");
		list.add(123);
		System.out.println(list);
	}
}
