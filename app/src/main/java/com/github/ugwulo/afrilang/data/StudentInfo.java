package com.github.ugwulo.afrilang.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public final class StudentInfo implements Parcelable {

    private String mStudentId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mStudentClass;
    private String mStudentPhoneNumber;
    private String mCountry;
    private ArrayList<String> mLanguage = new ArrayList<>();

    public StudentInfo(String studentId, String firstName, String lastName){
        this.mStudentId = studentId;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }
    public StudentInfo(){};

    protected StudentInfo(Parcel source){
        mStudentId = source.readString();
        mFirstName = source.readString();
        mLastName = source.readString();
        mEmail = source.readString();
    }

    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {
        @Override
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        @Override
        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };

    @Override
    public String toString() {
        return "StudentInfo{" +
                "mStudentId='" + mStudentId + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                '}';
    }

    public String getStudentId() {
        return mStudentId;
    }


    public void setStudentId(String studentId) {
        this.mStudentId = studentId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getStudentClass() {
        return mStudentClass;
    }

    public void setStudentClass(String studentClass) {
        mStudentClass = studentClass;
    }

    public String getStudentPhoneNumber() {
        return mStudentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        mStudentPhoneNumber = studentPhoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mStudentId);
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
        parcel.writeString(mEmail);
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setLanguage(String language) {
        String[] selectedLanguages = language.split(",");
        for(int i=0; i< selectedLanguages.length; i++){
            mLanguage.add(selectedLanguages[i]);
        }
    }

    public ArrayList<String> getLanguage() {
        return mLanguage;
    }
}
