package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * Created by lc on 2019/02/18.
 */
public class Question22 {

	// 回溯算法
	public List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<>();
		if (n > 0) {
			build(ret, "", 0, 0, n);
		}
		return ret;
	}

	public void build(List<String> ret, String src, int open, int close, int n) {
		if (close > open || close > n || open > n) return;
		if (open == n && close == n) {
			ret.add(src);
		}
		build(ret, src + ")", open, close + 1, n);
		build(ret, src + "(", open + 1, close, n);
	}


	public static void main(String[] args) {
		System.out.println(new Question22().generateParenthesis(3));
	}
}
