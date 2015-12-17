package com.meyourours.cito.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meyourours.cito.CitoApplication;
import com.meyourours.cito.R;
import com.squareup.picasso.Picasso;


public class SplashActivity extends Activity {

    private static int SPLASH_TIMEOUT = 2000;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CitoApplication application = (CitoApplication) getApplication();
        mTracker = application.getDefaultTracker();

        ImageView splashImage = (ImageView) findViewById(R.id.splash_image);
        Picasso.with(this).load(R.drawable.img_splash)
                .into(splashImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("SplashActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
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
}
