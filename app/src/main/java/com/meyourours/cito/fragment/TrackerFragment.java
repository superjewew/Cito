package com.meyourours.cito.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meyourours.cito.CitoApplication;

/**
 * Created by norman on 12/18/15.
 */
public class TrackerFragment extends Fragment {

    private Tracker mTracker;

    private String trackerName = "FormulaFragment";

    public String getTrackerName() {
        return trackerName;
    }

    public void setTrackerName(String trackerName) {
        this.trackerName = trackerName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CitoApplication application = (CitoApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName(getTrackerName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
