package com.example.harishmurari.curries.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.model.DataModel;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private Context context;
    private OnSelectedCurry onSelectedCurry;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewName;
        TextView textViewprice;
        ImageView imageViewIcon;
        Button add, remove;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.curry_name);
            this.textViewprice = (TextView) itemView.findViewById(R.id.curry_price);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.curry_image);
            this.add = (Button) itemView.findViewById(R.id.add_button);
            this.remove = (Button) itemView.findViewById(R.id.remove_button);
            textViewName.setOnClickListener(this);
            textViewprice.setOnClickListener(this);
            imageViewIcon.setOnClickListener(this);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    add.setVisibility(View.GONE);
                    remove.setVisibility(View.VISIBLE);
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        public void onClick(View view) {
            onSelectedCurry.onClickedCurry(dataSet.get(getAdapterPosition()));
        }
    }

    public Adapter(Context context, OnSelectedCurry selectedCurry, ArrayList<DataModel> data) {
        this.dataSet = data;
        onSelectedCurry = selectedCurry;
        this.context = context;
    }

    public interface OnSelectedCurry {
    void onClickedCurry(DataModel data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewprice = holder.textViewprice;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewprice.setText(String.format("₹%s", dataSet.get(listPosition).getPrice()));
        imageView.setContentDescription(dataSet.get(listPosition).getName());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
        Glide.with(context).load(dataSet.get(listPosition).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .override(600, 400)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
