package com.meyourours.cito;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.meyourours.cito.adapter.EBMAdapter;
import com.meyourours.cito.formula.Formulas;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.Switch;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EBMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EBMFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText editSensitivity, editSpecivicity, editPretest;
    private Switch testResult;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EBMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EBMFragment newInstance(String param1, String param2) {
        EBMFragment fragment = new EBMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EBMFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ebm, container, false);
//        View listHeader = inflater.inflate(R.layout.layout_list_ebm_header, null);
//        View listFooter = inflater.inflate(R.layout.layout_list_ebm_footer, null);
//
        Spinner spinnerDisease = (Spinner) rootView.findViewById(R.id.spinner_disease);
        ArrayAdapter<String> adapterDisease = new ArrayAdapter<>(getActivity(), R.layout.row_spn, new String[]{"Test Disease"});
        adapterDisease.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerDisease.setAdapter(adapterDisease);

        Spinner spinnerTest = (Spinner) rootView.findViewById(R.id.spinner_test);
        ArrayAdapter<String> adapterTest = new ArrayAdapter<>(getActivity(), R.layout.row_spn, new String[]{"Test Test"});
        adapterTest.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerTest.setAdapter(adapterTest);

        editSensitivity = (EditText) rootView.findViewById(R.id.edit_sensitivity);
        editSpecivicity = (EditText) rootView.findViewById(R.id.edit_specitivity);
        editPretest = (EditText) rootView.findViewById(R.id.edit_pretest);
        testResult = (Switch) rootView.findViewById(R.id.switches_test_result);

        spinnerTest.setOnItemClickListener(new Spinner.OnItemClickListener() {
            @Override
            public boolean onItemClick(Spinner parent, View view, int position, long id) {
                editSensitivity.setEnabled(true);
                editSpecivicity.setEnabled(true);
                return false;
            }
        });
//
//        ListView ebmList = (ListView) rootView.findViewById(R.id.list_ebm);
//        ebmList.addHeaderView(listHeader);
//        ebmList.addFooterView(listFooter);
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Test Disease");
//        final EBMAdapter adapter = new EBMAdapter(getActivity(), list);
//        ebmList.setAdapter(adapter);
//        Button buttonAdd = (Button) listFooter.findViewById(R.id.button_ebm_add);
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter.add("Test 2");
//                adapter.notifyDataSetChanged();
//            }
//        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_ebm, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_calculate:
                calculateEBM();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void calculateEBM() {
        if(!editSensitivity.getText().toString().equals("") && !editSpecivicity.getText().toString().equals("") && !editPretest.getText().toString().equals("")) {
            float pretest = Float.parseFloat(editPretest.getText().toString()) / 100.00f;
            float sensitivity = Float.parseFloat(editSensitivity.getText().toString()) / 100.00f;
            float specivicity = Float.parseFloat(editSpecivicity.getText().toString()) / 100.00f;
            float result = Formulas.getPostTestProb(pretest, sensitivity, specivicity, testResult.isChecked());
            Toast.makeText(getActivity(), "Result is: " + result * 100.00f + "%", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Harap masukan pre test, sensitivas dan spesivisitas", Toast.LENGTH_SHORT).show();
        }

    }
}
