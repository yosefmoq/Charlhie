package com.yosefmoq.charlhie.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yosefmoq.charlhie.Base.BaseViewModel;
import com.yosefmoq.charlhie.models.Category;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    public MutableLiveData<ArrayList<Category>> categoryMutableLiveData = new MutableLiveData<>();

    public HomeViewModel(Application application) {
        super(application);
    }

    public void getCategories() {
        getCompositeDisposable().add(getDataManager().getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> {
            this.categoryMutableLiveData.postValue(categories);
            Log.v("ttt", categories.toString());

        }, throwable -> {
            Log.v("ttt", "error");
            Log.v("ttt", throwable.getLocalizedMessage());

        }));
    }

}
