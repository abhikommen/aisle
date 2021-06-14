package com.jenni.aisleappchallange.util;

import android.util.Log;

import com.jenni.aisleappchallange.data.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (TokenUtil.fetchToken() == null) {
            Request newRequest = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
                    .build();
            return chain.proceed(newRequest);
        } else {
            String token = TokenUtil.fetchToken();
            Request newRequest = originalRequest.newBuilder()
                    .header("Authorization", token)
                    .header("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
                    .build();

            return chain.proceed(newRequest);
        }

    }
}

public class ServiceProvider {


    private static ApiService apiService = null;

    public static ApiService getApiService() {
        if (apiService == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new NetworkInterceptor())
                    .build();

            apiService = new Retrofit.Builder()
                    .baseUrl("https://testa2.aisle.co/V1/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService.class);
        }
        return apiService;
    }
}
