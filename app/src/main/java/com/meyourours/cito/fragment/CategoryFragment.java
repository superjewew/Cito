package com.meyourours.cito.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meyourours.cito.R;
import com.meyourours.cito.activity.FormulasActivity;
import com.meyourours.cito.adapter.CategoryAdapter;
import com.meyourours.cito.formula.Formulas;



/**
 * Fragment for displaying all formula's category
 * User can select a category to view formulas related to category
 */
public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String[] TITLES = {"Anak", "Geriatri", "Pulmonologi", "Umum"};
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_CATEGORY_ID = "categoryId";

    private View containerView;
    private Bitmap bitmap;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(int resId) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_formula_list, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_formula);
        CategoryAdapter adapter = new CategoryAdapter(getActivity(), Formulas.CATEGORY);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FormulasActivity.class);
        intent.putExtra(EXTRA_CATEGORY, position);
        Log.d("CATEGORY_ACTIVITY", "item position: " + position + ", id: " + id);
        startActivity(intent);
    }
}
