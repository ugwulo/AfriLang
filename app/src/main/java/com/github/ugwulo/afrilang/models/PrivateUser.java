package com.github.ugwulo.afrilang.models;

public class PrivateUser {

    private String firstName;
    private String lastName;
    private String telephone;
    private String country;
    private String language;
    private String user_id;


    @Override
    public String toString() {
        return "PrivateUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public PrivateUser(String firstName, String lastName, String telephone, String country, String language, String user_id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.country = country;
        this.language = language;
        this.user_id = user_id;
    }

    public PrivateUser(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
