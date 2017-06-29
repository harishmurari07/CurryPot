package com.example.harishmurari.curries.adapters;

import android.content.res.Resources;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.data.CurrryCartHelper;
import com.example.harishmurari.curries.data.CurryContract;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Cursor cursor;

    class CartViewHolder extends RecyclerView.ViewHolder {

        int cartItemId;
        TextView textViewName;
        TextView textViewprice;
        TextView textViewItemQuantity;
        TextView textViewTotalPrice;
        Button buttonAdd, buttonSubtract;
        ImageButton buttonRemove;


        CartViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.curry_item_name);
            this.textViewprice = (TextView) itemView.findViewById(R.id.curry_item_price);
            this.textViewItemQuantity = (TextView) itemView.findViewById(R.id.text_view_item_quantity);
            this.textViewTotalPrice = (TextView) itemView.findViewById(R.id.text_view_total_price);
            buttonSubtract = (Button) itemView.findViewById(R.id.button_quantity_subtract);
            buttonAdd = (Button) itemView.findViewById(R.id.button_quantity_add);
            buttonRemove = (ImageButton) itemView.findViewById(R.id.imagebutton_remove_cart);

            buttonSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurrryCartHelper.subtractCartQuantity(v.getContext(), cartItemId, 1);
                    updateUi();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurrryCartHelper.addCartQuantity(v.getContext(), cartItemId, 1);
                    updateUi();
                }
            });

            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int previousQuantity = Integer.parseInt(textViewItemQuantity.getText().toString());

                    CurrryCartHelper.removeFromCart(v.getContext(), cartItemId);
                    notifyItemRemoved(getAdapterPosition());

                    Snackbar.make(v, String.format("'%s' removed from cart", textViewName.getText()), Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CurrryCartHelper.addCartQuantity(v.getContext(), cartItemId, previousQuantity);
                                }
                            })
                            .show();
                }
            });
        }

        /**
         * Updates the UI after first view binding after each user interaction.
         */
        private void updateUi() {
            // Disable the subtraction button if there's only one quantity for the item.
            // Enable otherwise.
            if (1 == Integer.parseInt(textViewItemQuantity.getText().toString())) {
                buttonSubtract.setEnabled(false);
            } else {
                buttonSubtract.setEnabled(true);
            }
        }
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item_, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        if (cursor == null) {
            return;
        }
        cursor.moveToPosition(position);

        int id = cursor.getColumnIndexOrThrow(CurryContract.CurryEntry._ID);
        String name = cursor.getString(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CURRY_NAME));
        int price = cursor.getInt(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CURRY_PRICE));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CART_QUANTITY));
        int totalPrice = price * quantity;

        Resources resources = holder.itemView.getContext().getResources();
        String itemPrice = resources.getQuantityString(R.plurals.number_of_items, price, price);
        String totalPriceString = resources.getQuantityString(R.plurals.number_of_items, totalPrice, totalPrice);
        holder.cartItemId = cursor.getInt(id);
        holder.textViewName.setText(name);
        holder.textViewprice.setText(String.format("₹%s", itemPrice));
        holder.textViewItemQuantity.setText(String.valueOf(quantity));
        holder.textViewTotalPrice.setText(String.format("₹%s", totalPriceString));
        holder.updateUi();
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }
}
