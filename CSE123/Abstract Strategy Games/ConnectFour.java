// Atbaerk Yildiz
// Brett Wortzman
// CSE 123
// C0: Abstract Strategy Games
//
// This is a class to represent a game of ConnectFour that implements the 
// AbstractStrategyGame interface.
import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    
    private char[][] board;
    private boolean isXTurn;

    // Constructs a new TicTacToe game.
    public ConnectFour() {
        board = new char[][]{{'-', '-', '-','-','-', '-','-'},
                             {'-', '-', '-','-','-', '-','-'},
                             {'-', '-', '-','-','-', '-','-'},
                             {'-', '-', '-','-','-', '-','-'},
                             {'-', '-', '-','-','-', '-','-'},
                             {'-', '-', '-','-','-', '-','-'}
                            };
        isXTurn = true;
    }

    // Returns whether or not the game is over.
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // Returns the index of the winner of the game.
    // 1 if player 1 (X), 2 if player 2 (O), 0 if a tie occurred,
    // and -1 if the game is not over.
    public int getWinner() {
        for (int i = 0; i < board.length; i++) {
            // check row i
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && 
                    board[i][j] == board[i][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        for (int i = 0; i < board.length - 3; i++) {
            // check col j
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && 
                    board[i][j] == board[i+3][j] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        for (int i = 0; i < board.length - 3; i++) {
            // check diagonals from top left to bottom right
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && 
                    board[i][j] == board[i+3][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }

        for (int i = 3; i < board.length; i++) {
            // check diagonals from top right to bottom left
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && 
                    board[i][j] == board[i-3][j+3] && board[i][j] != '-') {
                    return board[i][j] == 'X' ? 1 : 2;
                }
            }
        }
        
        // check for tie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    // unfilled space; game not over
                    return -1;
                }
            }
        }
        
        // it's a tie!
        return 0;
    }

    // Returns the index of which player's turn it is.
    // 1 if player 1 (X), 2 if player 2 (O), -1 if the game is over
    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        }
        return isXTurn ? 1 : 2;
    }

    // Given the input, places an X or an O in the column
    // the player specifies.
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or if the column is already full.
    // Also throws an exception if the input for column is not an int.
    public void makeMove(Scanner input) {
        char currPlayer = isXTurn ? 'X' : 'O';

        System.out.print("Column? ");

        if (!input.hasNextInt()) {
            input.next();
            throw new IllegalArgumentException("Please enter an integer.");
        }

        int col = input.nextInt();
        
        makeMove(col, currPlayer);
        isXTurn = !isXTurn;
    }

    // Private helper method for makeMove.
    // Given a column, as well as player index,
    // places an X or an O in that col at the bottom.
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or if the column is already full.
    private void makeMove(int col, char player) {
        
        if (col < 0 || col >= board[0].length) {
            throw new IllegalArgumentException("Invalid board position: column " + col);
        }

        int nextOpen = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == ('-')) {
                nextOpen = i;
            }
        }
        if (nextOpen == -1) {
            throw new IllegalArgumentException("Column " + col + " already full.");
        }
        
        board[nextOpen][col] = player;
    }

    // Returns a String containing instructions to play the game.
    public String instructions() {
        String result = "";
        result += "Player 1 is X and goes first. Choose where to play by entering a column,\n";
        result += "where the first column is 0, and the last (7th) column is 6. Your symbol\n"; 
        result += "will be put into the first available space from the bottom of that column.\n";
        result += "Spaces shown as a - are empty. The game ends when one player holds four\n";
        result += "positions in a row, in which case that player wins, or when the board is\n";
        result += "full, in which case the game ends in a tie.";
        return result;
    }

    // Returns a String representation of the current state of the board.
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
