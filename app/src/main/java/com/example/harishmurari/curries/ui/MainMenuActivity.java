package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;

/**
 * Created by harishmurari on 6/7/2017.
 */

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new MainMenuFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case R.id.ic_action_shopping_cart:
                launchCartActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchCartActivity(){
        Intent i = new Intent(MainMenuActivity.this, CartActivity.class);
        startActivity(i);
    }

    public void launchSelectedActivity(int position) {
        switch (position) {
            case MyData.VEGETARIAN:

                Intent intent = new Intent(this, VegetarianItemsActivity.class);
                startActivity(intent);
//                replaceFragment(VegetarianItemsFragment.getInstance());
                break;
            case MyData.NON_VEGETARIAN:
                Intent intentn = new Intent(this, NonVegetarianItemsActivity.class);
                startActivity(intentn);
        //        replaceFragment(new NonVegetarianItemsFragment());
                break;
            case MyData.WHITE_RICE:
                Intent intentw = new Intent(this, WhiteRiceItemsActivity.class);
                startActivity(intentw);
        //        replaceFragment(new LunchBoxItemsFragment());
                break;
            case MyData.LUNCH_BOX:
                Intent intent1 = new Intent(this, LunchBoxItemsActivity.class);
                startActivity(intent1);
        //        replaceFragment(new WhiteRiceItemsFragment());
                break;
        }
    }

    public void replaceFragment(Fragment fragmentToReplace) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragmentToReplace)
                .addToBackStack(null)
                .commit();

    }
}
