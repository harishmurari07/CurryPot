package com.example.harishmurari.curries.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import com.example.harishmurari.curries.VegetarianAdapter;
import com.example.harishmurari.curries.VegDataModel;
import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;

import java.util.ArrayList;


public class VegetarianActivity extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<VegDataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegetarian_activity);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<VegDataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new VegDataModel(
                    MyData.nameArray[i],
                    MyData.price[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        adapter = new VegetarianAdapter(data);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}