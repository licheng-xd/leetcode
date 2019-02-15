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
		int i = 0;
		getNext("", chars, i, ret);
		return ret;
	}

	private void getNext(String pre, char[] chars, int i, List<String> ret) {
		if (i >= chars.length) {
			ret.add(pre);
			return;
		}
		for (String s : map.get(chars[i])) {
			getNext(pre + s, chars, i+1, ret);
		}
	}

	public static List<String> letterCombinations2(String digits) {
		if(digits.length() == 0) return new ArrayList<>();
		char[][] numChar = new char[][]{
				{'a','b','c'},
				{'d','e','f'},
				{'g','h','i'},
				{'j','k','l'},
				{'m','n','o'},
				{'p','q','r','s'},
				{'t','u','v'},
				{'w','x','y','z'}
		};
		List<String> result = new ArrayList<>();
		char[] tem  = new char[digits.length()];
		char[][] temp = new char[digits.length()][];
		for(int i = 0; i < digits.length(); i ++){
			temp[i] = numChar[digits.charAt(i) - '2'];
		}
		build(result, temp, tem, 0);
		return result;

	}

	private static void build(List<String> result, char[][] temp, char[] tem, int idx){
		for(char tt : temp[idx]){
			tem[idx] = tt;
			if(idx == temp.length - 1){
				result.add(new String(tem));
			}else{
				build(result, temp, tem, idx + 1);
			}
		}
	}


	public static void main(String[] args) {
		System.out.println(new LetterCombinations_17().letterCombinations2("235"));
	}
}
