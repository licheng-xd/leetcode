package com.lchml.test.leetcode;

/**
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
