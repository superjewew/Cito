package com.meyourours.cito.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.meyourours.cito.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenShotableFragment extends Fragment {


    private View containerView;
    private Bitmap bitmap;

    public ScreenShotableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

}
