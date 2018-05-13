package com.lchml.test.leetcode;

/**
 * Created by lc on 2018/05/13.
 */
public class TwoNumbersAdd_2 {

    public static void main(String[] args) {

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
