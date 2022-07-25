package com.example.qualification_js22_1.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.activity.PromptActivity;

public class PromptFragment extends Fragment {

    TextView phoneTv, submitTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prompt, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneTv = getView().findViewById(R.id.editTextPhone);
        submitTv = getView().findViewById(R.id.submitTv);
        OTPFragment otpFragment = new OTPFragment();

        submitTv.setOnClickListener(view1 -> {
            if(phoneTv.getText().toString().equals(""))
                return;

            ((PromptActivity)getActivity()).setPhoneNum(phoneTv.getText().toString());
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.OTPLayout, otpFragment).commit();
        });
    }
}