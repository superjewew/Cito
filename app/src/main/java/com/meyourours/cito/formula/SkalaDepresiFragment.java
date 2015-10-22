package com.meyourours.cito.formula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.meyourours.cito.R;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkalaDepresiFragment extends FormulaFragment implements CompoundButton.OnCheckedChangeListener {

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10,
    checkBox11, checkBox12, checkBox13, checkBox14, checkBox15;
    private TextView textViewResult;
    private int depressionScore = 5;
    private int[] scores = new int[15];

    public SkalaDepresiFragment() {
        // Required empty public constructor
        setmId(100);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_skala_depresi, container, false);
        checkBox1 = (CheckBox) rootView.findViewById(R.id.check_1);
        checkBox2 = (CheckBox) rootView.findViewById(R.id.check_2);
        checkBox3 = (CheckBox) rootView.findViewById(R.id.check_3);
        checkBox4 = (CheckBox) rootView.findViewById(R.id.check_4);
        checkBox5 = (CheckBox) rootView.findViewById(R.id.check_5);
        checkBox6 = (CheckBox) rootView.findViewById(R.id.check_6);
        checkBox7 = (CheckBox) rootView.findViewById(R.id.check_7);
        checkBox8 = (CheckBox) rootView.findViewById(R.id.check_8);
        checkBox9 = (CheckBox) rootView.findViewById(R.id.check_9);
        checkBox10 = (CheckBox) rootView.findViewById(R.id.check_10);
        checkBox11 = (CheckBox) rootView.findViewById(R.id.check_11);
        checkBox12 = (CheckBox) rootView.findViewById(R.id.check_12);
        checkBox13 = (CheckBox) rootView.findViewById(R.id.check_13);
        checkBox14 = (CheckBox) rootView.findViewById(R.id.check_14);
        checkBox15 = (CheckBox) rootView.findViewById(R.id.check_15);
        textViewResult = (TextView) rootView.findViewById(R.id.text_result);

        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);
        checkBox5.setOnCheckedChangeListener(this);
        checkBox6.setOnCheckedChangeListener(this);
        checkBox7.setOnCheckedChangeListener(this);
        checkBox8.setOnCheckedChangeListener(this);
        checkBox9.setOnCheckedChangeListener(this);
        checkBox10.setOnCheckedChangeListener(this);
        checkBox11.setOnCheckedChangeListener(this);
        checkBox12.setOnCheckedChangeListener(this);
        checkBox13.setOnCheckedChangeListener(this);
        checkBox14.setOnCheckedChangeListener(this);
        checkBox15.setOnCheckedChangeListener(this);


        return rootView;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        depressionScore = 0;
        int tag = Integer.valueOf(buttonView.getTag().toString());
        if(buttonView == checkBox1 || buttonView == checkBox5 || buttonView == checkBox7 || buttonView == checkBox11 || buttonView == checkBox13) {
            if(isChecked)
                scores[tag] = 0;
            else
                scores[tag] = 1;
        } else {
            if(isChecked)
                scores[tag] = 1;
            else
                scores[tag] = 0;
        }

        for(int i : scores) {
            depressionScore += i;
        }

        if(depressionScore > 5) {
            textViewResult.setText("Kemungkinan Depressi");
        } else {
            textViewResult.setText("Tidak Depressi");
        }
    }
}
