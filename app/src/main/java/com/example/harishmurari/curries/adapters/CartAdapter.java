package com.example.harishmurari.curries.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.model.CartItems;
import com.example.harishmurari.curries.model.CurryItem;

import java.util.ArrayList;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<CurryItem> cartItems;
    private Context context;
    private OnCurryQuantitySelected quantitySelected;

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewName;
        TextView textViewprice;
        Spinner quantity;

        public CartViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.curry_name);
            this.textViewprice = (TextView) itemView.findViewById(R.id.curry_price);
            this.quantity = (Spinner) itemView.findViewById(R.id.curry_quantity);

        }

        @Override
        public void onClick(View view) {
//            quantitySelected.onSelectedquantity(cartItems.get(getAdapterPosition()));
        }
    }

    public CartAdapter(Context context, OnCurryQuantitySelected selectedQuantity, ArrayList<CurryItem> data) {
        this.cartItems = data;
        quantitySelected = selectedQuantity;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_main, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
       holder.textViewName.setText(cartItems.get(position).getCurryName());
        holder.textViewprice.setText(cartItems.get(position).getCurryPrice());

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface OnCurryQuantitySelected {
        void onSelectedquantity(CartItems cartItemsData);
    }

    public void itemToAdd(CurryItem itemToAdd) {
        cartItems.add(itemToAdd);
    }

    public void itemToRemove(CurryItem itemToRemove) {
        cartItems.remove(itemToRemove);
    }

}
