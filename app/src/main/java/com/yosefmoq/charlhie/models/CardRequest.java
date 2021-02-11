package com.yosefmoq.charlhie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardRequest {
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("token")
    @Expose
    private String token;

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount2) {
        this.amount = amount2;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }
}
