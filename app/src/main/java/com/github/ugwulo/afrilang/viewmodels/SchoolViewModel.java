package com.github.ugwulo.afrilang.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.ugwulo.afrilang.data.DataManager;
import com.github.ugwulo.afrilang.data.SchoolInfo;
import com.github.ugwulo.afrilang.data.StudentInfo;

import java.util.UUID;

public class SchoolViewModel extends ViewModel {

    private MutableLiveData<SchoolInfo> mSchool = new MutableLiveData<>();
    private final MutableLiveData<String> mSchoolType = new MutableLiveData<>();

    private DataManager dm = DataManager.getInstance();

    public void setSchoolType (String schoolType){
        mSchoolType.setValue(schoolType);
    }
    public MutableLiveData<String> getSchoolType(){
        return mSchoolType;
    }
    public void createSchool(SchoolInfo school){
        mSchool.setValue(school);
    }

    public MutableLiveData<SchoolInfo> getSchool(){
        if(mSchool == null){
            mSchool.setValue(createNewSchool());
        }
        return mSchool;
    }

    public SchoolInfo getSchoolDetails(String schoolId){
        if(mSchool == null){
            return null;
        }
        return mSchool.getValue();
    }
    private SchoolInfo createNewSchool() {
        String schoolId = UUID.randomUUID().toString();
        SchoolInfo school = new SchoolInfo(mSchoolType.getValue(), schoolId);
        return school;
    }
    public boolean addStudent(StudentInfo student){
        if(mSchool == null){
            return false;
        }
        getSchool().getValue().addStudent(student);
        return true;
    }
}
