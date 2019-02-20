package com.lchml.test.leetcode;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * Created by lc on 2019/01/22.
 */
public class IsMatch_10 {

    // TODO: 这段代码是抄过来的答案
    public static boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (s.length() > 0 && p.length() == 0) {
            return false;
        }
        if (s.length() == 0 && p.length() > 0) {
            if (p.length() % 2 == 1)
                return false;
            for (int i = 0; i < p.length(); i++) {
                if (i % 2 == 1 && p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        char sch = s.charAt(s.length() - 1);
        char pch = p.charAt(p.length() - 1);
        if (pch == '.') {
            return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
        } else if (pch == '*') {
            boolean result = false;
            char pre = p.charAt(p.length() - 2);
            if (pre == '.') {
                for (int i = s.length(); i >= 0; i--) {
                    result = result || isMatch(s.substring(0, i), p.substring(0, p.length() - 2));
                }
            } else {
                int lastIndex = -1;
                for (int i = s.length() - 1; i >= 0 ; i--) {
                    if (pre != s.charAt(i)) {
                        lastIndex = i;
                        break;
                    }
                }
                for (int i = lastIndex; i < s.length(); i++) {
                    result = result || isMatch(s.substring(0, i + 1), p.substring(0, p.length() - 2));
                }
            }
            return result;
        } else if (pch == sch) {
            return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

}
