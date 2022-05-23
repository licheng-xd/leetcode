package com.lchml.leetcode;

import java.util.Arrays;

/**
 * 有效的数独
 *
 * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 *
 * 链接：https://leetcode.cn/problems/valid-sudoku
 *
 * Created by lc on 2022/5/23.
 */
public class Question36 {

    public static boolean isValidSudoku(char[][] board) {
        int[][] ret1 = new int[9][9];
        int[][] ret2 = new int[9][9];
        int[][] ret3 = new int[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.') {
                    int num = Character.getNumericValue(board[i][j]);
                    // 横竖
                    ret1[i][num - 1]++;
                    ret2[j][num - 1]++;
                    // 3*3
                    int r = i / 3;
                    int t = j / 3;
                    ret3[r * 3 + t][num - 1]++;
                    if (ret1[i][num - 1] > 1 || ret2[j][num - 1] > 1 || ret3[r * 3 + t][num - 1] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'5','3','.','.','7','.','.','.','.'}
                            ,{'6','.','.','1','9','5','.','.','.'}
                            ,{'.','9','8','.','.','.','.','6','.'}
                            ,{'8','.','.','.','6','.','.','.','3'}
                            ,{'4','.','.','8','.','3','.','.','1'}
                            ,{'7','.','.','.','2','.','.','.','6'}
                            ,{'.','6','.','.','.','.','2','8','.'}
                            ,{'.','.','.','4','1','9','.','.','5'}
                            ,{'.','.','.','.','8','.','.','7','9'}};

        System.out.println(isValidSudoku(board));
    }
}
