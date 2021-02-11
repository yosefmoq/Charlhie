package com.yosefmoq.charlhie.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        /* class com.yosefmoq.charlhie.models.Category.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override // android.os.Parcelable.Creator
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    @SerializedName("MT1")
    @Expose
    private String MT1;
    @SerializedName("MerkOmschr")
    @Expose
    private String category;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("cpnr")
    @Expose
    private int id;
    @SerializedName("Foto1")
    @Expose
    private String image;
    @SerializedName("computed")
    @Expose
    private String longDescription;
    @SerializedName("price4")
    @Expose
    private double nativePrice;
    @SerializedName("Verkoopprijs")
    @Expose
    private double price;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("CatOmschr")
    @Expose
    private String subCategory;

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = in.readInt();
        this.price = in.readDouble();
        this.description = in.readString();
        this.category = in.readString();
        this.subCategory = in.readString();
        this.MT1 = in.readString();
        this.longDescription = in.readString();
        this.image = in.readString();
        this.quantity = in.readInt();
        this.nativePrice = in.readDouble();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity2) {
        this.quantity = quantity2;
    }

    public double getNativePrice() {
        return this.nativePrice;
    }

    public void setNativePrice(double nativePrice2) {
        this.nativePrice = nativePrice2;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image2) {
        this.image = image2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price2) {
        this.price = price2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category2) {
        this.category = category2;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(String subCategory2) {
        this.subCategory = subCategory2;
    }

    public String getMT1() {
        return this.MT1;
    }

    public void setMT1(String MT12) {
        this.MT1 = MT12;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription2) {
        this.longDescription = longDescription2;
    }

    public String toString() {
        return "Category{id=" + this.id + ", price=" + this.price + ", description='" + this.description + '\'' + ", category='" + this.category + '\'' + ", subCategory='" + this.subCategory + '\'' + ", MT1='" + this.MT1 + '\'' + ", longDescription='" + this.longDescription + '\'' + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeDouble(this.price);
        parcel.writeString(this.description);
        parcel.writeString(this.category);
        parcel.writeString(this.subCategory);
        parcel.writeString(this.MT1);
        parcel.writeString(this.longDescription);
        parcel.writeString(this.image);
        parcel.writeInt(this.quantity);
        parcel.writeDouble(this.nativePrice);
    }
}
