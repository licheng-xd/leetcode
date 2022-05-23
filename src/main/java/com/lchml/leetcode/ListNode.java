package com.lchml.leetcode;

/**
 * Created by lc on 2019/02/18.
 */
public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder().append(this.val);
		ListNode node = this.next;
		while (node != null) {
			ret.append("->").append(node.val);
			node = node.next;
		}
		return ret.toString();
	}

	public static ListNode fromInts(int... ints) {
		if (ints.length == 0) {
			return null;
		}
		ListNode ret = new ListNode(ints[0]);
		ListNode tmp = ret;
		for (int i=1; i<ints.length; i++) {
			ListNode node = new ListNode(ints[i]);
			tmp.next = node;
			tmp = node;
		}
		return ret;
	}
}
