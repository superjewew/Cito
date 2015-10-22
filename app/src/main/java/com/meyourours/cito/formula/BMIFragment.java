package com.meyourours.cito.formula;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meyourours.cito.R;
import com.rey.material.widget.EditText;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BMIFragment extends FormulaFragment {

    Handler countHandler;
    EditText editTextWeight;
    EditText editTextHeight;
    TextView textViewResult;
    TextView textViewBMI;

    public BMIFragment() {
        // Required empty public constructor
        setmId(0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_bmi, container, false);
        textViewBMI = (TextView) rootView.findViewById(R.id.text_bmi);
        textViewResult = (TextView) rootView.findViewById(R.id.text_result);
        editTextWeight = (EditText) rootView.findViewById(R.id.edit_weight);
        editTextHeight = (EditText) rootView.findViewById(R.id.edit_height);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countHandler = new Handler();
        startCounting();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopCounting();
    }

    Runnable mBMICalculate = new Runnable() {
        @Override
        public void run() {
            double weight = 0, height = 0, res = 0;
            String temp = editTextWeight.getText().toString();
            if(!temp.equals("")) {
                weight = Double.valueOf(temp);
            }
            temp = editTextHeight.getText().toString();
            if(!temp.equals("")) {
                height = Double.valueOf(temp);
            }

            if(weight != 0 && height != 0) {
                res = Formulas.countBMI(height, weight);
                textViewBMI.setText(String.format("%.2f", res) + " kg/m2");
            }

            if(res != 0) {
                if(res <= 18.5) textViewResult.setText("berat badan UNDERWEIGHT");
                else if (res > 18.5 && res <= 23) textViewResult.setText("berat badan ACCEPTABLE RISK");
                else if (res > 23 && res <= 27.5) textViewResult.setText("berat badan INCREASED RISK");
                else if (res > 27.5) textViewResult.setText("berat badan HIGHER RISK");
            }
            countHandler.postDelayed(mBMICalculate, 2000);
        }
    };

    private void startCounting() {
        mBMICalculate.run();
    }

    private void stopCounting() {
        countHandler.removeCallbacks(mBMICalculate);
    }
}
