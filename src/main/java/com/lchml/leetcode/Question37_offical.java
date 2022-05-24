package com.lchml.leetcode;

import java.util.*;

/**
 * 解数独
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 链接：https://leetcode.cn/problems/sudoku-solver
 *
 * Created by lc on 2022/5/23.
 */
public class Question37_offical {

    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        while (true) {
            boolean modified = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                        if ((mask & (mask - 1)) == 0) {
                            int digit = Integer.bitCount(mask - 1);
                            flip(i, j, digit);
                            board[i][j] = (char) (digit + '0' + 1);
                            modified = true;
                        }
                    }
                }
            }
            if (!modified) {
                break;
            }
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs(board, pos + 1);
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }

    public static void main(String[] args) {
        char[][] board1 = new char[][] {{'5','3','.','.','7','.','.','.','.'}
                            ,{'6','.','.','1','9','5','.','.','.'}
                            ,{'.','9','8','.','.','.','.','6','.'}
                            ,{'8','.','.','.','6','.','.','.','3'}
                            ,{'4','.','.','8','.','3','.','.','1'}
                            ,{'7','.','.','.','2','.','.','.','6'}
                            ,{'.','6','.','.','.','.','2','8','.'}
                            ,{'.','.','.','4','1','9','.','.','5'}
                            ,{'.','.','.','.','8','.','.','7','9'}};

        char[][] board2 = new char[][] {{'.','.','9','7','4','8','.','.','.'}
                ,{'7','.','.','.','.','.','.','.','.'}
                ,{'.','2','.','1','.','9','.','.','.'}
                ,{'.','.','7','.','.','.','2','4','.'}
                ,{'.','6','4','.','1','.','5','9','.'}
                ,{'.','9','8','.','.','.','3','.','.'}
                ,{'.','.','.','8','.','3','.','2','.'}
                ,{'.','.','.','.','.','.','.','.','6'}
                ,{'.','.','.','2','7','5','9','.','.'}};

        char[][] board3 = new char[][] {{'.','.','.','2','.','.','.','6','3'}
                ,{'3','.','.','.','.','5','4','.','1'}
                ,{'.','.','1','.','.','3','9','8','.'}
                ,{'.','.','.','.','.','.','.','9','.'}
                ,{'.','.','.','5','3','8','.','.','.'}
                ,{'.','3','.','.','.','.','.','.','.'}
                ,{'.','2','6','3','.','.','5','.','.'}
                ,{'5','.','3','7','.','.','.','.','8'}
                ,{'4','7','.','.','.','1','.','.','.'}};
        
        new Question37_offical().solveSudoku(board2);
    }

}
