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
public class Question37 {

    static Deque<Triple> stack = new ArrayDeque<>();

    static boolean finish = false;

    static class Triple {
        int a;

        int b;

        int c;

        public Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triple triple = (Triple) o;
            return a == triple.a && b == triple.b && c == triple.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

    public static void solveSudoku(char[][] board) {
        // 初始化
        int[][][] ret = new int[3][9][9]; // 这个三维数组可以压缩成用bit位表示的数字，节省空间
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.') {
                    int num = Character.getNumericValue(board[i][j]);
                    // 横竖
                    ret[0][i][num - 1]++;
                    ret[1][j][num - 1]++;
                    // 3*3
                    int r = i / 3;
                    int t = j / 3;
                    ret[2][r * 3 + t][num - 1]++;
                    if (ret[0][i][num - 1] > 1 || ret[1][j][num - 1] > 1 || ret[2][r * 3 + t][num - 1] > 1) {
                        System.out.println("invalid sudoku");
                        break;
                    }
                }
            }
        }
        // 求解
        solve(board, ret);
    }

    public static void insert(char[][] board, int[][][] ret, Triple t) {
        ret[0][t.a][t.c] = 1;
        ret[1][t.b][t.c] = 1;
        ret[2][t.a / 3 * 3 + t.b / 3][t.c] = 1;
        board[t.a][t.b] = Character.forDigit(t.c + 1, 10);
        stack.addLast(t);
        System.out.println("入栈[" + t.a + "," + t.b + "]: " + board[t.a][t.b]);
    }

    public static void popUntil(char[][] board, int[][][] ret, Triple target) {
        Triple pop;
        do {
            pop = stack.pollLast();
            if (pop != null) {
                System.out.println("出栈[" + pop.a + "," + pop.b + "]: " + board[pop.a][pop.b]);
                ret[0][pop.a][pop.c] = 0;
                ret[1][pop.b][pop.c] = 0;
                ret[2][pop.a / 3 * 3 + pop.b / 3][pop.c] = 0;
                board[pop.a][pop.b] = '.';
            }
        } while (pop != null && !pop.equals(target));
    }

    public static boolean solve(char[][] board, int[][][] ret) {
        System.out.println("----------------------------");
        int empty = 0;
        List<Integer> backup2 = new ArrayList<>();
        boolean insert = false;
        int m = -1, n = -1;
        for (int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    empty++;
                    int r = i / 3;
                    int t = j / 3;
                    // 确认空位上的可选数字，如果大于1则跳过
                    List<Integer> backup = new ArrayList<>();
                    for (int p=0; p<9; p++) {
                        if ((ret[0][i][p] | ret[1][j][p] | ret[2][r * 3 + t][p]) == 0) {
                            // p+1 可选
                            backup.add(p);
                        }
                    }
                    if (backup.size() == 1) {
                        // 填入位置选项
                        insert = true;
                        empty--;
                        insert(board, ret, new Triple(i,j,backup.get(0)));
                    } else if (backup.size() == 0) {
                        System.out.println("死胡同: [" + i + "," + j + "]");
                        return false;
                    } else {
                        if (backup2.isEmpty() || backup.size() < backup2.size()) {
                            backup2 = backup;
                            m = i;
                            n = j;
                        }
                    }
                }
            }
        }
        if (empty == 0) {
            System.out.println("========================================");
            for (char[] chars : board) {
                System.out.println(Arrays.toString(chars));
            }
            System.out.println("========================================");
            finish = true;
            return true;
        }
        if (insert) {
            return solve(board, ret);
        } else {
            boolean valid = false;
            for (int p : backup2) {
                if (finish) {
                    break;
                }
                Triple branch = new Triple(m, n, p);
                System.out.println("进入分支路径：" + branch);
                insert(board, ret, branch);
                valid = solve(board, ret);
                System.out.println("分支路径结果：" + branch + ", " + valid);
                if (!valid) {
                    // 用栈做回滚操作，每次赋值压入栈帧，分支错误后回退到分叉点栈帧
                    popUntil(board, ret, branch);
                    System.out.println("跳出分支路径：" + branch);
                }
            }
            return valid;
        }
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
        
        solveSudoku(board2);
    }

}
