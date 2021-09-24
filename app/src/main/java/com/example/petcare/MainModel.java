package com.example.petcare;

public class MainModel {


    String address,gender,name,purl;

    MainModel()
    {

    }
    public MainModel(String address, String gender, String name, String purl) {
        this.address = address;
        this.gender = gender;
        this.name = name;
        this.purl = purl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
