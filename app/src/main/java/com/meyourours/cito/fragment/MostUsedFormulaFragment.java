package com.meyourours.cito.fragment;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.meyourours.cito.R;
import com.meyourours.cito.contentprovider.FormulaContentProvider;
import com.meyourours.cito.database.FormulaTable;
import com.meyourours.cito.formula.Formulas;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostUsedFormulaFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.list_most_used)
    ListView listMostUsed;

    private View containerView;
    private Bitmap bitmap;
    private SimpleCursorAdapter adapter;

    public MostUsedFormulaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_most_used_formula, container, false);
        ButterKnife.bind(this, rootView);

        fillData();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    private void fillData() {
        String[] from = new String[] {FormulaTable.COLUMN_NAME};
        int[] to = new int[] {R.id.label};
        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.layout_list_recent, null, from, to, 0);
        listMostUsed.setAdapter(adapter);
        listMostUsed.setOnItemClickListener(this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {FormulaTable.COLUMN_ID, FormulaTable.COLUMN_NAME};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), FormulaContentProvider.CONTENT_URI, projection, null, null, FormulaTable.COLUMN_USE_COUNT + " DESC");
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        String formulaName = cursor.getString(cursor.getColumnIndex(FormulaTable.COLUMN_NAME));
        Formulas.startCountActivity(getActivity(), formulaName);
    }
}
