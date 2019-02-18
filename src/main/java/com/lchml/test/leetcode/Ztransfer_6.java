package com.lchml.test.leetcode;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * Created by lc on 2018/05/13.
 */
public class Ztransfer_6 {
    public static void main(String[] args) {
        System.out.println(convert("ABCDE", 4));
    }

    public static String convert(String s, int numRows) {
        if (s == null || s.length() <= 1 || numRows == 1 || s.length() == numRows) {
            return s;
        }
        char[] chars = s.toCharArray();
        int tmp = chars.length / (numRows + numRows - 2);
        int tmpMode = chars.length % (numRows + numRows - 2);
        int numCols = tmp + tmp * (numRows - 2) + tmpMode % numRows + 1;
        char[][] z = new char[numRows][numCols];
        int idxRow = 0;
        int idxCol = 0;
        for (int i=0; i<chars.length; i++) {
            if (idxCol % (numRows - 1) == 0) {
                z[idxRow][idxCol] = chars[i];
                idxRow++;
                if (idxRow == numRows) {
                    idxRow -= 2;
                    idxCol++;
                }
            } else {
                z[idxRow][idxCol] = chars[i];
                idxRow--;
                idxCol++;
            }
        }

        char[] ret = new char[chars.length];
        int idxRet = 0;
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                if (z[i][j] != '\u0000') {
                    ret[idxRet++] = z[i][j];
                }
            }
        }
        return new String(ret);
    }
}
