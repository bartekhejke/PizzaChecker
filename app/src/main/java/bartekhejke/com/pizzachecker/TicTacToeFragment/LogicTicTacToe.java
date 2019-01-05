package bartekhejke.com.pizzachecker.TicTacToeFragment;

import static java.lang.Math.min;
import static java.lang.StrictMath.max;

/**
 * Created by Bartek on 29.12.2018.
 */

public class LogicTicTacToe {
    static final int BOARD_SIZE = 3;
    private final int human = -1;
    private final int computer = 1;
    private int board[][] = new int[BOARD_SIZE][BOARD_SIZE];
    private int winningCells[][] = new int[3][2];
    private int bestMove[] = new int[2];

    public int[][] getBoard(){
        return board;
    }

    public void humanMove(int row, int column){
        board[row][column] = human;
    }

    public void computerMove(int row, int column){
        board[row][column] = computer;
    }

    public boolean isCellEmpty(int row, int column){
        return board[row][column]==0;
    }

    public int[][] getWinningCells(){
        return winningCells;
    }

    public boolean areMovesLeft(){
        for (int i=0; i<BOARD_SIZE;i++){
            for (int j=0; j<BOARD_SIZE; j++){
                if(board[i][j]==0){
                    return true;
                }
            }
        }
        return false;
    }

    public void restartGame(){
        for (int i=0; i<BOARD_SIZE;i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean isWin(int xy1, int xy2, int xy3){
        if (xy1 == 0 || xy2 == 0 || xy3 == 0){
            return false;
        }
        return ((xy1 == xy2) && (xy2 == xy3));
    }

    public int getScore(int board[][]){
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isWin(board[i][0],board[i][1],board[i][2])){
                winningCells[0][0] = i;
                winningCells[1][0] = i;
                winningCells[2][0] = i;
                winningCells[0][1] = 0;
                winningCells[1][1] = 1;
                winningCells[2][1] = 2;
                if(board[i][0]==computer){
                    return +10;
                } else if (board[i][0]==human){
                    return -10;
                }
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isWin(board[0][i],board[1][i],board[2][i])){
                winningCells[0][0] = 0;
                winningCells[1][0] = 1;
                winningCells[2][0] = 2;
                winningCells[0][1] = i;
                winningCells[1][1] = i;
                winningCells[2][1] = i;
                if(board[0][i]==computer){
                    return +10;
                } else if (board[0][i]==human){
                    return -10;
                }
            }
            
        }

        if (isWin(board[0][0],board[1][1],board[2][2])){
            winningCells[0][0] = 0;
            winningCells[1][0] = 1;
            winningCells[2][0] = 2;
            winningCells[0][1] = 0;
            winningCells[1][1] = 1;
            winningCells[2][1] = 2;
            if(board[1][1]==computer){
                return +10;
            } else if (board[1][1]==human){
                return -10;
            }
        }

        if (isWin(board[0][2],board[1][1],board[2][0])){
            winningCells[0][0] = 2;
            winningCells[1][0] = 1;
            winningCells[2][0] = 0;
            winningCells[0][1] = 0;
            winningCells[1][1] = 1;
            winningCells[2][1] = 2;
            if(board[1][1]==computer){
                return +10;
            } else if (board[1][1]==human){
                return -10;
            }
        }
        return 0;
    }

    private int minimax(int stage, boolean turn){
        int score = getScore(board);

        if (score == 10 || score == -10){
            return score;
        }

        if (!areMovesLeft()){
            return 0;
        }

        if (turn){
            int bestScore = -10000;

            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j]==0){
                        board[i][j] = computer;

                        bestScore = max(bestScore, minimax(stage +1,!turn));

                        board[i][j] = 0;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 10000;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j]==0){
                        board[i][j] = human;

                        bestScore = min(bestScore, minimax(stage +1,!turn));

                        board[i][j] = 0;
                    }
                }
            }
            return bestScore;
        }
    }

    public int[] getOptimalMove(){
        int bestValue = -10000;
        bestMove[0] = -1;
        bestMove[1] = -1;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j]==0){
                    board[i][j] = computer;

                    int moreValue = minimax(0, false);

                    board[i][j] = 0;

                    if (moreValue > bestValue){
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moreValue;
                    }
                }
            }
        }
        return bestMove;
    }


}


