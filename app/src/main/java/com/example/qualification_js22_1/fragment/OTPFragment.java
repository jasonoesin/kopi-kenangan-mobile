package com.example.qualification_js22_1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.activity.PromptActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class OTPFragment extends Fragment {

    EditText otpEt;
    TextView submitTv;
    String otp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_o_t_p, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        otpEt = getView().findViewById(R.id.editOTP);
        submitTv = getView().findViewById(R.id.submitTv);
        otp = new DecimalFormat("0000").format(new Random().nextInt(9999));


        ((PromptActivity)getActivity()).sendSms(otp);



        submitTv.setOnClickListener(view1 -> {
            if(otpEt.getText().toString().equals(otp.toString()))
            {
                ((PromptActivity)getActivity()).insert();

                getActivity().finish();

                Toast.makeText(getActivity(), "Successfully Bought ! ", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Wrong OTP Code ! " +otp,Toast.LENGTH_SHORT).show();
            }
        });
    }
}