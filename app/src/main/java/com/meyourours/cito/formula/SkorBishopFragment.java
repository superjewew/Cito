package com.meyourours.cito.formula;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.meyourours.cito.R;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.TextView;


public class SkorBishopFragment extends FormulaFragment {


    private Spinner spinnerPosition;
    private Spinner spinnerConsistency;
    private Spinner spinnerEffacement;
    private Spinner spinnerDilation;
    private Spinner spinnerFetal;
    private TextView textBishop, textResult;
    private ArrayAdapter<CharSequence> adapter;

    int pos, consistency, effacement, dilation, fetal;

    public SkorBishopFragment() {
        // Required empty public constructor
        setmId(300);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_skorbishop, container, false);

        spinnerPosition = (Spinner) rootView.findViewById(R.id.spinner_position);
        spinnerConsistency = (Spinner) rootView.findViewById(R.id.spinner_consistency);
        spinnerEffacement = (Spinner) rootView.findViewById(R.id.spinner_effacement);
        spinnerDilation = (Spinner) rootView.findViewById(R.id.spinner_dilation);
        spinnerFetal = (Spinner) rootView.findViewById(R.id.spinner_fetal);
        textBishop = (TextView) rootView.findViewById(R.id.text_bishop);
        textResult = (TextView) rootView.findViewById(R.id.text_result);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.position_array, R.layout.row_spn);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosition.setAdapter(adapter);
        spinnerPosition.setSelection(0);
        spinnerPosition.setOnItemSelectedListener(listener);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.consistency_array, R.layout.row_spn);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConsistency.setAdapter(adapter);
        spinnerConsistency.setSelection(0);
        spinnerConsistency.setOnItemSelectedListener(listener);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.effacement_array, R.layout.row_spn);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEffacement.setAdapter(adapter);
        spinnerEffacement.setSelection(0);
        spinnerEffacement.setOnItemSelectedListener(listener);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.dilation_array, R.layout.row_spn);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDilation.setAdapter(adapter);
        spinnerDilation.setSelection(0);
        spinnerDilation.setOnItemSelectedListener(listener);

        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fetal_array, R.layout.row_spn);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFetal.setAdapter(adapter);
        spinnerFetal.setSelection(0);
        spinnerFetal.setOnItemSelectedListener(listener);

        return rootView;
    }

    Spinner.OnItemSelectedListener listener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Spinner parent, View view, int position, long id) {
            if(parent == spinnerPosition) {
                pos = position;
            } else if(parent == spinnerConsistency) {
                consistency = position;
            } else if(parent == spinnerEffacement) {
                effacement = position;
            } else if(parent == spinnerDilation) {
                dilation = position;
            } else if(parent == spinnerFetal) {
                fetal = position;
            }

            int score = Formulas.getBishopScore(pos, consistency, effacement, dilation, fetal);
            textBishop.setText("" + score);
            textResult.setText(Formulas.getBishopString(score));
        }
    };

}
