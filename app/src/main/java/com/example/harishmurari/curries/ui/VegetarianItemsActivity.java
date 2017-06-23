package com.example.harishmurari.curries.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.harishmurari.curries.R;

/**
 * Created by harishmurari on 6/21/2017.
 */

public class VegetarianItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, VegetarianItemsFragment.getInstance()).commit();
        }
    }
}
