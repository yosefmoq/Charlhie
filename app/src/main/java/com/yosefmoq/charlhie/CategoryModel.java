package com.yosefmoq.charlhie;

import android.util.ArraySet;

public class CategoryModel {
    String categoryName;
    ArraySet<String> subCategories;

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName2) {
        this.categoryName = categoryName2;
    }

    public ArraySet<String> getSubCategories() {
        return this.subCategories;
    }

    public void setSubCategories(ArraySet<String> subCategories2) {
        this.subCategories = subCategories2;
    }
}
