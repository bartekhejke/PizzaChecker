package bartekhejke.com.pizzachecker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TopFragment extends Fragment {

    private TextView tvWynikCena;
    private EditText etPrice;
    private EditText etSize;
    private Button btnCheck;
    private String resultcm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container,false);

        tvWynikCena = (TextView) view.findViewById(R.id.tvWynikCena);
        etPrice = (EditText) view.findViewById(R.id.etPrice);
        etSize = (EditText) view.findViewById(R.id.etSize);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);

        if( savedInstanceState != null){
            resultcm = savedInstanceState.getString("pricecm");
            tvWynikCena.setText(resultcm);
        }

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Results pricecm = new Results(
                        Double.parseDouble(etPrice.getText().toString()),
                        Integer.parseInt(etSize.getText().toString()));
                resultcm = pricecm.toString();
                tvWynikCena.setText(resultcm);
            }
        });
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("pricecm",resultcm);
    }
}
