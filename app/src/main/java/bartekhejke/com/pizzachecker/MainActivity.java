package bartekhejke.com.pizzachecker;

import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
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

    private String[] menuTitles;
    private ListView menuList;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuList = (ListView) findViewById(R.id.menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        menuTitles = getResources().getStringArray(R.array.menuTitles);

        menuList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, menuTitles));
        menuList.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState == null){
            selectItem(0);
        }


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
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        drawerLayout.closeDrawer(menuList);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
