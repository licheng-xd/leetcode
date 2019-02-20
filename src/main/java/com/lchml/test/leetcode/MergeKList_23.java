package com.lchml.test.leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * Created by lc on 2019/02/18.
 */
public class MergeKList_23 {


	// 二分法
	public ListNode mergeKLists(ListNode[] lists) {
		int len = lists.length;
		if (len == 0) {
			return null;
		} else if (len == 1) {
			return lists[0];
		} else if (len == 2) {
			return mergeTwoLists(lists[0], lists[1]);
		}
		if (len % 2 == 0) {
			ListNode[] half = new ListNode[len/2];
			for (int i=0; i<half.length; i++) {
				half[i] = mergeTwoLists(lists[i], lists[len-1-i]);
			}
			return mergeKLists(half);
		} else {
			ListNode[] half = new ListNode[len/2 + 1];
			for (int i=0; i<len/2; i++) {
				half[i] = mergeTwoLists(lists[i], lists[len-1-i]);
			}
			half[len/2] = lists[len/2];
			return mergeKLists(half);
		}
	}

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
		System.out.println(new MergeKList_23().mergeKLists(
				new ListNode[] {ListNode.fromInts(1, 4, 5), ListNode.fromInts(1, 3, 4), ListNode.fromInts(2, 6)}));
	}
}
