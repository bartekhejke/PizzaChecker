package bartekhejke.com.pizzachecker;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bartekhejke.com.pizzachecker.TicTacToeFragment.TicTacToeFragment;
import bartekhejke.com.pizzachecker.WeatherFragment.WheatherFragment;

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

        /*if (savedInstanceState == null){
            selectItem(0);
        }*/
        Intent intent = getIntent();
        selectItem(intent.getIntExtra("whichFragment",-1));
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
                fragment = new TopFragment();
                break;
            case 2:
                fragment = new TicTacToeFragment();
                break;
            case 3:
                fragment = new WheatherFragment();
                break;
            case 4:
                fragment = new SettingsFragment();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            finish();
            startActivity(intent);

        }
        return true;
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
*/
}
