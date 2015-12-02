package com.meyourours.cito;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by norman on 11/26/15.
 */
public class CitoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/AbadiCondensed.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
