package com.jenni.aisleappchallange.ui.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.jenni.aisleappchallange.R;
import com.jenni.aisleappchallange.data.model.ApiResponse;
import com.jenni.aisleappchallange.ui.LoginViewModel;
import com.jenni.aisleappchallange.util.ApiResult;

public class PhoneScreen extends Fragment implements View.OnClickListener, ApiResult {

    private TextView continueBtn;
    private LoginViewModel viewModel;
    private EditText countryCodeEt;
    private EditText phoneNumberEt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        phoneNumberEt = view.findViewById(R.id.phone_number_et);
        countryCodeEt = view.findViewById(R.id.otp_et);
        continueBtn = view.findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_btn:
                String completePhoneNumber = countryCodeEt.getText().toString() + phoneNumberEt.getText().toString();
                viewModel.sendOtp(completePhoneNumber, this);
                break;
        }
    }

    @Override
    public void onProgress() {

    }

    @Override
    public void onSuccess(ApiResponse response) {
        ApiResponse apiResponse = (ApiResponse) response;
        if (apiResponse.getStatus()) {
            //  move to otp screen..
            NavHostFragment.findNavController(this).navigate(PhoneScreenDirections.actionPhoneToOtp("919876543212"));
        } else {
            Toast.makeText(requireActivity(), "Something Went Wrong.. Try to  change your number to +919876543212", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Exception e) {
        Log.d("TAG", "onError: " + e.getMessage());
    }
}
