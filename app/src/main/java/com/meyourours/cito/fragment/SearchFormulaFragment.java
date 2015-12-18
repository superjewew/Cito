package com.meyourours.cito.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meyourours.cito.R;
import com.meyourours.cito.formula.Formulas;
import com.rey.material.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFormulaFragment extends TrackerFragment {

    EditText searchEdit;
    ListView resultList;
    private ArrayList<String> resultArray = new ArrayList<>();
    private ArrayAdapter<String> formulaAdapter;

    public SearchFormulaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_formula, container, false);

        searchEdit = (EditText) rootView.findViewById(R.id.edit_search);
        resultList = (ListView) rootView.findViewById(R.id.list_search_result);

        formulaAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, resultArray);
        resultList.setAdapter(formulaAdapter);
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String formulaName = formulaAdapter.getItem(position);
                Formulas.startCountActivity(getActivity(), formulaName);
            }
        });
        searchEdit.addTextChangedListener(searchWatcher);

        setTrackerName("SearchFormulaFragment");

        return rootView;
    }

    TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int textlength = searchEdit.getText().length();
            resultArray.clear();
            for(int resId : Formulas.FORMULA_LIST) {
                String[] listview_array = getActivity().getResources().getStringArray(resId);
                for (String listview_item : listview_array) {
                    if (textlength <= listview_item.length()) {
                        if (listview_item.toLowerCase().contains(searchEdit.getText().toString().toLowerCase())) {
                            resultArray.add(listview_item);
                        }
                    }
                }
            }
            formulaAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                    resultArray);
            resultList.setAdapter(formulaAdapter);
        }
    };

}
