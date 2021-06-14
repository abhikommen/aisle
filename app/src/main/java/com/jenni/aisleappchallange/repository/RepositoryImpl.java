package com.jenni.aisleappchallange.repository;

import androidx.annotation.NonNull;

import com.jenni.aisleappchallange.data.model.ApiResponse;
import com.jenni.aisleappchallange.util.ApiResult;
import com.jenni.aisleappchallange.util.ServiceProvider;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements Repository {

    @Override
    public void sendPhoneNumber(HashMap<String, Object> body, ApiResult apiResult) {
        apiResult.onProgress();
        ServiceProvider.getApiService().sendPhoneNumber(body).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                apiResult.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                apiResult.onError(new Exception(t));
            }
        });
    }

    @Override
    public void verifyOTP(HashMap<String, Object> body, ApiResult apiResult) {
        apiResult.onProgress();
        ServiceProvider.getApiService().verifyOTP(body).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                apiResult.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                apiResult.onError(new Exception(t));

            }
        });
    }

    @Override
    public void fetchProfile(ApiResult apiResult) {
        apiResult.onProgress();
        ServiceProvider.getApiService().fetchProfile().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                ApiResponse apiResponse = new ApiResponse();
                try {
                    apiResponse.setToken(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                apiResult.onSuccess(apiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                apiResult.onError(new Exception(t));
            }
        });
    }
}
