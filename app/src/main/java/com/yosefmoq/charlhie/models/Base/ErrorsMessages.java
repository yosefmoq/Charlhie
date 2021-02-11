package com.yosefmoq.charlhie.models.Base;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ErrorsMessages implements Parcelable {
    public static final Creator<ErrorsMessages> CREATOR = new Creator<ErrorsMessages>() {
        /* class com.yosefmoq.charlhie.models.Base.ErrorsMessages.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ErrorsMessages createFromParcel(Parcel source) {
            return new ErrorsMessages(source);
        }

        @Override // android.os.Parcelable.Creator
        public ErrorsMessages[] newArray(int size) {
            return new ErrorsMessages[size];
        }
    };
    @SerializedName("country_code")
    @Expose
    private ArrayList<String> country_code;
    @SerializedName(NotificationCompat.CATEGORY_EMAIL)
    @Expose
    private ArrayList<String> email;
    @SerializedName("equipmentIds")
    @Expose
    private ArrayList<String> equipmentIds;
    @SerializedName("fuel_type_id")
    @Expose
    private ArrayList<String> fuel_type_id;
    @SerializedName("images")
    @Expose
    private ArrayList<String> images;
    @SerializedName("interiorIds")
    @Expose
    private ArrayList<String> interiorIds;
    @SerializedName("location")
    @Expose
    private ArrayList<String> location;
    @SerializedName("mileage_number")
    @Expose
    private ArrayList<String> mileage_number;
    @SerializedName("phone_number")
    @Expose
    private ArrayList<String> mobile;
    @SerializedName("name")
    @Expose
    private ArrayList<String> name;
    @SerializedName("password")
    @Expose
    private ArrayList<String> password;
    @SerializedName("pluginIds")
    @Expose
    private ArrayList<String> pluginIds;
    @SerializedName("price")
    @Expose
    private ArrayList<String> price;
    @SerializedName("specificationIds")
    @Expose
    private ArrayList<String> specificationIds;
    @SerializedName("username")
    @Expose
    private ArrayList<String> username;

    public ErrorsMessages() {
    }

    public static Creator<ErrorsMessages> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<String> getFuel_type_id() {
        return this.fuel_type_id;
    }

    public void setFuel_type_id(ArrayList<String> fuel_type_id2) {
        this.fuel_type_id = fuel_type_id2;
    }

    public ArrayList<String> getName() {
        return this.name;
    }

    public void setName(ArrayList<String> name2) {
        this.name = name2;
    }

    public ArrayList<String> getEmail() {
        return this.email;
    }

    public void setEmail(ArrayList<String> email2) {
        this.email = email2;
    }

    public ArrayList<String> getMobile() {
        return this.mobile;
    }

    public void setMobile(ArrayList<String> mobile2) {
        this.mobile = mobile2;
    }

    public ArrayList<String> getInteriorIds() {
        return this.interiorIds;
    }

    public void setInteriorIds(ArrayList<String> interiorIds2) {
        this.interiorIds = interiorIds2;
    }

    public ArrayList<String> getCountry_code() {
        return this.country_code;
    }

    public void setCountry_code(ArrayList<String> country_code2) {
        this.country_code = country_code2;
    }

    public ArrayList<String> getPassword() {
        return this.password;
    }

    public void setPassword(ArrayList<String> password2) {
        this.password = password2;
    }

    public ArrayList<String> getEquipmentIds() {
        return this.equipmentIds;
    }

    public void setEquipmentIds(ArrayList<String> equipmentIds2) {
        this.equipmentIds = equipmentIds2;
    }

    public ArrayList<String> getUsername() {
        return this.username;
    }

    public void setUsername(ArrayList<String> username2) {
        this.username = username2;
    }

    public ArrayList<String> getSpecificationIds() {
        return this.specificationIds;
    }

    public void setSpecificationIds(ArrayList<String> specificationIds2) {
        this.specificationIds = specificationIds2;
    }

    public ArrayList<String> getPluginIds() {
        return this.pluginIds;
    }

    public void setPluginIds(ArrayList<String> pluginIds2) {
        this.pluginIds = pluginIds2;
    }

    public String toString() {
        return "ErrorsMessages{name=" + this.name + ", email=" + this.email + ", mobile=" + this.mobile + ", country_code=" + this.country_code + ", password=" + this.password + '}';
    }

    public ArrayList<String> getPrice() {
        return this.price;
    }

    public void setPrice(ArrayList<String> price2) {
        this.price = price2;
    }

    public ArrayList<String> getImages() {
        return this.images;
    }

    public void setImages(ArrayList<String> images2) {
        this.images = images2;
    }

    public ArrayList<String> getLocation() {
        return this.location;
    }

    public void setLocation(ArrayList<String> location2) {
        this.location = location2;
    }

    public ArrayList<String> getMileage_number() {
        return this.mileage_number;
    }

    public void setMileage_number(ArrayList<String> mileage_number2) {
        this.mileage_number = mileage_number2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.name);
        dest.writeStringList(this.email);
        dest.writeStringList(this.mobile);
        dest.writeStringList(this.country_code);
        dest.writeStringList(this.password);
        dest.writeStringList(this.interiorIds);
        dest.writeStringList(this.equipmentIds);
        dest.writeStringList(this.username);
        dest.writeStringList(this.fuel_type_id);
        dest.writeStringList(this.specificationIds);
        dest.writeStringList(this.pluginIds);
        dest.writeStringList(this.price);
        dest.writeStringList(this.images);
        dest.writeStringList(this.location);
        dest.writeStringList(this.mileage_number);
    }

    protected ErrorsMessages(Parcel in) {
        this.name = in.createStringArrayList();
        this.email = in.createStringArrayList();
        this.mobile = in.createStringArrayList();
        this.country_code = in.createStringArrayList();
        this.password = in.createStringArrayList();
        this.interiorIds = in.createStringArrayList();
        this.equipmentIds = in.createStringArrayList();
        this.username = in.createStringArrayList();
        this.fuel_type_id = in.createStringArrayList();
        this.specificationIds = in.createStringArrayList();
        this.pluginIds = in.createStringArrayList();
        this.price = in.createStringArrayList();
        this.images = in.createStringArrayList();
        this.location = in.createStringArrayList();
        this.mileage_number = in.createStringArrayList();
    }
}
