package com.meyourours.cito.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.meyourours.cito.formula.Formulas;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosageFragment extends Fragment {

    EditText editAge, editWeight;
    TextView textDosage;
    int medicine, age, weight;
    boolean isAgeExist, isWeightExist;


    public DosageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dosage, container, false);

        editAge = (EditText) rootView.findViewById(R.id.edit_age);
        editWeight = (EditText) rootView.findViewById(R.id.edit_weight);
        textDosage = (TextView) rootView.findViewById(R.id.text_dosage);
        Spinner medicineSpinner = (Spinner) rootView.findViewById(R.id.spinner_medicine);
        String[] medicineName = getActivity().getResources().getStringArray(R.array.dosage_medicine_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, medicineName);
        medicineSpinner.setAdapter(adapter);

        medicineSpinner.setOnItemClickListener(new Spinner.OnItemClickListener() {
            @Override
            public boolean onItemClick(Spinner parent, View view, int position, long id) {
                editAge.setEnabled(true);
                editWeight.setEnabled(true);
                medicine = position;
                return true;
            }
        });

        editAge.addTextChangedListener(ageWatcher);
        editWeight.addTextChangedListener(weightWatcher);

        return rootView;
    }

    private final TextWatcher ageWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() != 0) {
                age = Integer.parseInt(s.toString());
                isAgeExist = true;
                if(isWeightExist) {
                    textDosage.setText(Formulas.getDosage(medicine, age, weight));
                }
            } else {
                isAgeExist = false;
            }
        }
    };

    private final TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() != 0) {
                weight = Integer.parseInt(s.toString());
                isWeightExist = true;
                if(isAgeExist) {
                    textDosage.setText(Formulas.getDosage(medicine, age, weight));
                }
            } else {
                isWeightExist = false;
            }
        }
    };
}
