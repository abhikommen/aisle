package com.jenni.aisleappchallange.ui.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.jenni.aisleappchallange.R;
import com.jenni.aisleappchallange.data.model.ApiResponse;
import com.jenni.aisleappchallange.ui.HomeActivity;
import com.jenni.aisleappchallange.ui.LoginViewModel;
import com.jenni.aisleappchallange.util.ApiResult;
import com.jenni.aisleappchallange.util.TokenUtil;

public class OtpScreen extends Fragment implements View.OnClickListener, ApiResult {

    private TextView phoneNumberText;
    private TextView continueBtn;
    private LoginViewModel viewModel;
    private EditText otpEt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.otp_screen, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String phoneNumber = OtpScreenArgs.fromBundle(getArguments()).getPhone();
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        otpEt = view.findViewById(R.id.otp_et);

        continueBtn = view.findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(this);

        phoneNumberText = view.findViewById(R.id.phone_number);
        phoneNumberText.setOnClickListener(this);
        phoneNumberText.setText("+" + phoneNumber);

    }

    @Override
    public void onClick(View v) {

        //using swtich incase to handle more click events in future..
        switch (v.getId()) {
            case R.id.phone_number: {
                NavHostFragment.findNavController(this).navigateUp();
                break;
            }
            case R.id.continue_btn: {
                viewModel.verifyOtp(phoneNumberText.getText().toString(), otpEt.getText().toString(), this);
                break;
            }
        }
    }

    @Override
    public void onProgress() {

    }

    @Override
    public void onSuccess(ApiResponse response) {
        TokenUtil.saveToken(response.getToken());
        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(Exception e) {
        Log.d("RESPONSE", "onError: " + e.getMessage());

    }
}
