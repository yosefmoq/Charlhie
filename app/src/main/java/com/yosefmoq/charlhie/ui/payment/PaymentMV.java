  package com.yosefmoq.charlhie.ui.payment;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.yosefmoq.charlhie.Base.BaseViewModel;
import com.yosefmoq.charlhie.CardNavigation;
import com.yosefmoq.charlhie.models.BanContactRequest;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.repository.local.MyDatabase;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PaymentMV extends BaseViewModel<CardNavigation> {
    public MyDatabase myDatabase = new MyDatabase(getApplication());

    public PaymentMV(Application application) {
        super(application);
    }
    public MutableLiveData<String> baseResponseMutableLiveData = new MutableLiveData<>();
    public void banRequest(BanContactRequest banContactRequest) {
        getCompositeDisposable().add(getDataManager().banContact(banContactRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(banResponse -> {
            if (banResponse.equalsIgnoreCase("Your betaling has been received, Thank you!")) {
                getNavigator().SuccessBan();
            } else {
                getNavigator().ErrorBan(banResponse);
            }
            Toast.makeText(getApplication(), banResponse, Toast.LENGTH_LONG).show();

        }, throwable -> {
            Toast.makeText(getApplication(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.v("ttt", throwable.getLocalizedMessage());
            setErrorLoding(true, throwable.getMessage() + "");

        }));
    }

    public void sendEmail(String email, String data) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().sendEmail(email, data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            baseResponseMutableLiveData.postValue("done");
            setIsLoading(false);
        }, throwable -> {
            setIsLoading(false);

            baseResponseMutableLiveData.postValue("error");

            Log.v("ttt", throwable.getLocalizedMessage());
            Toast.makeText(getApplication(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.v("ttt", throwable.getLocalizedMessage());

        }));
    }


    public void deleteCart() {
        this.myDatabase.deleteAll();
    }

    public ArrayList<Category> getProduct() {
        return this.myDatabase.getProducts();
    }

    public double totalAmountToPay() {
        return this.myDatabase.getPrice();
    }
}
