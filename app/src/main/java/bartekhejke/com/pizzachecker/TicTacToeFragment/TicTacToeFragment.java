package bartekhejke.com.pizzachecker.TicTacToeFragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bartekhejke.com.pizzachecker.R;


public class TicTacToeFragment extends Fragment {


    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
    }

}
