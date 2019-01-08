package bartekhejke.com.pizzachecker.TicTacToeFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import bartekhejke.com.pizzachecker.R;

/**
 * Created by Bartek on 05.01.2019.
 */

public class GameDialog extends DialogFragment {

    private String dialogResultText;
    private GameDialogListener dialogListener;

    public static GameDialog newInstance(String resultText){
        GameDialog gameDialog = new GameDialog();
        gameDialog.dialogResultText = resultText;
        return gameDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            dialogListener = (GameDialogListener) getTargetFragment();
        } catch (ClassCastException e){
            throw new ClassCastException("DialogClickListener interface required");
        }
        if (savedInstanceState != null){
            dialogResultText = savedInstanceState.getString("dialogResultText");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.game_dialog, null, false);

        TextView result = (TextView) view.findViewById(R.id.resultText);
        result.setText(dialogResultText);

        Button restart = (Button) view.findViewById(R.id.restartGameButton);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.OnDialogRestartClick(GameDialog.this);
                dismiss();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        return alertDialog;
    }
    public interface GameDialogListener{
        void OnDialogRestartClick(DialogFragment dialog);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("dialogResultText", dialogResultText);
    }
}
