package com.example.harishmurari.curries.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.data.CurryContract;
import com.example.harishmurari.curries.ui.CurryDetailActivity;

public class CurryAdapter extends RecyclerView.Adapter<CurryAdapter.MyViewHolder> {

    private Cursor cursor;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public int curryItemId;
        public TextView textViewName;
        public TextView textViewprice;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.curry_name);
            this.textViewprice = (TextView) itemView.findViewById(R.id.curry_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurryDetailActivity.startActivity(v.getContext(), curryItemId);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mail_list_item_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (cursor == null) {
            return;
        }
        cursor.moveToPosition(position);

        int id = cursor.getColumnIndexOrThrow(CurryContract.CurryEntry._ID);
        String curryName = cursor.getString(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CURRY_NAME));
        int curryPrice = cursor.getInt(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CURRY_PRICE));
        holder.curryItemId = cursor.getInt(id);
        holder.textViewName.setText(curryName);
        holder.textViewprice.setText(String.format("₹%s", curryPrice));
    }

    public CurryAdapter (Cursor cursor) {
        this.cursor = cursor;
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }
}
