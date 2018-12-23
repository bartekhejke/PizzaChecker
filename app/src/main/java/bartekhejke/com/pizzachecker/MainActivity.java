package bartekhejke.com.pizzachecker;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import bartekhejke.com.pizzachecker.TicTacToeFragment.TicTacToeFragment;

public class MainActivity extends AppCompatActivity {

    Button btnCheck;
    TextView tvWynikCena;
    EditText etPrice, etSize;
    private String resultcm;
    private String[] menuTitles;
    private ListView menuList;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvWynikCena = (TextView) findViewById(R.id.tvWynikCena);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etSize = (EditText) findViewById(R.id.etSize);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        menuList = (ListView) findViewById(R.id.menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        menuTitles = getResources().getStringArray(R.array.menuTitles);

        menuList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, menuTitles));
        menuList.setOnItemClickListener(new DrawerItemClickListener());

       /* btnCheck.setOnClickListener(new View.OnClickListener() {
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
        }*/

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment;
        switch (position){
            case 1:
                fragment = new TicTacToeFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        drawerLayout.closeDrawer(menuList);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putString("pricecm",resultcm);
    }

}
