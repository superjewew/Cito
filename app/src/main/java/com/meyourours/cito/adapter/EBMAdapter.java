package com.meyourours.cito.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.Spinner;

/**
 * Created by norman on 11/5/15.
 */
public class EBMAdapter extends ArrayAdapter<String> {

    private final Activity mContext;

    public EBMAdapter(Activity context, String[] tests) {
        super(context, R.layout.layout_list_ebm_item, tests);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout_list_ebm_item, null);

            Spinner spinnerTest = (Spinner) rowView.findViewById(R.id.spinner_test);
            ArrayAdapter<String> adapterTest = new ArrayAdapter<>(mContext, R.layout.row_spn, new String[]{"Test Test"});
            adapterTest.setDropDownViewResource(R.layout.row_spn_dropdown);
            spinnerTest.setAdapter(adapterTest);

            spinnerTest.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(Spinner parent, View view, int position, long id) {

                }
            });
        }
        return rowView;
    }
}
