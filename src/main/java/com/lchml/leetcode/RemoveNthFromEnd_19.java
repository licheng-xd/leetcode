package com.lchml.leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * Created by lc on 2019/02/15.
 */
public class RemoveNthFromEnd_19 {

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
		System.out.println(new RemoveNthFromEnd_19().removeNthFromEnd(ListNode.fromInts(1, 2, 3, 4, 5), 2));
	}
}
