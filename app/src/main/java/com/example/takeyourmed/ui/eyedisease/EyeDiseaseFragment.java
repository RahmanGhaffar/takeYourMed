package com.example.takeyourmed.ui.eyedisease;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeyourmed.R;

public class EyeDiseaseFragment extends Fragment {

    private EyeDiseaseViewModel mViewModel;

    public static EyeDiseaseFragment newInstance() {
        return new EyeDiseaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.eye_disease_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EyeDiseaseViewModel.class);
        // TODO: Use the ViewModel
    }

}