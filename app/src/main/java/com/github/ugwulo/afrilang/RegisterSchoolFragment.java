package com.github.ugwulo.afrilang;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.github.ugwulo.afrilang.utils.MultiSelectSpinner;
import com.github.ugwulo.afrilang.utils.ReadCSV;
import com.github.ugwulo.afrilang.viewmodels.SchoolViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RegisterSchoolFragment extends Fragment implements MultiSelectSpinner.MultiSpinnerListener {

    private EditText mSchoolName;
    private EditText mSchoolAddress;
    private EditText mSchoolEmail;
    private EditText mSchoolTelPhone;
    private Spinner mSpinnerCountry;
    private MultiSelectSpinner mSpinnerLanguage;

    private TextView mDescription;
    private Context mContext;
    private SchoolInfo mSchool;
    private SchoolViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this.getActivity()).get(SchoolViewModel.class);
        mViewModel.getSchoolType().observe(this, schoolType -> {
            mDescription.setText(getString(R.string.register_school_description) +" "+ schoolType);
        });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_school, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDescription = view.findViewById(R.id.text_view_register_school_description);
        mSchoolName = view.findViewById(R.id.edit_text_school_name);
        mSchoolAddress = view.findViewById(R.id.edit_text_school_address);
        mSchoolEmail = view.findViewById(R.id.edit_text_school_email);
        mSchoolTelPhone = view.findViewById(R.id.edit_text_school_phone_number);
        mSpinnerCountry = view.findViewById(R.id.spinner_select_school_country);
        mSpinnerLanguage = view.findViewById(R.id.spinner_select_language);

        List<String> countries = getListOfCountries();
        ArrayList<String> languages = new ArrayList<>();
        mContext = getContext();
        populateSpinnerCountries(countries);
        mSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] selectedCountry = mSpinnerCountry.getSelectedItem().toString().split(" ");
                String country = selectedCountry[0].toLowerCase();
                if (country.contains("-")){
                    country = country.replace("-", "_");
                }
                final int fileIdentifier = getResources().getIdentifier(country, "raw", mContext.getPackageName());
                languages.clear();
                try{
                    InputStream inputStream = getResources().openRawResource(fileIdentifier);
                    ReadCSV csvFile = new ReadCSV(inputStream);
                    List<String[]> extracted_Languages = csvFile.read();
                    for(int i=0; i<extracted_Languages.size(); i++){
                        languages.add(extracted_Languages.get(i)[0]);
                    }
                    mSpinnerLanguage.setItems(languages, getString(R.string.for_all_languages), RegisterSchoolFragment.this);
                }catch (Resources.NotFoundException e){
                    mSpinnerLanguage.setItems(languages, getString(R.string.language_not_available), RegisterSchoolFragment.this);
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.button_register_school_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSchoolData();
                if(mSchool != null){
                    mViewModel.createSchool(mSchool);
                    NavHostFragment.findNavController(RegisterSchoolFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        });

    }

    private List<String> getListOfCountries() {
        InputStream inputStream = getResources().openRawResource(R.raw.countries);
        ReadCSV csvFile = new ReadCSV(inputStream);
        List<String[]> extracted_countries = csvFile.read();
        ArrayList<String> countries = new ArrayList<>(extracted_countries.size());
        for(int i=0; i<extracted_countries.size(); i++){
            countries.add(extracted_countries.get(i)[0]);
        }
        return countries;
    }

    private void populateSpinnerCountries(List<String> countries) {
        ArrayAdapter mAdapterCountries = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, countries);
        mAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerCountry.setAdapter(mAdapterCountries);
    }

    private void createNewSchool(String schoolType) {
        String schoolId = generateUniqueSchoolId();
        mSchool = new SchoolInfo(schoolType, schoolId);
    }

    private String generateUniqueSchoolId() {
        return UUID.randomUUID().toString();
    }

    private void saveSchoolData(){
        String typeOfSchool = mViewModel.getSchoolType().getValue();
        String schoolName = mSchoolName.getText().toString().trim();
        String schoolAddress = mSchoolAddress.getText().toString().trim();
        String schoolEmail = mSchoolEmail.getText().toString().trim();
        String schoolPhoneNumber = mSchoolTelPhone.getText().toString().trim();
        String country = (String) mSpinnerCountry.getSelectedItem();
        ArrayList selectedLanguages = mSpinnerLanguage.getSelectedItemsArray();
        boolean isValid = checkInputs(schoolName,schoolAddress,schoolEmail,schoolPhoneNumber,country,selectedLanguages);

        if(isValid) {
            createNewSchool(typeOfSchool);
            mSchool.setSchoolName(schoolName);
            mSchool.setLanguages(selectedLanguages);
            mSchool.setSchoolAddress(schoolAddress);
            mSchool.setSchoolEmail(schoolEmail);
            mSchool.setCountry(country);
            mSchool.setSchoolPhone(schoolPhoneNumber);
        } else{
            mSchool = null;
        }
    }

    private boolean checkInputs(String schoolName,
                                String schoolAddress,
                                String schoolEmail,
                                String schoolPhoneNumber,
                                String country,
                                ArrayList selectedLanguages) {
        if(schoolName.isEmpty() || schoolAddress.isEmpty()){
            return false;
        }
        if(selectedLanguages.size() < 1){
            return false;
        }
        return true;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

    }
}