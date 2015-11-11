package com.meyourours.cito.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.TextView;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by norman on 7/16/15.
 */
public class FormulaDetailAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private String[] formulas;
    private String[] descFormula;
    private LayoutInflater inflater;

    public FormulaDetailAdapter(Context context, int res, int desc) {
        inflater = LayoutInflater.from(context);
        formulas = context.getResources().getStringArray(res);
        descFormula = context.getResources().getStringArray(desc);
    }

    @Override
    public int getCount() {
        return formulas.length;
    }

    @Override
    public Object getItem(int position) {
        return formulas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_list_formula_detail, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.name);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(formulas[position]);
        holder.desc.setText(descFormula[position]);

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.layout_list_formula_detail_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "" + formulas[position].subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return formulas[position].subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        TextView desc;
    }
}
