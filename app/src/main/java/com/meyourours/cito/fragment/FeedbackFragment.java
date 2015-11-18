package com.meyourours.cito.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.meyourours.cito.GMailSender;
import com.meyourours.cito.R;
import com.rey.material.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText nameEditText;
    private EditText feedbackEditText;
    private String name;
    private MaterialDialog dialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FeedbackFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

        nameEditText = (EditText) rootView.findViewById(R.id.edit_name);
        feedbackEditText = (EditText) rootView.findViewById(R.id.edit_feedback);

        nameEditText.setError("Harus diisi");
        feedbackEditText.setError("Harus diisi");

        nameEditText.addTextChangedListener(errorNameWatcher);

        feedbackEditText.addTextChangedListener(errorFeedbackWatcher);

        dialog = new MaterialDialog.Builder(getActivity())
                .title("Mengirim Tanggapan")
                .content("Mohon tunggu sebentar")
                .progress(true, 0)
                .build();

        return rootView;
    }

    TextWatcher errorNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().equals("")) {
                nameEditText.setError("Harus diisi");
            } else {
                nameEditText.setError(null);
            }
        }
    };

    TextWatcher errorFeedbackWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().equals("")) {
                feedbackEditText.setError("Harus diisi");
            } else {
                feedbackEditText.setError(null);
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_feedback, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_send:
                name = nameEditText.getText().toString();
                String feedback = feedbackEditText.getText().toString();
                if(!name.equals("") && !feedback.equals("")) {
                    dialog.show();
                    SendEmail sendEmail = new SendEmail();
                    sendEmail.execute(feedback);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class SendEmail extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for(String body : params) {
                try {
                    Log.d("FEEDBACK", "Sending email");
                    GMailSender sender = new GMailSender("feedback.cito@gmail.com", "superqwater21");
                    sender.sendMail("Feedback for Cito",
                            name + " said,\n" + body,
                            "feedback.cito@gmail.com",
                            "norman.lie91@gmail.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Email Terkirim", Toast.LENGTH_SHORT).show();
            Log.d("FEEDBACK", "Email sent");
        }
    }
}
