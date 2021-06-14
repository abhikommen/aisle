package com.jenni.aisleappchallange.ui;

import androidx.lifecycle.ViewModel;

import com.jenni.aisleappchallange.repository.Repository;
import com.jenni.aisleappchallange.repository.RepositoryImpl;
import com.jenni.aisleappchallange.util.ApiResult;

public class HomeScreenViewModel extends ViewModel {


    private final Repository repository;

    public HomeScreenViewModel() {
        repository = new RepositoryImpl();
    }


    public void fetchProfile(ApiResult apiResult) {
        repository.fetchProfile(apiResult);
    }


}
