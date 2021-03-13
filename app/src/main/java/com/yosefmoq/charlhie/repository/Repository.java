package com.yosefmoq.charlhie.repository;

import android.content.Context;

import com.yosefmoq.charlhie.models.BanContactRequest;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.models.RigsterResponse;
import com.yosefmoq.charlhie.repository.network.Rest.ApiClient;
import com.yosefmoq.charlhie.repository.network.Rest.ApiInterface;

import java.util.ArrayList;

import io.reactivex.Observable;

public class Repository {
    private static final String TAG = "Repository";
    private static ApiInterface apiClient;
    private static Repository instance;
    Context mContext;

    public Repository(Context context) {
        this.mContext = context;
        apiClient = ApiClient.getApiClient(context);
    }

    public static synchronized Repository getInstance(Context context) {
        Repository repository;
        synchronized (Repository.class) {
            if (instance == null) {
                instance = new Repository(context);
            }
            repository = instance;
        }
        return repository;
    }

    public Observable<ArrayList<Category>> getCategories() {
        return apiClient.getCategories();
    }

    public Observable<RigesterRequest> login(String username, String password) {
        return apiClient.login(username, password);
    }

    public Observable<RigsterResponse> register(RigesterRequest rigesterRequest) {
        return apiClient.register(rigesterRequest.getEmail(), rigesterRequest.getFirstname(), rigesterRequest.getPassword(), rigesterRequest.getLastname(), rigesterRequest.getStreetname(), rigesterRequest.getBoxnumber(), rigesterRequest.getHousenumber(), rigesterRequest.getPostcode(), rigesterRequest.getCity(), rigesterRequest.getCountry(), rigesterRequest.getVat(), rigesterRequest.getVat());
    }

    public Observable<String> banContact(BanContactRequest banContactRequest) {
        return apiClient.banRes(banContactRequest.getSource(), banContactRequest.getAmount());
    }

    public Observable<String> sendEmail(String email, String data) {
        return apiClient.sendEmail(email, data);
    }
}
