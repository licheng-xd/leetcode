package com.lchml.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
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
