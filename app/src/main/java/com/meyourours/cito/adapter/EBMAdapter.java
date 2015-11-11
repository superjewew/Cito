package com.meyourours.cito.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.TextView;

import java.util.ArrayList;

/**
 * Created by norman on 11/5/15.
 */
public class EBMAdapter extends ArrayAdapter<String> {

    private final Activity mContext;
    private ArrayList<String> input;

    static class ViewHolder {
        public EditText sensitivity;
        public EditText specivicity;
    }

    public EBMAdapter(Activity context, ArrayList<String> tests) {
        super(context, R.layout.layout_list_ebm_item, tests);
        mContext = context;
        input = tests;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout_list_ebm_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.sensitivity = (EditText) rowView.findViewById(R.id.edit_sensitivity);
            viewHolder.specivicity = (EditText) rowView.findViewById(R.id.edit_specitivity);
            Spinner spinnerTest = (Spinner) rowView.findViewById(R.id.spinner_test);
            ArrayAdapter<String> adapterTest = new ArrayAdapter<>(mContext, R.layout.row_spn, new String[]{"Test Test"});
            adapterTest.setDropDownViewResource(R.layout.row_spn_dropdown);
            spinnerTest.setAdapter(adapterTest);

            spinnerTest.setOnItemClickListener(new Spinner.OnItemClickListener() {
                @Override
                public boolean onItemClick(Spinner parent, View view, int position, long id) {
                    viewHolder.sensitivity.setEnabled(true);
                    viewHolder.specivicity.setEnabled(true);
                    return false;
                }
            });



            rowView.setTag(viewHolder);
        }

        return rowView;
    }
}
