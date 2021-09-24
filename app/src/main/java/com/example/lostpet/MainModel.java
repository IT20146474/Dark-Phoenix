package com.example.lostpet;

public class MainModel {

    String  name,gender,breed,lost,purl,oname,phone,address,description;

    MainModel()
    {

    }
    public MainModel(String name, String gender, String breed,String lost, String purl,String oname, String phone, String address, String description) {
        this.name = name;
        this.gender = gender;
        this.breed = breed;
        this.lost = lost;
        this.purl = purl;
        this.oname = oname;
        this.phone = phone;
        this.address = address;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
    public String getOname() {
        return oname;
    }

    public void getOname(String oname) {
        this.oname = oname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void getAddress(String address) {this.address = address;}

    public String getDescription() {
        return description;
    }

    public void getDescription(String description) {
        this.description = description;
    }

}
