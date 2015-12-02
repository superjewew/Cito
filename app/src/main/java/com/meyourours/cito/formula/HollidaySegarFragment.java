package com.meyourours.cito.formula;


import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meyourours.cito.R;
import com.rey.material.widget.EditText;
import com.rey.material.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HollidaySegarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HollidaySegarFragment extends FormulaFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editWeight;
    private TextView textFluid;
    private TextView textResult;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HollidaySegarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HollidaySegarFragment newInstance(String param1, String param2) {
        HollidaySegarFragment fragment = new HollidaySegarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HollidaySegarFragment() {
        // Required empty public constructor
        setmId(600);
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
        View rootView = inflater.inflate(R.layout.fragment_holliday_segar, container, false);

        editWeight = (EditText) rootView.findViewById(R.id.edit_weight);
        textFluid = (TextView) rootView.findViewById(R.id.text_fluid);
        textResult = (TextView) rootView.findViewById(R.id.text_result);

        editWeight.addTextChangedListener(weightWatcher);

        return rootView;
    }

    TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() != 0) {
                int volume = Formulas.getMaintenanceFluid(Integer.parseInt(s.toString()));
                textFluid.setText(volume + "ml");
                textResult.setText("Tetesan per menit: " + Formulas.getFluidDropRate(volume));
            }
        }
    };

}
