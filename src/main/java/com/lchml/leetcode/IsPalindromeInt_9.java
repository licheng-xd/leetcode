package com.lchml.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * Created by lc on 2018/05/14.
 */
public class IsPalindromeInt_9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1000000001));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x <= 9) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        long div = 10;
        while (x / div > 0) {
            int re = (int) (x % div / (div / 10));
            list.add(re);
            div = div * 10;
        }
        list.add((int) (x % div / (div / 10)));
        for (int i= 0; i<list.size()/2; i++) {
            if (list.get(i).intValue() != list.get(list.size() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
