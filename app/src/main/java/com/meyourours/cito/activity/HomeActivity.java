package com.meyourours.cito.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meyourours.cito.CitoApplication;
import com.meyourours.cito.EBMFragment;
import com.meyourours.cito.R;
import com.meyourours.cito.fragment.DosageFragment;
import com.meyourours.cito.fragment.FeedbackFragment;
import com.meyourours.cito.fragment.FormulaMainFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREFS_DOMAIN = "com.myo.cito.prefs";
    private static final String FIRST_LAUNCH = "first_launch";
    private SharedPreferences prefs;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CitoApplication application = (CitoApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs = getSharedPreferences(PREFS_DOMAIN, 0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchFragment(new FormulaMainFragment());

        boolean isFirstTime = prefs.getBoolean(FIRST_LAUNCH, true);
        if(isFirstTime) {
            new MaterialDialog.Builder(this)
                    .title("Cito beta ver.")
                    .content("Aplikasi ini masih dalam tahap pengembangan (beta) sehingga konten yang " +
                            "terdapat di dalam masih sangat sedikit. Kami akan selalu menambahkan " +
                            "rumus-rumus dan konten secara teratur setiap minggu-nya. Kami juga " +
                            "mengharapkan tanggapan anda melalui email atau review di play store. " +
                            "Terima kasih")
                    .positiveText("Ok")
                    .show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(FIRST_LAUNCH, false);
            editor.commit();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("HomeActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rumus) {
            switchFragment(new FormulaMainFragment());
        } else if (id == R.id.nav_dosis) {
            switchFragment(new DosageFragment());
        } else if (id == R.id.nav_ebm) {
            switchFragment(new EBMFragment());
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_feedback) {
            switchFragment(new FeedbackFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer

    }
}
