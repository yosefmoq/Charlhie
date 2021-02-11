package com.yosefmoq.charlhie.ui.Auth;

import android.app.Application;
import android.widget.Toast;

import com.yosefmoq.charlhie.Base.BaseViewModel;
import com.yosefmoq.charlhie.listener.auth;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.repository.local.LocalSave;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthMV extends BaseViewModel<auth> {
    public AuthMV(Application application) {
        super(application);
    }

    public void login(String username, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().login(username, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseResponse -> {
            setIsLoading(false);
            if (baseResponse.getStatus().equalsIgnoreCase("error")) {
                return;
            }
            LocalSave.getInstance(getApplication()).saveCurrentUser(baseResponse);
            LocalSave.getInstance(getApplication()).setLoginAsGuest(true);
            ((auth) getNavigator()).goToPayment();

        }, throwable -> {

        }));
    }


    public void register(RigesterRequest rigesterRequest) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().register(rigesterRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(rigsterResponse -> {
            setIsLoading(false);
            if (rigsterResponse.getMessage().equalsIgnoreCase("error")) {
                Toast.makeText(getApplication(), rigsterResponse.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getApplication(), rigsterResponse.getMessage(), Toast.LENGTH_LONG).show();
            LocalSave.getInstance(getApplication()).saveCurrentUser(rigesterRequest);
            ((auth) getNavigator()).goToSignIn();
            LocalSave.getInstance(getApplication()).setLoginAsGuest(true);

        },throwable -> {

        }));
    }

}
