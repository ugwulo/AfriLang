package com.github.ugwulo.afrilang.data;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;
    public final String TAG = getClass().getSimpleName();
    private ArrayList<String> mLanguages = new ArrayList<>();
    private List<String> mCountries = new ArrayList<>();
    private List<SchoolInfo> mSchools = new ArrayList<>();
    private List<StudentInfo> mStudents = new ArrayList<>();
    private ArrayList<String> mClasses = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null){
            ourInstance = new DataManager();
            ourInstance.initializeCountries();
            ourInstance.initializeLanguages();
            ourInstance.initializeClasses();
        }
        return ourInstance;
    }

    private void initializeCountries() {
        mCountries.add("Nigeria");
        mCountries.add("Ghana");
        mCountries.add("Cameroon");
        mCountries.add("Sirea Lone");
        mCountries.add("Mali");
    }

    private void initializeLanguages() {
        mLanguages.add("Hausa");
        mLanguages.add("Igbo");
        mLanguages.add("Yoruba");
        mLanguages.add("Kanuri");
        mLanguages.add("Fulani");
        mLanguages.add("Urhobo");
        mLanguages.add("Ukwa");
    }

    private void initializeClasses(){
        mClasses.add("Pre-Nusery");
        mClasses.add("Nursery One");
        mClasses.add("Nursery Two");
        mClasses.add("Nursery Three");
        mClasses.add("Primary One");
        mClasses.add("Primary Two");
        mClasses.add("Primary Three");
        mClasses.add("Primary Four");
        mClasses.add("Primary Five");
        mClasses.add("Primary Six");
        mClasses.add("Junior School One");
        mClasses.add("Junior School Two");
        mClasses.add("Junior School Three");
        mClasses.add("Senior School One");
        mClasses.add("Senior School Two");
        mClasses.add("Senior School Three");
        mClasses.add("Undergraduate");
    }

    private DataManager() {

    }

    public ArrayList<String> getClasses(){
        return mClasses;
    }

    public List<SchoolInfo> getSchools() {
        return mSchools;
    }

    public List<StudentInfo> getStudents() {
        return mStudents;
    }

    public ArrayList<String> getLanguages(){
        return mLanguages;
    }

    public List<String> getCountries(){
        return mCountries;
    }

    public SchoolInfo getSchool(String id){
        for(SchoolInfo school : mSchools){
            if(id.equals(school.getSchoolId()))
                return school;
        }
        return null;
    }

    public StudentInfo getStudent(String id){
        for(StudentInfo student : mStudents){
            if(id ==(student.getStudentId()))
                return student;
        }
        return null;
    }

    public int findStudent(StudentInfo student) {
        for(int index=0; index < mStudents.size(); index++){
            if(student.equals(mStudents.get(index))){
                return index;
            }
        }
        return -1;
    }
}
