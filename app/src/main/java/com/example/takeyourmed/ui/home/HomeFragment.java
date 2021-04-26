package com.example.takeyourmed.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.takeyourmed.R;
import com.example.takeyourmed.ui.camera.CaptureCameraActivity;
import com.example.takeyourmed.ui.doctor.DoctorActivity;
import com.example.takeyourmed.ui.eyedisease.EyeDiseaseActivity;
import com.example.takeyourmed.ui.reminder.ReminderActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        GridLayout mainGrid = (GridLayout)root.findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
//        setToggleEvents(mainGrid);

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void setToggleEvents(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(getActivity().getApplicationContext(), "true", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(getActivity().getApplicationContext(), "False", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( finalI == 0){
                        Intent ReminderIntent = new Intent(getActivity().getApplicationContext(), ReminderActivity.class);
                        startActivity(ReminderIntent);
                    }
                    else if (finalI == 1) {
                        Intent DoctorIntent = new Intent(getActivity().getApplicationContext(), DoctorActivity.class);
                        startActivity(DoctorIntent);
                    }
                    else if (finalI == 2) {
                        Intent EyeDiseaseIntent = new Intent(getActivity().getApplicationContext(), EyeDiseaseActivity.class);
                        startActivity(EyeDiseaseIntent);
                    }
                    else if (finalI == 3) {
                        Intent cameraIntent = new Intent(getActivity().getApplicationContext(), CaptureCameraActivity.class);
                        startActivity(cameraIntent);
                    }
                    else if (finalI == 4) {
                        Toast.makeText(getActivity().getApplicationContext(), "Share Picture Action", Toast.LENGTH_SHORT).show();
                    }
                    else if (finalI == 5) {
                        Toast.makeText(getActivity().getApplicationContext(), "Share Log Action", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}