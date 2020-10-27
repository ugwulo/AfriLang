package com.github.ugwulo.afrilang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.github.ugwulo.afrilang.data.DataManager;
import com.github.ugwulo.afrilang.data.SchoolInfo;
import com.github.ugwulo.afrilang.data.StudentInfo;
import com.github.ugwulo.afrilang.utils.MultiSelectSpinner;
import com.github.ugwulo.afrilang.viewmodels.SchoolViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddStudentFragment extends Fragment implements MultiSelectSpinner.MultiSpinnerListener {

    private EditText mStudent_first_name;
    private EditText mStudent_last_name;
    private EditText mStudent_email;
    private EditText mStudent_phone_number;
    private MultiSelectSpinner mSpinner_Student_language;
    private Spinner mSpinner_Student_class;
    private SchoolViewModel mViewModel;
    private EditText mStudent_id_number;
    private TextView mNumber_of_students;
    private boolean mAddStudentSuccess;
    private Context mContext;
    private SchoolInfo mSchool;

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
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getContext();
        mNumber_of_students = view.findViewById(R.id.text_view_number_of_students);
        mViewModel.getSchool().observe(getViewLifecycleOwner(), schoolInfo -> {
            int numStudents = mViewModel.getSchool().getValue().getStudents().size();
            mNumber_of_students.setText(String.valueOf(numStudents));
        });

        mStudent_first_name = view.findViewById(R.id.edit_text_student_first_name);
        mStudent_last_name = view.findViewById(R.id.edit_text_student_last_name);
        mStudent_email = view.findViewById(R.id.edit_text_student_email);
        mStudent_phone_number = view.findViewById(R.id.edit_text_student_phone_number);
        mStudent_id_number = view.findViewById(R.id.edit_text_student_id_number);
        mSpinner_Student_language = view.findViewById(R.id.spinner_select_student_language);
        mSpinner_Student_class = view.findViewById(R.id.spinner_select_class);

        mSchool = mViewModel.getSchool().getValue();
        ArrayList<String> languages = mSchool.getLanguages();
        ArrayList<String> classes = DataManager.getInstance().getClasses();

        mSpinner_Student_language.setItems(languages, getString(R.string.for_all_languages), this);

        ArrayAdapter mAdapterClasses = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, classes);
        mAdapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_Student_class.setAdapter(mAdapterClasses);

        view.findViewById(R.id.button_add_student_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudentInfo();
                if(mAddStudentSuccess) {
                    clearInputs();
                }
            }
        });
        view.findViewById(R.id.button_add_student_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "SAVED. GOTO SCHOOL LANDING PAGE", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void clearInputs() {
        mStudent_first_name.setText("");
        mStudent_last_name.setText("");
        mStudent_email.setText("");
        mStudent_phone_number.setText("");
        mStudent_id_number.setText("");
        mSpinner_Student_language.setSelection(0);
        mSpinner_Student_class.setSelection(0);
        mStudent_id_number.requestFocus();
    }

    private void saveStudentInfo() {
        StudentInfo student = new StudentInfo();
        String firstName = mStudent_first_name.getText().toString().trim();
        String lastName = mStudent_last_name.getText().toString().trim();
        String studentEmail = mStudent_email.getText().toString().trim();
        String phoneNumber = mStudent_phone_number.getText().toString().trim();
        String studentId = mStudent_id_number.getText().toString().trim();
        String studentPreferredLanguage = String.valueOf(mSpinner_Student_language.getSelectedItem());
        String studentClass = String.valueOf(mSpinner_Student_class.getSelectedItem());

        boolean isValid = checkInputData(firstName,lastName,studentEmail,
                studentClass,studentId);
        if(isValid) {

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(studentEmail);
            student.setStudentClass(studentClass);
            student.setStudentPhoneNumber(phoneNumber);
            student.setLanguage(studentPreferredLanguage);
            if(mViewModel.addStudent(student)){
                mViewModel.getSchool().observe(this, schoolInfo -> {
                    int numStudents = mViewModel.getSchool().getValue().getStudents().size();
                    mNumber_of_students.setText(String.valueOf(numStudents));
                });
                mAddStudentSuccess = true;
            }

        }

    }

    private boolean checkInputData(String firstName, String lastName, String studentEmail, String studentClass, String studentId) {
        if(firstName.isEmpty() || lastName.isEmpty() || studentId.isEmpty()){
            return false;
        }
        if(studentClass.isEmpty() || studentEmail.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

    }
}