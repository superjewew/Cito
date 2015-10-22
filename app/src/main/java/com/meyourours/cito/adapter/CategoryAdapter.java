package com.meyourours.cito.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.meyourours.cito.formula.Formulas;
import com.rey.material.widget.TextView;

/**
 * Created by norman on 7/16/15.
 */
public class CategoryAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;

    static class ViewHolder {
        public TextView name;
        public TextView count;
    }

    public CategoryAdapter(Activity context, String[] names) {
        super(context, R.layout.layout_list_formula, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout_list_formula, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.textview_formula);
            viewHolder.count = (TextView) rowView
                    .findViewById(R.id.textview_count);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = names[position];
        int res = Formulas.FORMULA_LIST[position];
        int count = context.getResources().getStringArray(res).length;
        holder.name.setText(s);
        holder.count.setText("" + count);

        return rowView;
    }
}
