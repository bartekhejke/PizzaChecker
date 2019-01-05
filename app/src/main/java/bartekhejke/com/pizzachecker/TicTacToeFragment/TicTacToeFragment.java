package bartekhejke.com.pizzachecker.TicTacToeFragment;


import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import bartekhejke.com.pizzachecker.R;


public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    LogicTicTacToe game = new LogicTicTacToe();
    private int[][] winningCells = game.getWinningCells();
    private int[][] board = game.getBoard();
    private int[] bestMove;
    private TextView[][] cellBoard = new TextView[game.BOARD_SIZE][game.BOARD_SIZE];
    private TextView cell_00;
    private TextView cell_10;
    private TextView cell_20;
    private TextView cell_01;
    private TextView cell_11;
    private TextView cell_21;
    private TextView cell_02;
    private TextView cell_12;
    private TextView cell_22;
    private int COMPUTER_WINNING_SCORE = 10;
    private int HUMAN_WINNING_SCORE = -10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container,false);

        cell_00 = (TextView) view.findViewById(R.id.cell_00);
        cell_10 = (TextView) view.findViewById(R.id.cell_10);
        cell_20 = (TextView) view.findViewById(R.id.cell_20);
        cell_01 = (TextView) view.findViewById(R.id.cell_01);
        cell_11 = (TextView) view.findViewById(R.id.cell_11);
        cell_21 = (TextView) view.findViewById(R.id.cell_21);
        cell_02 = (TextView) view.findViewById(R.id.cell_02);
        cell_12 = (TextView) view.findViewById(R.id.cell_12);
        cell_22 = (TextView) view.findViewById(R.id.cell_22);

        cell_00.setOnClickListener(this);
        cell_10.setOnClickListener(this);
        cell_20.setOnClickListener(this);
        cell_01.setOnClickListener(this);
        cell_11.setOnClickListener(this);
        cell_21.setOnClickListener(this);
        cell_02.setOnClickListener(this);
        cell_12.setOnClickListener(this);
        cell_22.setOnClickListener(this);

        cellBoard[0][0] = cell_00;
        cellBoard[1][0] = cell_10;
        cellBoard[2][0] = cell_20;
        cellBoard[0][1] = cell_01;
        cellBoard[1][1] = cell_11;
        cellBoard[2][1] = cell_21;
        cellBoard[0][2] = cell_02;
        cellBoard[1][2] = cell_12;
        cellBoard[2][2] = cell_22;

        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cell_00:
                onClickedCell(0,0, cell_00);
                break;
            case R.id.cell_10:
                onClickedCell(1,0, cell_10);
                break;
            case R.id.cell_20:
                onClickedCell(2,0, cell_20);
                break;
            case R.id.cell_01:
                onClickedCell(0,1, cell_01);
                break;
            case R.id.cell_11:
                onClickedCell(1,1, cell_11);
                break;
            case R.id.cell_21:
                onClickedCell(2,1, cell_21);
                break;
            case R.id.cell_02:
                onClickedCell(0,2, cell_02);
                break;
            case R.id.cell_12:
                onClickedCell(1,2, cell_12);
                break;
            case R.id.cell_22:
                onClickedCell(2,2, cell_22);
                break;
        }
    }

    private void onClickedCell(int row, int column, TextView cell){

        if (game.isCellEmpty(row, column) && !(game.getScore(board) == COMPUTER_WINNING_SCORE) && !(game.getScore(board) == HUMAN_WINNING_SCORE)) {
            game.humanMove(row,column);
            cell.setText("x");

            if (game.areMovesLeft()){
                bestMove = game.getOptimalMove();
                game.computerMove(bestMove[0],bestMove[1]);
                cellBoard[bestMove[0]][bestMove[1]].setText("o");
            }

            if (game.getScore(board)==COMPUTER_WINNING_SCORE){
                cellBoard[winningCells[0][0]][winningCells[0][1]].setTextColor(getResources().getColor(R.color.colorLost));
                cellBoard[winningCells[1][0]][winningCells[1][1]].setTextColor(getResources().getColor(R.color.colorLost));
                cellBoard[winningCells[2][0]][winningCells[2][1]].setTextColor(getResources().getColor(R.color.colorLost));
                Toast.makeText(getActivity(),"You LOST",Toast.LENGTH_LONG).show();
            }

            if (game.getScore(board)==HUMAN_WINNING_SCORE){
                cellBoard[winningCells[0][0]][winningCells[0][1]].setTextColor(getResources().getColor(R.color.colorWon));
                cellBoard[winningCells[1][0]][winningCells[1][1]].setTextColor(getResources().getColor(R.color.colorWon));
                cellBoard[winningCells[2][0]][winningCells[2][1]].setTextColor(getResources().getColor(R.color.colorWon));
                Toast.makeText(getActivity(),"You WON",Toast.LENGTH_LONG).show();
            }

            if (!game.areMovesLeft()){
                Toast.makeText(getActivity(),"Draw",Toast.LENGTH_LONG).show();
            }

        }
    }
}
