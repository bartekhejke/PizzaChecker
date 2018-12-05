package bartekhejke.com.pizzachecker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCheck;
    TextView tvWynikCena;
    EditText etPrice, etSize;
    private String resultcm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvWynikCena = (TextView) findViewById(R.id.tvWynikCena);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etSize = (EditText) findViewById(R.id.etSize);

        btnCheck = (Button) findViewById(R.id.btnCheck);
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
        if( savedInstanceState != null){
            resultcm = savedInstanceState.getString("pricecm");
            tvWynikCena.setText(resultcm);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("pricecm",resultcm);
    }

}
