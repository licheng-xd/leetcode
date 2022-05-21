package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiliang on 2019/02/20.
 */
public class IsMatch_10_2 {

	public static void main(String[] args) {
		System.out.println(new IsMatch_10_2().isMatch("     ", ".*"));
	}

	public IsMatch_10_2() {
	}

	public boolean isMatch(String s, String p) {
		IsMatch_10_2 kit = new IsMatch_10_2(p);
		kit.compile();

		return kit.matches(s);
	}

	private boolean matches(String s) {
		if (head == null) {
			return false;
		}
		this.s = s;
		int startIndex = 0;
		Match temp = head;
		while (true) {
			if (startIndex != -2 && temp.match(startIndex)) {
				startIndex = temp.range.end;
				temp = temp.next;
				if (temp == null) {
					return true;
				}
			} else if (temp.previous != null) {
				if (temp.previous.canBack()) {
					temp.previous.range.end--;
					temp = temp.previous;
					startIndex = temp.range.end;
				} else {
					startIndex = -2;
					temp = temp.previous;
					temp.matched = false;
				}
			} else {
				return false;
			}
		}

	}

	private IsMatch_10_2(String p) {
		this.p = p;
	}

	private void compile() {
		List<String> strings = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			if (c == '.') {
				if (i == p.length() - 1) {
					//结束了
					if (sb.length() > 0) {
						strings.add(sb.toString());
						sb = new StringBuilder();
					}
					strings.add(".");
				} else {
					char next = p.charAt(i + 1);
					if (next == '*') {
						//.*
						if (sb.length() > 0) {
							strings.add(sb.toString());
							sb = new StringBuilder();
						}
						strings.add(".*");
						i++;
					} else {
						if (sb.length() > 0) {
							strings.add(sb.toString());
							sb = new StringBuilder();
						}
						strings.add(".");
					}
				}
			} else if (c != '*') {
				if (i == p.length() - 1) {
					sb.append(c);
					strings.add(sb.toString());
				} else {
					char next = p.charAt(i + 1);
					if (next == '*') {
						//.*
						if (sb.length() > 0) {
							strings.add(sb.toString());
							sb = new StringBuilder();
						}
						strings.add(c + "*");
						i++;
					} else {
						sb.append(c);
					}
				}
			} else {
				return;
			}
		}

		Match match;
		Match head = new Start();
		Match prev = head;
		for (String string : strings) {
			if (string.equals(".")) {
				match = new AnyCharMatch();
			} else if (string.equals(".*")) {
				match = new ZeroOrMoreMatch(new AnyChar());
				((ZeroOrMoreMatch) match).pattern = string;
			} else if (string.endsWith("*")) {
				match = new ZeroOrMoreMatch(new OneChar(string.charAt(0)));
				((ZeroOrMoreMatch) match).pattern = string;
			} else {
				match = new StringMatch(string);
			}

			prev.next = match;
			match.previous = prev;
			prev = match;
		}
		prev.next = new End();
		this.head = head;
	}

	private Match head;
	private String s;
	private String p;

	class Match {
		Match previous;
		Match next;
		Range range = new Range(0, 0);
		boolean matched = false;

		boolean match(int start) {
			if (!matched) {
				range.start = start;
				range.end = start;
				matched = match0();
				return matched;
			} else {
				return true;
			}
		}

		boolean match0() {
			return false;
		}

		boolean canBack() {
			return false;
		}

		@Override
		public String toString() {
			if (range.start > 0 && range.end < s.length()) {
				return s.substring(range.start, range.end);
			} else {
				return null;
			}
		}
	}

	class Range {
		int start;
		int end;

		Range(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	class ZeroOrMoreMatch extends Match {
		Char _char;
		String pattern;

		ZeroOrMoreMatch(Char _char) {
			this._char = _char;
		}

		@Override
		boolean match0() {
			int i = range.start;
			for (; i < s.length(); i++) {
				char c = s.charAt(i);
				if (_char.match(c)) {
					range.end++;
				} else {
					return true;
				}
			}
			return true;
		}

		@Override
		boolean canBack() {
			return range.start < range.end;
		}

		@Override
		public String toString() {
			return pattern + ": " + super.toString();
		}
	}

	class StringMatch extends Match {
		String piece;

		StringMatch(String piece) {
			this.piece = piece;
		}

		@Override
		boolean match0() {
			if (s.startsWith(piece, range.start)) {
				range.end += piece.length();
				return true;
			}
			return false;
		}

		@Override
		boolean canBack() {
			return false;
		}

		@Override
		public String toString() {
			return piece + ": " + super.toString();
		}
	}

	class AnyCharMatch extends Match {

		@Override
		boolean match0() {
			if (range.start < s.length() && range.end < s.length()) {
				range.end++;
				return true;
			} else {
				return false;
			}

		}

		@Override
		boolean canBack() {
			return false;
		}

		@Override
		public String toString() {
			return ".: " + super.toString();
		}
	}

	class Start extends Match {
		Start() {
			this.range = new Range(0, 0);
		}

		@Override
		boolean match0() {
			return range.start == 0;
		}

		@Override
		public String toString() {
			return "[START]";
		}
	}

	class End extends Match {
		End() {
			this.range = new Range(-1, -1);
		}

		@Override
		boolean match0() {
			return range.end == s.length();
		}

		@Override
		public String toString() {
			return "[END]";
		}
	}

	interface Char {
		boolean match(char c);
	}

	class AnyChar implements Char {

		@Override
		public boolean match(char c) {
			return true;
		}
	}

	class OneChar implements Char {
		char aChar;

		OneChar(char aChar) {
			this.aChar = aChar;
		}

		@Override
		public boolean match(char c) {
			return c == aChar;
		}
	}
}
