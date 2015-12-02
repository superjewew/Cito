package com.meyourours.cito.formula;


import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkorNuryFragment extends FormulaFragment {

    TextView textResult;
    EditText editDistance, editAge, editHeight, editWeight;
    Spinner spinnerSex;

    public SkorNuryFragment() {
        setmId(700);
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nuri, container, false);

        String[] sex = getActivity().getResources().getStringArray(R.array.sex_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, sex);

        editDistance = (EditText) rootView.findViewById(R.id.edit_distance);
        editAge = (EditText) rootView.findViewById(R.id.edit_age);
        editHeight = (EditText) rootView.findViewById(R.id.edit_height);
        editWeight = (EditText) rootView.findViewById(R.id.edit_weight);
        spinnerSex = (Spinner) rootView.findViewById(R.id.spinner_sex);
        textResult = (TextView) rootView.findViewById(R.id.text_o2);

        editDistance.addTextChangedListener(watcher);
        editAge.addTextChangedListener(watcher);
        editHeight.addTextChangedListener(watcher);
        editWeight.addTextChangedListener(watcher);
        spinnerSex.setAdapter(adapter);

        spinnerSex.setOnItemClickListener(listener);

        return rootView;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!isTextNull(editDistance) && !isTextNull(editAge) && !isTextNull(editHeight) && !isTextNull(editWeight)) {
                float distance = Float.parseFloat(editDistance.getText().toString());
                int age = Integer.parseInt(editAge.getText().toString());
                int height = Integer.parseInt(editHeight.getText().toString());
                int weight = Integer.parseInt(editWeight.getText().toString());
                float res = Formulas.getMaxVolumeO2(distance, age, height, weight, spinnerSex.getSelectedItemPosition());
                textResult.setText("" + res + " liter");
            }
        }
    };

    Spinner.OnItemClickListener listener = new Spinner.OnItemClickListener() {
        @Override
        public boolean onItemClick(Spinner parent, View view, int position, long id) {
            if(!isTextNull(editDistance) && !isTextNull(editAge) && !isTextNull(editHeight) && !isTextNull(editWeight)) {
                float distance = Float.parseFloat(editDistance.getText().toString());
                int age = Integer.parseInt(editAge.getText().toString());
                int height = Integer.parseInt(editHeight.getText().toString());
                int weight = Integer.parseInt(editWeight.getText().toString());
                float res = Formulas.getMaxVolumeO2(distance, age, height, weight, position);
                textResult.setText("" + res + " liter");
            }
            return true;
        }
    };

    private boolean isTextNull(EditText tv) {
        return tv.getText().toString().equals("");
    }
}
