package com.jenni.aisleappchallange.repository;

import com.jenni.aisleappchallange.util.ApiResult;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.Body;

public interface Repository {

    void sendPhoneNumber(
            HashMap<String, Object> body,
            ApiResult apiResult
    );

    void verifyOTP(
            HashMap<String, Object> body,
            ApiResult apiResult
    );

    void fetchProfile(
            ApiResult apiResult
    );

}
