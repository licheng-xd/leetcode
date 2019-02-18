package com.lchml.test.leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * Created by lc on 2018/05/13.
 */
public class TwoNumbersAdd_2 {

    public static void main(String[] args) {
        System.out.println(new TwoNumbersAdd_2().addTwoNumbers(ListNode.fromInts(2, 4, 3), ListNode.fromInts(5, 6, 4)));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = null;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode curSum = null;
        int upVal = 0; // 进位
        while (cur1 != null && cur2 != null) {
            if (curSum == null) {
                sum = curSum = new ListNode((cur1.val + cur2.val + upVal) % 10);
            } else {
                curSum = curSum.next = new ListNode((cur1.val + cur2.val + upVal) % 10);
            }
            upVal = (cur1.val + cur2.val + upVal) / 10;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        ListNode curRest = null;
        if (cur1 != null) {
            curRest = cur1;
        } else if (cur2 != null) {
            curRest = cur2;
        }
        if (curRest == null) {
            if (upVal == 1) {
                curSum.next = new ListNode(1);
            }
        } else {
            curSum.next = curRest;
            ListNode beforeCur = null;
            while (upVal == 1) {
                if (curRest == null) {
                    beforeCur.next = new ListNode(1);
                    upVal = 0;
                } else {
                    upVal = (curRest.val + 1) / 10;
                    curRest.val = (curRest.val + 1) % 10;
                    beforeCur = curRest;
                    curRest = curRest.next;
                }
            }
        }
        return sum;
    }

}
