// Filename: Sudoku.java
// Author: Miles Clikeman

import java.util.*;

// *****************************************************************************
// *****************************************************************************
// **** Sudoku
// *****************************************************************************
// *****************************************************************************

public class Sudoku {

  public static final int N = 9;
  
  public static void main(String[] args) {
    
    Scanner console = new Scanner(System.in);
    
    int[][] board = new int[N][N];

    System.out.println("Enter 0 for blank squares");
    // read in n
    for (int row = 0; row < N; ++row) {
      System.out.print("Enter ");
      if (row == 0) {
        System.out.print("1st ");
      } else if (row == 1) {
        System.out.print("2nd ");
      } else if (row == 2) {
        System.out.print("3rd ");
      } else {
        System.out.print((row+1) + "th ");
      }
      System.out.print("row: ");
      for (int col = 0; col < N; ++col) {
        board[row][col] = console.nextInt();
      }
    }
    
    System.out.println();

    if (solvePuzzle(board, 0, 0)) {
      System.out.println("Solution:");
      System.out.println();
      print(board);
    } else {
      System.out.println("No solution exists");
    }

  } // main

  private static boolean solvePuzzle(int[][] board, int row, int col) {
    if (row == N-1 && col == N) {
      return true;
    }

    if (col == N) {
      row++;
      col = 0;
    }

    if (board[row][col] > 0) {
      return solvePuzzle(board, row, col+1);
    }

    for (int num = 1; num <= N; ++num) {
      if (isSafe(board, row, col, num)) {
        board[row][col] = num;
        if (solvePuzzle(board, row, col+1)) {
          return true;
        }
      }
      board[row][col] = 0;
    }

    return false;
  }

  private static boolean isSafe(int[][] board, int row, int col, int num) {
    for (int j = 0; j < N; ++j) {
      if (board[row][j] == num) {
        return false;
      }
    }
    for (int i = 0; i < N; ++i) {
      if (board[i][col] == num) {
        return false;
      }
    }
    int startRow = row - row % 3;
    int startCol = col - col % 3;
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; j++) {
        if (board[i+startRow][j+startCol] == num) {
          return false;
        }
      }
    }
    return true;
  }

  private static void print(int[][] board) {
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

} // end of Sudoku class
