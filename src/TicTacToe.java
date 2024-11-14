import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean nextGame;
        do {
            clearBoard();
            playGame(in);
            nextGame = SafeInput.getYNConfirm(in, "Do you want to play again? (Y or N)");
        } while (nextGame);

        System.out.println("Thank you for playing!");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void playGame(Scanner in) {
        int moveCount = 0;
        boolean gameOver = false;

        while (!gameOver) {
            displayBoard();
            int row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1;
            int col = SafeInput.getRangedInt(in, "Enter column (1-3)", 1, 3) - 1;

            while (!isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1;
                col = SafeInput.getRangedInt(in, "Enter column (1-3)", 1, 3) - 1;
            }

            board[row][col] = currentPlayer;
            moveCount++;

            if (isWin(currentPlayer)) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (moveCount == ROWS * COLS) {
                displayBoard();
                System.out.println("Game Over!! It's a tie!");
                gameOver = true;
            } else {

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(" " + board[i][j]);
                if (j < COLS - 1) System.out.print(" |");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("---|---|---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String move) {
        return isRowWin(move) || isColWin(move) || isDiagonalWin(move);
    }

    private static boolean isRowWin(String move) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(move) && board[i][1].equals(move) && board[i][2].equals(move)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String move) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(move) && board[1][i].equals(move) && board[2][i].equals(move)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String move) {
        return (board[0][0].equals(move) && board[1][1].equals(move) && board[2][2].equals(move)) ||
                (board[0][2].equals(move) && board[1][1].equals(move) && board[2][0].equals(move));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}