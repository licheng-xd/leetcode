package com.lchml.leetcode;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * Created by lc on 2019/02/20.
 */
public class Question25 {

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		if (k == 1) {
			return head;
		}
		ListNode pre = null;
		ListNode[] win = new ListNode[k];
		win[0] = head;
		for (int i=1; i<win.length; i++) {
			win[i] = win[i-1].next;
			if (win[i] == null) {
				return head;
			}
		}
		boolean c = true;
		while (c) {
			for (int i=0; i<win.length; i++) {
				if (i == 0) {
					win[i].next = win[win.length - 1].next;
				} else {
					win[i].next = win[i - 1];
				}
			}
			if (pre == null) {
				pre = win[win.length - 1];
				head = pre;
			} else {
				pre.next = win[win.length - 1];
			}
			pre = win[0];
			ListNode now = win[0].next;
			if (now == null) {
				break;
			}
			win[0] = now;
			for (int i=1; i<win.length; i++) {
				win[i] = win[i-1].next;
				if (win[i] == null) {
					c = false;
					break;
				}
			}
		}
		return head;
	}

	public static void main(String[] args) {
		System.out.println(new Question25().reverseKGroup(ListNode.fromInts(1, 2, 3, 4, 5), 3));
	}
}
