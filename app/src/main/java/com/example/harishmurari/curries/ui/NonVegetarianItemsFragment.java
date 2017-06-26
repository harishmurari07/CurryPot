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

import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.adapters.Adapter;
import com.example.harishmurari.curries.model.CurryItem;

import java.util.ArrayList;

public class NonVegetarianItemsFragment extends Fragment implements Adapter.OnSelectedCurry{

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CurryItem> data;
    private static NonVegetarianItemsFragment nonVegetarianItemsFragment;

    public static NonVegetarianItemsFragment getInstance() {
        if(nonVegetarianItemsFragment == null) {
            nonVegetarianItemsFragment = new NonVegetarianItemsFragment();
        }
        return nonVegetarianItemsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_activity, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for (int i = 0; i < MyData.nonVegCurriesArray.length; i++) {
            data.add(new CurryItem(
                    MyData.nonVegCurriesArray[i],
                    MyData.nonVegPrice[i],
                    MyData.nonVegDrawableArray[i]
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
