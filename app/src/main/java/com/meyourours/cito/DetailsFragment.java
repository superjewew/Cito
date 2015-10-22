package com.meyourours.cito;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String formula) {
        DetailsFragment fragment = new DetailsFragment();

        Bundle args = new Bundle();
        args.putString("formula", formula);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String formula = getArguments().getString("formula");
        int layoutID = getResources().getIdentifier("fragment_detail_" + formula, "layout", getActivity().getPackageName());
        return inflater.inflate(layoutID, container, false);
    }


}
