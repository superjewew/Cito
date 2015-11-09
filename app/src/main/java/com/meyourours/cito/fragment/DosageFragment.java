package com.meyourours.cito.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosageFragment extends Fragment {

    EditText editAge, editWeight;


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
        Spinner medicineSpinner = (Spinner) rootView.findViewById(R.id.spinner_medicine);
        String[] medicineName = getActivity().getResources().getStringArray(R.array.dosage_medicine_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, medicineName);
        medicineSpinner.setAdapter(adapter);

        medicineSpinner.setOnItemClickListener(new Spinner.OnItemClickListener() {
            @Override
            public boolean onItemClick(Spinner parent, View view, int position, long id) {
                editAge.setEnabled(true);
                editWeight.setEnabled(true);
                return true;
            }
        });

        return rootView;
    }


}
