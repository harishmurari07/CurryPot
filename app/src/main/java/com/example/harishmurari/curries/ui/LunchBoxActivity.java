package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.adapters.Adapter;
import com.example.harishmurari.curries.model.DataModel;

import java.util.ArrayList;

/**
 * Created by harishmurari on 6/7/2017.
 */

public class LunchBoxActivity extends AppCompatActivity implements Adapter.OnSelectedCurry {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setTitle("Lunch Box");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for (int i = 0; i < MyData.lunchBoxArray.length; i++) {
            data.add(new DataModel(
                    MyData.lunchBoxArray[i],
                    MyData.lunchprice[i],
                    MyData.lunchDrawableArray[i]
            ));
        }

        adapter = new Adapter(this, this, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClickedCurry(DataModel data) {
        Intent intent = new Intent(LunchBoxActivity.this, WhiteRiceActivity.class);
        intent.putExtra("Image", data.getImage());
        intent.putExtra("name", data.getName());
        intent.putExtra("price", data.getName());
    }
}
