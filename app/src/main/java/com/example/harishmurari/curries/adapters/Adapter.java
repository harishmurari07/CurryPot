package com.example.harishmurari.curries.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.Utility;
import com.example.harishmurari.curries.model.CartItems;
import com.example.harishmurari.curries.model.CurryItem;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<CurryItem> dataSet;
    private Context context;
    private OnSelectedCurry onSelectedCurry;
    private CartItems cartItems;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
                    if (!Utility.isItemAvailable(context, dataSet.get(getAdapterPosition()))) {
                        Utility.saveToCart(context, dataSet.get(getAdapterPosition()).getCurryName(), dataSet.get(getAdapterPosition()));
                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Item already Added", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utility.isItemAvailable(context, dataSet.get(getAdapterPosition()))) {
                        Utility.removeFromCart(context, dataSet.get(getAdapterPosition()).getCurryName());
                        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Item not exists", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            onSelectedCurry.onClickedCurry(dataSet.get(getAdapterPosition()));
        }
    }

    public Adapter(Context context, OnSelectedCurry selectedCurry, ArrayList<CurryItem> data) {
        this.dataSet = data;
        onSelectedCurry = selectedCurry;
        this.context = context;
        this.cartItems = new CartItems();
    }

    public interface OnSelectedCurry {
        void onClickedCurry(CurryItem data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        TextView textViewName = holder.textViewName;
        TextView textViewprice = holder.textViewprice;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(position).getCurryName());
        textViewprice.setText(String.format("₹%s", dataSet.get(position).getCurryPrice()));
        imageView.setContentDescription(dataSet.get(position).getCurryName());
        imageView.setImageResource(dataSet.get(position).getCurryImage());
        Glide.with(context).load(dataSet.get(position).getCurryImage())
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
