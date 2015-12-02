package com.meyourours.cito.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meyourours.cito.CustomViewPager;
import com.meyourours.cito.R;
import com.rey.material.widget.TabPageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormulaMainFragment extends Fragment {

    @Bind(R.id.main_tpi) TabPageIndicator tpi;
    @Bind(R.id.main_vp) CustomViewPager vp;

    public FormulaMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_formula_main, container, false);

        ButterKnife.bind(this, rootView);

        PagerAdapter mPagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mPagerAdapter);
        tpi.setViewPager(vp);

        return rootView;
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {
        Fragment[] fragments = {new CategoryFragment(), new MostUsedFormulaFragment(),
                                new RecentFormulaFragment(), new SearchFormulaFragment()};
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "All";
                case 1:
                    return "Sering";
                case 2:
                    return "Terbaru";
                case 3:
                    return "Cari";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

}
