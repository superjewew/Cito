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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkorCURBFragment extends FormulaFragment implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.check_age) CheckBox checkBoxAge;
    @Bind(R.id.check_confusion) CheckBox checkBoxConfusion;
    @Bind(R.id.check_bun) CheckBox checkBoxBun;
    @Bind(R.id.check_respiratory) CheckBox checkBoxRespiratory;
    @Bind(R.id.check_blood_pressure) CheckBox checkBoxBloodPressure;
    @Bind(R.id.text_curb) TextView textViewCurb;
    @Bind(R.id.text_result) TextView textViewResult;
    private int curbScore;


    public SkorCURBFragment() {
        // Required empty public constructor
        setmId(200);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_skor_curb, container, false);
        ButterKnife.bind(this, rootView);

        checkBoxAge.setOnCheckedChangeListener(this);
        checkBoxConfusion.setOnCheckedChangeListener(this);
        checkBoxBun.setOnCheckedChangeListener(this);
        checkBoxRespiratory.setOnCheckedChangeListener(this);
        checkBoxBloodPressure.setOnCheckedChangeListener(this);

        setTrackerName("SkorCURBFragment");

        return rootView;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            curbScore++;
        } else {
            curbScore--;
        }
        textViewCurb.setText("" + curbScore);
        textViewResult.setText("Mortality Risk " + Formulas.countCURB(curbScore) + "%");
    }
}
