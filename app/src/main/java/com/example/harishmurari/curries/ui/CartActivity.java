package com.example.harishmurari.curries.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.adapters.CartAdapter;
import com.example.harishmurari.curries.data.CurryContract;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CART_LOADER = 2;

    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private TextView totalPrice;
    private CartAdapter cartAdapter;
    private Button checkoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cart_toolbar);
        toolbar.setTitle(R.string.shopping_cart_title);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        emptyTextView = (TextView) findViewById(R.id.text_view_empty_cart);
        totalPrice = (TextView) findViewById(R.id.text_view_total_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), layoutManager.getOrientation());
        cartAdapter = new CartAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cartAdapter);

        checkoutButton = (Button) findViewById(R.id.button_cart_checkout);
        checkoutButton.setOnClickListener(this);
        getSupportLoaderManager().initLoader(CART_LOADER, null, this);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                CurryContract.CurryEntry._ID,
                CurryContract.CurryEntry.COLUMN_CURRY_NAME,
                CurryContract.CurryEntry.COLUMN_CURRY_PRICE,
                CurryContract.CurryEntry.COLUMN_CART_QUANTITY
        };
        String selection = CurryContract.CurryEntry.COLUMN_CART_QUANTITY + " > 0";
        return new CursorLoader(this,
                CurryContract.CurryEntry.CONTENT_URI,
                projection,
                selection,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            cartAdapter.swapCursor(data);

            int price = calculateTotal(data);
            totalPrice.setText(getString(R.string.shopping_cart_total, price));

            boolean emptyCart = data.getCount() == 0;
            emptyTextView.setVisibility(emptyCart ? View.VISIBLE : View.GONE);
            checkoutButton.setEnabled(!emptyCart);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cartAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cart_checkout:
                Snackbar.make(v, "Going to check out", Snackbar.LENGTH_LONG).show();
            break;
        }
    }

    /**
     * Returns the total price of the shopping cart
     * @param cursor The cursor containing the items in the cart
     * @return The total price of the cart
     */
    private int calculateTotal(Cursor cursor) {
        if (cursor == null) {
            return 0;
        }
        int total = 0;
        while (cursor.moveToNext()) {
            int quantity = cursor.getInt(
                    cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CART_QUANTITY));
            int price = cursor.getInt(
                    cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CURRY_PRICE));
            total = total + (quantity * price);
        }
        return total;
    }
}
