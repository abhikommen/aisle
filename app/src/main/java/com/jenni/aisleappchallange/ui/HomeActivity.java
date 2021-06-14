package com.jenni.aisleappchallange.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.jenni.aisleappchallange.R;
import com.jenni.aisleappchallange.data.model.ApiResponse;
import com.jenni.aisleappchallange.util.ApiResult;

public class HomeActivity extends AppCompatActivity implements ApiResult {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeScreenViewModel viewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);
        viewModel.fetchProfile(this);
    }

    @Override
    public void onProgress() {

    }

    @Override
    public void onSuccess(ApiResponse response) {
        Log.d("RESPONSE", response.getToken());
    }

    @Override
    public void onError(Exception e) {
        Log.d("RESPONSE", e.getMessage());
    }
}
