package com.meyourours.cito.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meyourours.cito.CitoApplication;
import com.meyourours.cito.fragment.CategoryFragment;
import com.meyourours.cito.R;
import com.meyourours.cito.adapter.FormulaDetailAdapter;
import com.meyourours.cito.formula.Formulas;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class FormulasActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private int formulaList;
    FormulaDetailAdapter adapter;
    int categoryId;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_detail);

        CitoApplication application = (CitoApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        Intent intent = getIntent();
        int category = intent.getIntExtra(CategoryFragment.EXTRA_CATEGORY, -1);
        categoryId = intent.getIntExtra(CategoryFragment.EXTRA_CATEGORY_ID, -1);

        StickyListHeadersListView detailList = (StickyListHeadersListView) findViewById(R.id.list);
        if(category != -1) {
            formulaList = Formulas.FORMULA_LIST[category];
            getSupportActionBar().setTitle(Formulas.CATEGORY[category]);
            adapter = new FormulaDetailAdapter(this, formulaList, Formulas.FORMULA_DESC[category]);
        }
        detailList.setAdapter(adapter);
        detailList.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("FormulaActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formula_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String className = this.getResources().getStringArray(formulaList)[position];
//        className = className.replace(" ", "");
//        startFormulaActivity("com.meyourours.cito.formula." + className);
        Formulas.startCountActivity(this, className);
//        intent.putExtra("id", id + categoryId);
//        Uri todoUri = Uri.parse(FormulaContentProvider.CONTENT_URI + "/" + id + categoryId);
//        intent.putExtra(FormulaContentProvider.CONTENT_ITEM_TYPE, todoUri);

    }

    public void startFormulaActivity(String className) {
        Class<?> c = null;
        if(className != null) {
            try {
                c = Class.forName(className);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
