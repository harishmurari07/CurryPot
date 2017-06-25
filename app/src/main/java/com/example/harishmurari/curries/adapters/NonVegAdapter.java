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
import com.example.harishmurari.curries.model.CartItems;
import com.example.harishmurari.curries.model.CurryItem;

import java.util.ArrayList;

/**
 * Created by harishmurari on 6/17/2017.
 */

public class NonVegAdapter extends RecyclerView.Adapter<NonVegAdapter.NonVegViewHolder> {

    private ArrayList<CurryItem> dataSet;
    private Context context;
    private OnNonVegSelectedCurry onSelectedCurry;
    private CartItems cartItems;

    public class NonVegViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewName;
        TextView textViewPrice;
        ImageView imageViewIcon;
        Button add, remove;

        public NonVegViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.curry_name);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.curry_price);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.curry_image);
            this.add = (Button) itemView.findViewById(R.id.add_button);
            this.remove = (Button) itemView.findViewById(R.id.remove_button);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    add.setVisibility(View.GONE);
                    remove.setVisibility(View.VISIBLE);
                    cartItems.addItemToCart(dataSet.get(getAdapterPosition()));
                    Toast.makeText(context, "Added : "+ cartItems.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove.setVisibility(view.GONE);
                    add.setVisibility(View.VISIBLE);
                    if (cartItems.checkProductInCart(dataSet.get(getAdapterPosition()))) {
                        cartItems.removeItemfromCart(dataSet.get(getAdapterPosition()));
                        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            onSelectedCurry.onClickedCurry(dataSet.get(getAdapterPosition()));
        }
    }

    public NonVegAdapter(Context context, OnNonVegSelectedCurry selectedCurry, ArrayList<CurryItem> data ) {
        this.dataSet = data;
        onSelectedCurry = selectedCurry;
        this.context = context;
        this.cartItems = new CartItems();
    }

    public interface OnNonVegSelectedCurry {
        void onClickedCurry(CurryItem data);
    }

    @Override
    public NonVegViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);

        return new NonVegViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NonVegAdapter.NonVegViewHolder holder, int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewprice = holder.textViewPrice;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getCurryName());
        textViewprice.setText(String.format("₹%s", dataSet.get(listPosition).getCurryPrice()));
        imageView.setContentDescription(dataSet.get(listPosition).getCurryName());
        imageView.setImageResource(dataSet.get(listPosition).getCurryImage());
        Glide.with(context).load(dataSet.get(listPosition).getCurryImage())
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
