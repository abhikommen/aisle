package com.jenni.aisleappchallange.data;

import com.jenni.aisleappchallange.data.model.ApiResponse;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    @POST("users/phone_number_login"    )
    Call<ApiResponse> sendPhoneNumber(
            @Body HashMap<String, Object> body
    );


    @POST("users/verify_otp")
    Call<ApiResponse> verifyOTP(
            @Body HashMap<String, Object> body
    );


    @GET("users/test_profile_list")
    Call<ResponseBody> fetchProfile();

}
