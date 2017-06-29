package com.example.harishmurari.curries.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.harishmurari.curries.R;
import com.example.harishmurari.curries.adapters.CurryAdapter;
import com.example.harishmurari.curries.data.CurryContract;
import com.facebook.stetho.Stetho;

/**
 * Created by harishmurari on 6/7/2017.
 */

public class MainMenuActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String INTENT_CURRY_ITEM_EXTRA = "intent_curry_item_extra";

    CurryAdapter curryAdapter;

    private static final int CURRY_LOADER = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initailizeStetho();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        curryAdapter = new CurryAdapter(null);
        recyclerView.setAdapter(curryAdapter);

        getSupportLoaderManager().initLoader(CURRY_LOADER, null, this);
    }

    private void initailizeStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_shopping_cart:
                CartActivity.startActivity(this);
                return true;
            case R.id.menu_purchases:

                return true;
            case R.id.menu_my_profile:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        return new CursorLoader(this,
                CurryContract.CurryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        curryAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        curryAdapter.swapCursor(null);
    }
}
