package com.meyourours.cito.formula;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.meyourours.cito.R;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.EditText;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaldiyonoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaldiyonoFragment extends FormulaFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CheckBox checkBoxHaus;
    private CheckBox checkBoxSistolik6090;
    private CheckBox checkBoxSistolik60;
    private CheckBox checkBoxNadi;
    private CheckBox checkBoxApatis;
    private CheckBox checkBoxSomnolen;
    private CheckBox checkBoxNafas;
    private CheckBox checkBoxFacies;
    private CheckBox checkBoxVox;
    private CheckBox checkBoxTurgor;
    private CheckBox checkBoxWasher;
    private CheckBox checkBoxCold;
    private CheckBox checkBoxSianosis;
    private CheckBox checkBoxAge1;
    private CheckBox checkBoxAge2;
    private EditText editTextWeight;
    private TextView textDaldiyono, textResult;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaldiyonoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DaldiyonoFragment newInstance(String param1, String param2) {
        DaldiyonoFragment fragment = new DaldiyonoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DaldiyonoFragment() {
        // Required empty public constructor
        setmId(400);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_daldiyono, container, false);

        checkBoxHaus = (CheckBox) rootView.findViewById(R.id.checkBoxHaus);
        checkBoxSistolik6090 = (CheckBox) rootView.findViewById(R.id.checkBoxSistolik6090);
        checkBoxSistolik60 = (CheckBox) rootView.findViewById(R.id.checkBoxSistolik60);
        checkBoxNadi = (CheckBox) rootView.findViewById(R.id.checkBoxNadi);
        checkBoxApatis = (CheckBox) rootView.findViewById(R.id.checkBoxApatis);
        checkBoxSomnolen = (CheckBox) rootView.findViewById(R.id.checkBoxSomnolen);
        checkBoxNafas = (CheckBox) rootView.findViewById(R.id.checkBoxNafas);
        checkBoxFacies = (CheckBox) rootView.findViewById(R.id.checkBoxFacies);
        checkBoxVox = (CheckBox) rootView.findViewById(R.id.checkBoxVox);
        checkBoxTurgor = (CheckBox) rootView.findViewById(R.id.checkBoxTurgor);
        checkBoxWasher = (CheckBox) rootView.findViewById(R.id.checkBoxWasher);
        checkBoxCold = (CheckBox) rootView.findViewById(R.id.checkBoxCold);
        checkBoxSianosis = (CheckBox) rootView.findViewById(R.id.checkBoxSianosis);
        checkBoxAge1 = (CheckBox) rootView.findViewById(R.id.checkBoxAge1);
        checkBoxAge2 = (CheckBox) rootView.findViewById(R.id.checkBoxAge2);

        editTextWeight = (EditText) rootView.findViewById(R.id.edit_weight);
        textDaldiyono = (TextView) rootView.findViewById(R.id.text_daldiyono);
        textResult = (TextView) rootView.findViewById(R.id.text_result);

        checkBoxHaus.setOnCheckedChangeListener(listener);
        checkBoxSistolik6090.setOnCheckedChangeListener(listener);
        checkBoxSistolik60.setOnCheckedChangeListener(listener);
        checkBoxNadi.setOnCheckedChangeListener(listener);
        checkBoxApatis.setOnCheckedChangeListener(listener);
        checkBoxSomnolen.setOnCheckedChangeListener(listener);
        checkBoxNafas.setOnCheckedChangeListener(listener);
        checkBoxFacies.setOnCheckedChangeListener(listener);
        checkBoxVox.setOnCheckedChangeListener(listener);
        checkBoxTurgor.setOnCheckedChangeListener(listener);
        checkBoxWasher.setOnCheckedChangeListener(listener);
        checkBoxCold.setOnCheckedChangeListener(listener);
        checkBoxSianosis.setOnCheckedChangeListener(listener);
        checkBoxAge1.setOnCheckedChangeListener(listener);
        checkBoxAge2.setOnCheckedChangeListener(listener);

        setTrackerName("DaldiyonoFragment");

        return rootView;
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int res = 0;
            res += checkBoxHaus.isChecked() ? 1 : 0;
            res += checkBoxSistolik6090.isChecked() ? 1 : 0;
            res += checkBoxSistolik60.isChecked() ? 2 : 0;
            res += checkBoxNadi.isChecked() ? 1 : 0;
            res += checkBoxApatis.isChecked() ? 1 : 0;
            res += checkBoxSomnolen.isChecked() ? 2 : 0;
            res += checkBoxNafas.isChecked() ? 1 : 0;
            res += checkBoxFacies.isChecked() ? 2 : 0;
            res += checkBoxVox.isChecked() ? 2 : 0;
            res += checkBoxTurgor.isChecked() ? 1 : 0;
            res += checkBoxWasher.isChecked() ? 1 : 0;
            res += checkBoxCold.isChecked() ? 1 : 0;
            res += checkBoxSianosis.isChecked() ? 2 : 0;
            res += checkBoxAge1.isChecked() ? -1 : 0;
            res += checkBoxAge2.isChecked() ? -2 : 0;

            textDaldiyono.setText("" + res);
            if(!editTextWeight.getText().toString().equals("")) {
                textResult.setText("Defisit cairan (cc) : " + Formulas.getDaldiyonoString(res, Integer.parseInt(editTextWeight.getText().toString())));
            }
        }
    };
}
