package com.yosefmoq.charlhie.repository.network.Rest;

import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.models.RigsterResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("myhack/processbancontactCharlhie.php")
    Observable<String> banRes(@Field("source") String str, @Field("amount") int i);

    @GET("be/Charlhiekaart.php")
    Observable<ArrayList<Category>> getCategories();

    @FormUrlEncoded
    @POST("CharlhieFiles/userLoginCharlhie.php")
    Observable<RigesterRequest> login(@Field("email") String str, @Field("password") String str2);

    @FormUrlEncoded
    @POST("CharlhieFiles/userRegisterCharlhie.php")
    Observable<RigsterResponse> register(@Field("email") String str, @Field("firstname") String str2, @Field("password") String str3, @Field("lastname") String str4, @Field("streetname") String str5, @Field("boxnumber") String str6, @Field("housenumber") String str7, @Field("postcode") String str8, @Field("city") String str9, @Field("country") String str10, @Field("vat") String str11, @Field("company") String str12);

    @FormUrlEncoded
    @POST("be/fileCharlhieOrder.php")
    Observable<String> sendEmail(@Field("email") String str, @Field("data") String str2);
}
