package com.github.ugwulo.afrilang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.ugwulo.afrilang.viewmodels.SchoolViewModel;

public class SchoolTypeFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private String mTypeOfSchool;
    private FragmentManager mFragmentManager;
    private SchoolViewModel mViewModel;

    public SchoolTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this.getActivity()).get(SchoolViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "View Created");

        mFragmentManager = getParentFragmentManager();

        Button nuserySchool = view.findViewById(R.id.id_button_register_as_nursery);
        Button primarySchool = view.findViewById(R.id.id_button_register_as_primary);
        Button secondarySchool = view.findViewById(R.id.id_button_register_secondary_school);
        Button collegeSchool = view.findViewById(R.id.id_button_register_college);
        Button polytechnicSchool = view.findViewById(R.id.id_button_register_as_polytechnic);
        Button university = view.findViewById(R.id.id_button_register_as_univesity);

        nuserySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Nursery School";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });

        primarySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Primary School";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });

        secondarySchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Secondary School";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });

        collegeSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Tertiary Institution";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });

        polytechnicSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Tertiary Institution";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTypeOfSchool = "Tertiary Institution";
                mViewModel.setSchoolType(mTypeOfSchool);
                NavHostFragment.findNavController(SchoolTypeFragment.this)
                        .navigate(R.id.action_schoolTypeFragment_to_RegisterSchoolFragment);
            }
        });

    }

    private void sendSchoolTypeData(String typeOfSchool) {
        FragmentDataListener listener = (FragmentDataListener) getActivity();
        listener.schoolType(typeOfSchool);
    }

}