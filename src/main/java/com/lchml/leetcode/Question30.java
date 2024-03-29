package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 *
 * Created by lc on 2019/03/15.
 */
public class Question30 {

	public List<Integer> findSubstring(String s, String[] words) {
		Set<Integer> ret = new HashSet<>();
		if (words.length == 0 || s.length() == 0 || words[0].length() == 0) {
			return new ArrayList<>(ret);
		}
		int wordLen = words[0].length();
		int totalLen = words.length * wordLen;
		if (totalLen > s.length()) {
			return new ArrayList<>(ret);
		}
		int start = 0;
		int index;

		while (start < s.length() && (index = s.indexOf(words[0], start)) >= 0) {
			start = index + 1;
			while (index >= 0 && index >= start - 1 - totalLen + wordLen) {
				if (index + totalLen <= s.length()) {
					String sub = s.substring(index, index + totalLen);
					// check sub string
					if (check(sub, words)) {
						ret.add(index);
					}
				}
				index = index - wordLen;
			}
		}
		return new ArrayList<>(ret);
	}

	public boolean check(String s, String[] words) {
		int wordLen = words[0].length();
		List<String> split = new ArrayList<>();
		for (int i=0; i<s.length(); i+=wordLen) {
			split.add(s.substring(i, i + wordLen));
		}
		for (String word : words) {
			if (!split.remove(word)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Question30()
				.findSubstring("wordgoodgoodgoodbestword", new String[] {"word","good","best","good"}));
	}
}
