package com.lchml.leetcode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * Created by lc on 2019/02/18.
 */
public class Question21 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode ret = null;
		if (l1.val <= l2.val) {
			ret = l1;
			l1 = l1.next;
		} else {
			ret = l2;
			l2 = l2.next;
		}
		ListNode tmp = ret;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				tmp.next = l1;
				l1 = l1.next;
			} else {
				tmp.next = l2;
				l2 = l2.next;
			}
			tmp = tmp.next;
		}
		if (l1 != null) {
			tmp.next = l1;
		} else {
			tmp.next = l2;
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Question21().mergeTwoLists(ListNode.fromInts(1,2,4), ListNode.fromInts(1,3,4)));
	}
}
