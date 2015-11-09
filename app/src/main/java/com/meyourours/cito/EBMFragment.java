package com.meyourours.cito;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.meyourours.cito.adapter.EBMAdapter;
import com.rey.material.widget.Spinner;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ebm, container, false);
        View listHeader = inflater.inflate(R.layout.layout_list_ebm_header, null);
        View listFooter = inflater.inflate(R.layout.layout_list_ebm_footer, null);

        Spinner spinnerDisease = (Spinner) listHeader.findViewById(R.id.spinner_disease);
        ArrayAdapter<String> adapterDisease = new ArrayAdapter<>(getActivity(), R.layout.row_spn, new String[]{"Test Disease"});
        adapterDisease.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerDisease.setAdapter(adapterDisease);

        ListView ebmList = (ListView) rootView.findViewById(R.id.list_ebm);
        ebmList.addHeaderView(listHeader);
        ebmList.addFooterView(listFooter);

        ArrayList<String> list = new ArrayList<>();
        list.add("Test Disease");
        final EBMAdapter adapter = new EBMAdapter(getActivity(), list);
        ebmList.setAdapter(adapter);

        Button buttonAdd = (Button) listFooter.findViewById(R.id.button_ebm_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("Test 2");
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }


}
