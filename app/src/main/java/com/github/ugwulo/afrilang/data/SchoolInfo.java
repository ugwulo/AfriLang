package com.github.ugwulo.afrilang.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class SchoolInfo implements Parcelable {

    private String mSchoolId;
    private final String mSchoolType;
    private String mSchoolName;
    private List<StudentInfo> mStudents;
    private String mSchoolAddress;
    private String mSchoolEmail;
    private ArrayList<String> mLanguages = new ArrayList<>();
    private String mSchoolPhone;
    private String mCountry;

    public SchoolInfo(String schoolType) {
        mSchoolType= schoolType;
    }

    public SchoolInfo(String schoolId, String schoolType){
        this.mSchoolId = schoolId;
        this.mSchoolType = schoolType;
    }

    protected SchoolInfo(Parcel in) {
        mSchoolId = in.readString();
        mSchoolType = in.readString();
        mSchoolName = in.readString();
        mStudents = in.createTypedArrayList(StudentInfo.CREATOR);
    }

    public String getSchoolId() {
        return mSchoolId;
    }

    public String getSchoolType() {
        return mSchoolType;
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public void setSchoolId(String schoolId) {
        mSchoolId = schoolId;
    }

    public String getSchoolAddress() {
        return mSchoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        mSchoolAddress = schoolAddress;
    }

    public String getSchoolEmail() {
        return mSchoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        mSchoolEmail = schoolEmail;
    }

    public void setSchoolName(String schoolName) {
        mSchoolName = schoolName;
    }

    public String getSchoolPhone() {
        return mSchoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        mSchoolPhone = schoolPhone;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public List<StudentInfo> getStudents() {
        if(mStudents == null){
            mStudents = new ArrayList<>();
        }
        return mStudents;
    }

    public ArrayList<String> getLanguages() {
        return mLanguages;
    }

    public void addLanguage(String language) {
        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        mLanguages.add(language);
    }

    public void setLanguages(ArrayList languages){
        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        for(int index=0; index < languages.size(); index++){
            mLanguages.add(String.valueOf(languages.get(index)));
        }
    }

    public static final Creator<SchoolInfo> CREATOR = new Creator<SchoolInfo>() {
        @Override
        public SchoolInfo createFromParcel(Parcel in) {
            return new SchoolInfo(in);
        }

        @Override
        public SchoolInfo[] newArray(int size) {
            return new SchoolInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSchoolId);
        parcel.writeString(mSchoolName);
        parcel.writeTypedList(mStudents);
    }

    public void addStudent(StudentInfo student) {
        if(mStudents == null) {
            mStudents = new ArrayList();
        }
        mStudents.add(student);
    }
}
