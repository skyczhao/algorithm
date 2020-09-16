package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * SudokuSolver
 * https://leetcode-cn.com/problems/sudoku-solv
 * key: choose porpertly status indicator(better for recursion), instead of just simulating people thought
 *
 * @since 2020-09-15
 */
public class SudokuSolver {
//5 3 . . 7 . . . .
//6 . . 1 9 5 . . .
//. 9 8 . . . . 6 .
//8 . . . 6 . . . 3
//4 . . 8 . 3 . . 1
//7 . . . 2 . . . 6
//. 6 . . . . 2 8 .
//. . . 4 1 9 . . 5
//. . . . 8 . . 7 9

    public static void main(String[] args) {

//        char[][] board = new char[][]{
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//        };
        char[][] board = new char[][]{
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        SudokuSolver sol = new SudokuSolver();
        sol.solveSudoku(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        boolean[][] isRowUsed = new boolean[9][9];
        boolean[][] isColUsed = new boolean[9][9];
        boolean[][] isRectUsed = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int n = board[i][j] - '1';
                    isRowUsed[i][n] = true;
                    isColUsed[j][n] = true;
                    isRectUsed[(i / 3) * 3 + j / 3][n] = true;
                }
            }
        }

        dfs(board, isRowUsed, isColUsed, isRectUsed);
    }

    public boolean dfs(char[][] board, boolean[][] isRowUsed, boolean[][] isColUsed, boolean[][] isRectUsed) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int num = 0; num < 9; num++) {
                        if (!isRowUsed[i][num] && !isColUsed[j][num] && !isRectUsed[(i / 3) * 3 + j / 3][num]) {
                            isRowUsed[i][num] = true;
                            isColUsed[j][num] = true;
                            isRectUsed[(i / 3) * 3 + j / 3][num] = true;
                            board[i][j] = (char) (num + '1');
                            // prepare
                            boolean flag = dfs(board, isRowUsed, isColUsed, isRectUsed);
                            if (flag) {
                                return true;
                            }
                            // cleanup
                            board[i][j] = '.';
                            isRowUsed[i][num] = false;
                            isColUsed[j][num] = false;
                            isRectUsed[(i / 3) * 3 + j / 3][num] = false;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku_old(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Set<Character>[][] available = new HashSet[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                available[i][j] = new HashSet<>();
                for (int num = 1; num < 10; num++) {
                    available[i][j].add((char) (num + '0'));
                }
            }
        }

        updateAvailable(board, available);
        while (!isDone(board)) {
            if (!updateBoard(board, available)) {
                break;
            }
        }

        return;
    }

    private boolean isDone(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateAvailable(char[][] board, Set<Character>[][] available) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (available[i][j].isEmpty()) {
                    continue;
                }

                if (board[i][j] != '.') {
                    available[i][j].clear();
                } else {
                    // remove row
                    for (int moveJ = 0; moveJ < board[i].length; moveJ++) {
                        available[i][j].remove(board[i][moveJ]);
                    }
                    // remove col
                    for (int moveI = 0; moveI < board.length; moveI++) {
                        available[i][j].remove(board[moveI][j]);
                    }
                    // remove square
                    int squareI = i / 3;
                    int squareJ = j / 3;
                    for (int moveI = 0; moveI < 3; moveI++) {
                        for (int moveJ = 0; moveJ < 3; moveJ++) {
                            available[i][j].remove(board[moveI + squareI * 3][moveJ + squareJ * 3]);
                        }
                    }
                }
            }
        }
    }

    private boolean updateBoard(char[][] board, Set<Character>[][] available) {
        boolean flag = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (available[i][j].size() != 1) {
                    continue;
                }
                flag = true;

                for (Character avi : available[i][j]) {
                    board[i][j] = avi;
                }
                available[i][j].clear();

                // remove row
                for (int moveJ = 0; moveJ < board[i].length; moveJ++) {
                    available[i][moveJ].remove(board[i][j]);
                }
                // remove col
                for (int moveI = 0; moveI < board.length; moveI++) {
                    available[moveI][j].remove(board[i][j]);
                }
                // remove square
                int squareI = i / 3;
                int squareJ = j / 3;
                for (int moveI = 0; moveI < 3; moveI++) {
                    for (int moveJ = 0; moveJ < 3; moveJ++) {
                        available[moveI + squareI * 3][moveJ + squareJ * 3].remove(board[i][j]);
                    }
                }
            }
        }
        return flag;
    }
}
