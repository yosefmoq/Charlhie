package com.yosefmoq.charlhie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RigesterRequest {

    @SerializedName("status")
    @Expose
    String status;

    public String getStatus() {
        return status;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getBoxnumber() {
        return boxnumber;
    }

    public void setBoxnumber(String boxnumber) {
        this.boxnumber = boxnumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @SerializedName("streetname")
    @Expose
    private String streetname;

    @SerializedName("housenumber")
    @Expose
    private String housenumber;

    @SerializedName("boxnumber")
    @Expose
    private String boxnumber;

    @SerializedName("box")
    @Expose
    private String box;

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @SerializedName("postcode")
    @Expose
    private String postcode;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("vat")
    @Expose
    private String vat;

    @SerializedName("company")
    @Expose
    private String company;

    @Override
    public String toString() {
        return "RigesterRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", streetname='" + streetname + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", boxnumber='" + boxnumber + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", vat='" + vat + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
