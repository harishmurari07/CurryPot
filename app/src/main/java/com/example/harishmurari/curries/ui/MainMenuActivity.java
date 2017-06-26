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
import com.example.harishmurari.curries.Utility;
import com.example.harishmurari.curries.model.CurryItem;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

/**
 * Created by harishmurari on 6/7/2017.
 */

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        Utility.clear(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new MainMenuFragment()).commit();
        }

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
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
        ArrayList<CurryItem> curryItemsCart = Utility.retriveCart(this);
        i.putParcelableArrayListExtra(Intent.EXTRA_TEXT, curryItemsCart);
        startActivity(i);
    }

    public void launchSelectedActivity(int position) {
        switch (position) {
            case MyData.VEGETARIAN:
                launchScreen(VegetarianItemsActivity.class);
                break;
            case MyData.NON_VEGETARIAN:
                launchScreen(NonVegetarianItemsActivity.class);
                break;
            case MyData.WHITE_RICE:
                launchScreen(WhiteRiceItemsActivity.class);
                break;
            case MyData.LUNCH_BOX:
                launchScreen(LunchBoxItemsActivity.class);
                break;
        }
    }

    public void launchScreen(Class launchActivity){
        Intent launchIntent = new Intent(this, launchActivity);
        startActivity(launchIntent);
    }
}
