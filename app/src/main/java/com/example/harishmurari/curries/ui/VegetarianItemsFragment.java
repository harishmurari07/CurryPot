package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.harishmurari.curries.adapters.Adapter;
import com.example.harishmurari.curries.model.CurryItem;
import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;

import java.util.ArrayList;

public class VegetarianItemsFragment extends Fragment implements Adapter.OnSelectedCurry{

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private static ArrayList<CurryItem> data;
    private static VegetarianItemsFragment vegetarianItemsFragment;

    public static VegetarianItemsFragment getInstance() {
        if (vegetarianItemsFragment == null) {
            vegetarianItemsFragment = new VegetarianItemsFragment();
        }

        return vegetarianItemsFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_activity, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
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

        adapter = new Adapter(getActivity(), this, data);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onClickedCurry(CurryItem data) {
    }
}