package com.meyourours.cito.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.meyourours.cito.CustomViewPager;
import com.meyourours.cito.DetailsFragment;
import com.meyourours.cito.R;
import com.meyourours.cito.contentprovider.FormulaContentProvider;
import com.meyourours.cito.database.FormulaTable;
import com.meyourours.cito.formula.FormulaFragment;
import com.rey.material.widget.TabPageIndicator;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CountActivity extends ActionBarActivity {

    @Bind(R.id.main_tpi) TabPageIndicator tpi;
    @Bind(R.id.main_vp) CustomViewPager vp;

    static String formulaFragmentName;
    static String formulaTitle;
    static FormulaFragment formulaFragment;

    private Uri formulaUri;
    private SharedPreferences prefs;
    private int formulaId;
    private int useCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        formulaTitle = getIntent().getStringExtra("formula");

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(formulaTitle);
        }

        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mPagerAdapter);
        tpi.setViewPager(vp);

        boolean isFirstTime = true;

        formulaUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState.getParcelable(FormulaContentProvider.CONTENT_ITEM_TYPE);

//        if(extras != null) {
//            formulaUri = extras.getParcelable(FormulaContentProvider.CONTENT_ITEM_TYPE);
//        }

        formulaFragmentName = formulaTitle.replace(" ", "");
        try {
            formulaFragment = (FormulaFragment) Class.forName("com.meyourours.cito.formula."
                    + formulaFragmentName + "Fragment").newInstance();
            formulaId = formulaFragment.getmId();
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            isFirstTime = prefs.getBoolean("com.meyourours.cito.formula." + formulaFragmentName
                    + ".firstTime", true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!isFirstTime) {
            formulaUri = Uri.parse(FormulaContentProvider.CONTENT_URI + "/" + formulaId);
            Log.d("COUNT_ACTIVITY", "Formula Id: " + formulaId);
            Cursor cursor = getContentResolver().query(formulaUri, null, null, null, null);
            cursor.moveToFirst();
            useCount = cursor.getInt(cursor.getColumnIndex(FormulaTable.COLUMN_USE_COUNT));
            cursor.close();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putParcelable(FormulaContentProvider.CONTENT_ITEM_TYPE, formulaUri);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                return formulaFragment;
            } else {
                return DetailsFragment.newInstance(formulaFragmentName.toLowerCase());
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0) {
                return "FORMULA";
            } else {
                return "DETAILS";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void saveState() {
        Calendar c = Calendar.getInstance();

        ContentValues values = new ContentValues();
        values.put(FormulaTable.COLUMN_ID, formulaId);
        values.put(FormulaTable.COLUMN_NAME, formulaTitle);
        values.put(FormulaTable.COLUMN_CATEGORY, "");
        values.put(FormulaTable.COLUMN_USE_TIME, c.getTimeInMillis());
        values.put(FormulaTable.COLUMN_USE_COUNT, useCount + 1);

        if (formulaUri == null) {
            // New todo
            formulaUri = getContentResolver().insert(FormulaContentProvider.CONTENT_URI, values);
        } else {
            // Update todo
            int rowsUpdated = getContentResolver().update(formulaUri, values, null, null);
            Log.d("COUNT_ACTIVITY", "Rows updated: " + rowsUpdated);
        }

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("com.meyourours.cito.formula." + formulaFragmentName + ".firstTime", false);
        edit.apply();

    }
}
