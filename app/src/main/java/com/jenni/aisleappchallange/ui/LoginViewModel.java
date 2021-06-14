package com.jenni.aisleappchallange.ui;

import androidx.lifecycle.ViewModel;

import com.jenni.aisleappchallange.repository.Repository;
import com.jenni.aisleappchallange.repository.RepositoryImpl;
import com.jenni.aisleappchallange.util.ApiResult;

import java.util.HashMap;

public class LoginViewModel extends ViewModel {

    private final Repository repository;

    public LoginViewModel() {
        repository = new RepositoryImpl();
    }

    public void sendOtp(String number, ApiResult apiResult) {
        try {
            HashMap<String, Object> body = new HashMap<>();
            body.put("number", number);
            repository.sendPhoneNumber(body, apiResult);
        } catch (Exception exception) {
            apiResult.onError(exception);
        }
    }

    public void verifyOtp(String number, String otp, ApiResult apiResult) {
        try {
            HashMap<String, Object> body = new HashMap<>();
            body.put("number", number);
            body.put("otp", otp);
            repository.verifyOTP(body, apiResult);
        } catch (Exception exception) {
            apiResult.onError(exception);
        }
    }

    public void fetchProfile(ApiResult apiResult) {
        repository.fetchProfile(apiResult);
    }


}
