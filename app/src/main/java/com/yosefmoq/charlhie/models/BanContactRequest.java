package com.yosefmoq.charlhie.models;

public class BanContactRequest {
    private int amount;
    private String source;

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount2) {
        this.amount = amount2;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source2) {
        this.source = source2;
    }

    public String toString() {
        return "BanContactRequest{amount=" + this.amount + ", source='" + this.source + '\'' + '}';
    }
}
