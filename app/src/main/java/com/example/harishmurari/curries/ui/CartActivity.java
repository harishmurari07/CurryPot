package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.adapters.CartAdapter;
import com.example.harishmurari.curries.model.CartItems;
import com.example.harishmurari.curries.model.CurryItem;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCurryQuantitySelected {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<CurryItem> cartItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        setTitle("CART ITEMS");

        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle data = getIntent().getExtras();

        cartItems = data.getParcelableArrayList(Intent.EXTRA_TEXT);
        adapter = new CartAdapter(this, this, cartItems);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onSelectedquantity(CartItems cartItemsData) {

    }
}
