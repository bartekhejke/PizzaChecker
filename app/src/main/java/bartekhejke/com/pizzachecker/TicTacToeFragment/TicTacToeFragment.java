package bartekhejke.com.pizzachecker.TicTacToeFragment;


import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import bartekhejke.com.pizzachecker.R;


public class TicTacToeFragment extends Fragment implements View.OnClickListener, GameDialog.GameDialogListener {

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
    static private String result;
    private String[] textBoard = new String[game.BOARD_SIZE*game.BOARD_SIZE];
    private int[] boardArray = new int[game.BOARD_SIZE*game.BOARD_SIZE];


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

            //font
            Typeface TicTacToeFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/ComicBook.ttf");

            cell_00.setTypeface(TicTacToeFont);
            cell_10.setTypeface(TicTacToeFont);
            cell_20.setTypeface(TicTacToeFont);
            cell_01.setTypeface(TicTacToeFont);
            cell_11.setTypeface(TicTacToeFont);
            cell_21.setTypeface(TicTacToeFont);
            cell_02.setTypeface(TicTacToeFont);
            cell_12.setTypeface(TicTacToeFont);
            cell_22.setTypeface(TicTacToeFont);

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

            if (savedInstanceState != null){
                result = savedInstanceState.getString("result");
                textBoard = savedInstanceState.getStringArray("textBoard");
                boardArray = savedInstanceState.getIntArray("board");
                for (int i = 0; i < game.BOARD_SIZE; i++) {
                    for (int j = 0; j < game.BOARD_SIZE; j++) {
                        cellBoard[i][j].setText(textBoard[i * game.BOARD_SIZE + j]);
                        board[i][j] = boardArray[i*game.BOARD_SIZE+j];
                    }
                }

            }

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
    @Override
    public void OnDialogRestartClick(DialogFragment dialog) {
        game.restartGame();

        for (int i = 0; i < game.BOARD_SIZE; i++) {
            for (int j = 0; j < game.BOARD_SIZE; j++) {
                cellBoard[i][j].setText(null);
                cellBoard[i][j].setTextColor(Color.BLACK);
                textBoard[i*game.BOARD_SIZE+j] = null;
            }
        }
    }


    private void showGameDialog(String result){
        DialogFragment dialog = GameDialog.newInstance(result);
        dialog.setTargetFragment(this, 0);
        dialog.show(getFragmentManager(), "Result");

    }


    private void onClickedCell(int row, int column, TextView cell){

        if (game.isCellEmpty(row, column) && !(game.getScore(board) == COMPUTER_WINNING_SCORE) && !(game.getScore(board) == HUMAN_WINNING_SCORE)) {
            game.humanMove(row,column);
            cell.setText("x");
            textBoard[row*game.BOARD_SIZE+column] = "x";
            boardArray[row*game.BOARD_SIZE+column] = board[row][column];

            if (game.areMovesLeft()){
                bestMove = game.getOptimalMove();
                game.computerMove(bestMove[0],bestMove[1]);
                cellBoard[bestMove[0]][bestMove[1]].setText("o");
                textBoard[bestMove[0]*game.BOARD_SIZE+bestMove[1]] = "o";
                boardArray[bestMove[0]*game.BOARD_SIZE+bestMove[1]] = board[bestMove[0]][bestMove[1]];

            }

            if (game.getScore(board)==COMPUTER_WINNING_SCORE){
                cellBoard[winningCells[0][0]][winningCells[0][1]].setTextColor(getResources().getColor(R.color.colorLost));
                cellBoard[winningCells[1][0]][winningCells[1][1]].setTextColor(getResources().getColor(R.color.colorLost));
                cellBoard[winningCells[2][0]][winningCells[2][1]].setTextColor(getResources().getColor(R.color.colorLost));
                result = getResources().getString(R.string.lost);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showGameDialog(result);
                    }
                },100);

            }

            if (game.getScore(board)==HUMAN_WINNING_SCORE){
                cellBoard[winningCells[0][0]][winningCells[0][1]].setTextColor(getResources().getColor(R.color.colorWon));
                cellBoard[winningCells[1][0]][winningCells[1][1]].setTextColor(getResources().getColor(R.color.colorWon));
                cellBoard[winningCells[2][0]][winningCells[2][1]].setTextColor(getResources().getColor(R.color.colorWon));
                result = getResources().getString(R.string.won);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showGameDialog(result);
                    }
                },100);
            }

            if (!game.areMovesLeft()){
                result = getResources().getString(R.string.draw);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showGameDialog(result);
                    }
                },100);
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", result);
        outState.putStringArray("textBoard", textBoard);
        outState.putIntArray("board", boardArray);

    }
}
