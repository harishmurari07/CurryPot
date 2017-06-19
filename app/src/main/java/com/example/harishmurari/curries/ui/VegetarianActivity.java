package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.harishmurari.curries.adapters.Adapter;
import com.example.harishmurari.curries.model.CurryItem;
import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;

import java.util.ArrayList;

public class VegetarianActivity extends AppCompatActivity implements Adapter.OnSelectedCurry{

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private static ArrayList<CurryItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setTitle("Vegetarian");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for (int i = 0; i < MyData.vegcurriesArray.length; i++) {
            data.add(new CurryItem(
                    MyData.vegcurriesArray[i],
                    MyData.price[i],
                    MyData.drawableArray[i]
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
        Intent i = new Intent(VegetarianActivity.this, CartActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClickedCurry(CurryItem data) {

    }
}