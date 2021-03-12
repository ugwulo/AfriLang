package com.github.ugwulo.afrilang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.ugwulo.afrilang.data.SchoolInfo;
import com.github.ugwulo.afrilang.data.StudentInfo;
import com.github.ugwulo.afrilang.viewmodels.SchoolViewModel;

import java.util.ArrayList;
import java.util.List;

public class ConfirmSchoolRegistrationFragment extends Fragment {

    private TextView mSchool_name;
    private TextView mSchool_address;
    private TextView mSchool_email;
    private TextView mSchool_phone_number;
    private TextView mSchool_languages;
    private SchoolViewModel mViewModel;
    private TextView mNumber_of_students;
    private boolean mAddStudentSuccess;
    private Context mContext;
    private SchoolInfo mSchool;
    private TextView mSchool_country;
    private List<String> mLanguages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this.getActivity()).get(SchoolViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_school_registration, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getContext();
        mNumber_of_students = view.findViewById(R.id.text_view_number_of_students);
        mViewModel.getSchool().observe(getViewLifecycleOwner(), schoolInfo -> {
            int numStudents = mViewModel.getSchool().getValue().getStudents().size();
            mNumber_of_students.setText(String.valueOf(numStudents));
        });

        mSchool_name = view.findViewById(R.id.textview_submitted_school_name);
        mSchool_address = view.findViewById(R.id.textview_submitted_school_address);
        mSchool_email = view.findViewById(R.id.textview_submitted_school_email);
        mSchool_phone_number = view.findViewById(R.id.textview_submitted_school_phone_number);
        mSchool_languages = view.findViewById(R.id.textview_submitted_school_languages);
        mSchool_country = (TextView) view.findViewById(R.id.textview_submitted_school_country);

        mSchool = mViewModel.getSchool().getValue();

        populateSubmissionTextViews();

        view.findViewById(R.id.button_confirm_school_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save data to database and open landing page
                Intent intent = new Intent(getActivity(), SchoolLandingPageActivity.class);
                startActivity(intent);
            }
        });

    }

    private void populateSubmissionTextViews() {
        List<String> mLanguages = mSchool.getLanguages();
        mSchool_name.setText(mSchool.getSchoolName());
        mSchool_address.setText(mSchool.getSchoolAddress());
        mSchool_email.setText(mSchool.getSchoolEmail());
        mSchool_phone_number.setText(mSchool.getSchoolPhone());
        mSchool_country.setText(mSchool.getCountry());
        displayLanguageList();
        mSchool_languages.setText(mLanguages.toString());
    }

    private void displayLanguageList() {
        ListView languageList = getView().findViewById(R.id.listview_selected_languages);
        ListAdapter langAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, mSchool.getLanguages());
        languageList.setAdapter(langAdapter);
    }

}