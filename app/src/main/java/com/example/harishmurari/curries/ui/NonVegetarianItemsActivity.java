package com.example.harishmurari.curries.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.harishmurari.curries.R;

/**
 * Created by harishmurari on 6/25/2017.
 */

public class NonVegetarianItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
//        if(savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, NonVegetarianItemsFragment.getInstance()).commit();
//        }
    }
}
