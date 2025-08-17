import java.util.Scanner;

public class Tictactoe {
    static char[][] board = {
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';

        while (true) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            int move;

            // Input validation
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next(); // clear invalid input
                continue;
            }

            move = scanner.nextInt();

            if (move < 1 || move > 9) {
                System.out.println("Invalid move. Please choose between 1 and 9.");
                continue;
            }

            if (isValidMove(move)) {
                placeMove(move, currentPlayer);
                if (isWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("🎉 Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Position already taken. Try another move.");
            }
        }

        scanner.close();
    }

    // Display board
    public static void printBoard() {
        System.out.println();
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Convert input 1-9 to board position
    public static int[] getPosition(int move) {
        switch (move) {
            case 1: return new int[]{0, 0};
            case 2: return new int[]{0, 2};
            case 3: return new int[]{0, 4};
            case 4: return new int[]{2, 0};
            case 5: return new int[]{2, 2};
            case 6: return new int[]{2, 4};
            case 7: return new int[]{4, 0};
            case 8: return new int[]{4, 2};
            case 9: return new int[]{4, 4};
            default: return new int[]{-1, -1}; // should never happen
        }
    }

    // Check if move is valid
    public static boolean isValidMove(int move) {
        int[] pos = getPosition(move);
        return board[pos[0]][pos[1]] == ' ';
    }

    // Place the player's move
    public static void placeMove(int move, char player) {
        int[] pos = getPosition(move);
        board[pos[0]][pos[1]] = player;
    }

    // Check if the board is full
    public static boolean isBoardFull() {
        for (int i = 1; i <= 9; i++) {
            int[] pos = getPosition(i);
            if (board[pos[0]][pos[1]] == ' ') {
                return false;
            }
        }
        return true;
    }

    // Check for a win
    public static boolean isWinner(char player) {
        // Check rows
        if (checkLine(player, 0, 0, 0, 2, 0, 4)) return true;
        if (checkLine(player, 2, 0, 2, 2, 2, 4)) return true;
        if (checkLine(player, 4, 0, 4, 2, 4, 4)) return true;

        // Check columns
        if (checkLine(player, 0, 0, 2, 0, 4, 0)) return true;
        if (checkLine(player, 0, 2, 2, 2, 4, 2)) return true;
        if (checkLine(player, 0, 4, 2, 4, 4, 4)) return true;

        // Check diagonals
        if (checkLine(player, 0, 0, 2, 2, 4, 4)) return true;
        if (checkLine(player, 0, 4, 2, 2, 4, 0)) return true;

        return false;
    }

    // Check if three positions contain the same player's symbol
    public static boolean checkLine(char player, int r1, int c1, int r2, int c2, int r3, int c3) {
        return board[r1][c1] == player &&
               board[r2][c2] == player &&
               board[r3][c3] == player;
    }
}
