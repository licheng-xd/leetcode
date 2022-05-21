package com.lchml.leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * Created by lc on 2019/02/19.
 */
public class SwapPairs_24 {

	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode pre = null;
		ListNode now = head;
		ListNode suf = now.next;
		while (suf != null) {
			now.next = suf.next;
			suf.next = now;
			if (pre != null) {
				pre.next = suf;
			} else {
				head = suf;
			}
			pre = now;
			now = now.next;
			if (now != null) {
				suf = now.next;
			} else {
				break;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		System.out.println(new SwapPairs_24().swapPairs(ListNode.fromInts(1, 2, 3, 4)));
	}
}
