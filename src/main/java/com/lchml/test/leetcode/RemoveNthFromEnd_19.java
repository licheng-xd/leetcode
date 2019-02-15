package com.lchml.test.leetcode;

/**
 * Created by lc on 2019/02/15.
 */
public class RemoveNthFromEnd_19 {

	public static class ListNode {
	 	int val;
        ListNode next;
	    ListNode(int x) { val = x; }
	}

	// 普通两次遍历方法
	public ListNode removeNthFromEnd(ListNode head, int n) {
		int len = 1;
		ListNode tmp = head;
		while (tmp.next != null) {
			len++;
			tmp = tmp.next;
		}
		ListNode tmp2 = head;
		ListNode pre = null;
		for (int i=0; i<=len && tmp2 != null; i++) {
			if (i == len - n) {
				if (pre == null) {
					return tmp2.next;
				} else {
					pre.next = tmp2.next;
					break;
				}
			}
			pre = tmp2;
			tmp2 = tmp2.next;
		}
		return head;
	}

	// 一次遍历方法，初始化一个大小为n的窗口，当窗口的右边走到底时，左边的就是要删除的节点
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode right = head;
		ListNode left = head;
		ListNode pre = null;
		// right先走n布
		for (int i=1; i<n; i++) {
			right = right.next;
		}

		// left和right同时走
		while (right.next != null) {
			pre = left;
			left = left.next;
			right = right.next;
		}
		if (pre != null) {
			pre.next = left.next;
			return head;
		} else {
			return left.next;
		}
	}

	public static void main(String[] args) {
		new RemoveNthFromEnd_19().removeNthFromEnd(new ListNode(1), 1);
	}
}
