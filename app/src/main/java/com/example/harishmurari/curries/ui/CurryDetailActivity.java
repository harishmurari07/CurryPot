package com.example.harishmurari.curries.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harishmurari.curries.MyData;
import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.data.CurrryCartHelper;
import com.example.harishmurari.curries.data.CurryContract;
import com.example.harishmurari.curries.model.CurryItem;

/**
 * Created by kaika on 6/28/2017.
 */

public class CurryDetailActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {


    private static final String INTENT_EXTRA_ITEM = "item_id";
    private static final int CURRY_DETAIL_LOADER = 3;

    private int mItemId;
    private CurryItem curryItem;

    private Cursor cursor;

    private Toolbar toolbar;
    private TextView curryDescription;
    private TextView curryPrice;
    private ImageView curryImage;

    public static void startActivity(Context context, int itemPosition) {
        Intent i = new Intent(context, CurryDetailActivity.class);
        i.putExtra(INTENT_EXTRA_ITEM, itemPosition);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curry_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle("");
        curryDescription = (TextView) findViewById(R.id.text_view_item_description);
        curryPrice = (TextView) findViewById(R.id.text_view_item_price);
        curryImage = (ImageView) findViewById(R.id.curryImage);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        fab.setOnClickListener(this);

        mItemId = getIntent().getIntExtra(INTENT_EXTRA_ITEM, 0);

        getSupportLoaderManager().initLoader(CURRY_DETAIL_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                CurryContract.CurryEntry._ID,
                CurryContract.CurryEntry.COLUMN_CURRY_NAME,
                CurryContract.CurryEntry.COLUMN_CURRY_DESCRIPTION,
                CurryContract.CurryEntry.COLUMN_CURRY_PRICE,
                CurryContract.CurryEntry.COLUMN_CURRY_IMAGE
        };
        String selection = CurryContract.CurryEntry._ID + " = " + mItemId;
        return new CursorLoader(this,
                CurryContract.CurryEntry.CONTENT_URI,
                projection,
                selection,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cursor = data;
        cursor.moveToFirst();

        curryItem = new CurryItem(data);
        toolbar.setTitle(curryItem.curryName);
        curryDescription.setText(curryItem.curryDescription);
        curryPrice.setText(getString(R.string.curry_price, curryItem.curryPrice));
        curryImage.setImageResource(MyData.getResourceDrawable(curryItem.curryImage));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursor = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (curryItem == null)
                    return;

                int quantity = 1;
                CurrryCartHelper.addCartQuantity(this, curryItem.id, quantity);
                Snackbar.make(v, R.string.shopping_cart_item_added, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
