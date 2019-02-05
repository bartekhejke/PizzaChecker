package bartekhejke.com.pizzachecker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView menuTextView, checkTextView, tictactoeTextView, weatherTextView, settingsTextView;
    private ImageButton checkButton, tictactoeButton, weatherButton, settingsButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuTextView = (TextView) findViewById(R.id.menuTextView);
        checkTextView = (TextView) findViewById(R.id.checkTextView);
        tictactoeTextView = (TextView) findViewById(R.id.tictactoeTextView);
        weatherTextView = (TextView) findViewById(R.id.weatherTextView);
        settingsTextView = (TextView) findViewById(R.id.settingsTextView);

        //fonts
        Typeface MainFont = Typeface.createFromAsset(getAssets(),"fonts/ITCKRIST.ttf");

        menuTextView.setTypeface(MainFont);
        checkTextView.setTypeface(MainFont);
        tictactoeTextView.setTypeface(MainFont);
        weatherTextView.setTypeface(MainFont);
        settingsTextView.setTypeface(MainFont);

        checkButton = (ImageButton) findViewById(R.id.checkButton);
        tictactoeButton = (ImageButton) findViewById(R.id.tictactoeButton);
        weatherButton = (ImageButton) findViewById(R.id.weatherButton);
        settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        //button action
        checkButton.setOnClickListener(new ShowSelectedFragment());
        tictactoeButton.setOnClickListener(new ShowSelectedFragment());
        weatherButton.setOnClickListener(new ShowSelectedFragment());
        settingsButton.setOnClickListener(new ShowSelectedFragment());
    }

    class ShowSelectedFragment implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            showSelectedFragment(v.getId());
        }
        void showSelectedFragment(int whichFragment){
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            switch (whichFragment){
                case R.id.checkButton:
                    intent.putExtra("whichFragment",1);
                    startActivity(intent);
                    break;
                case R.id.tictactoeButton:
                    intent.putExtra("whichFragment",2);
                    startActivity(intent);
                    break;
                case R.id.weatherButton:
                    intent.putExtra("whichFragment",3);
                    startActivity(intent);
                    break;
                case R.id.settingsButton:
                    intent.putExtra("whichFragment",4);
                    startActivity(intent);
                    break;


            }

        }
    }
}
