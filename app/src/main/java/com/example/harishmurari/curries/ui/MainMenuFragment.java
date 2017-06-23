package com.example.harishmurari.curries.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;

/**
 * Created by harishmurari on 6/21/2017.
 */

public class MainMenuFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.main_menu_activity_listitem, MyData.mainmenuArray);

        GridView gridView = (GridView) view.findViewById(R.id.main_menu_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ((MainMenuActivity)getActivity()).launchSelectedActivity(position);
            }
        });
        return view;
    }
}
