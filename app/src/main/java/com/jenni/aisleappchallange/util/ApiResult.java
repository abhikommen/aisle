package com.jenni.aisleappchallange.util;

import com.jenni.aisleappchallange.data.model.ApiResponse;

import okhttp3.ResponseBody;

public interface ApiResult {
    void onProgress();

    void onSuccess(ApiResponse response);

    void onError(Exception e);
}

