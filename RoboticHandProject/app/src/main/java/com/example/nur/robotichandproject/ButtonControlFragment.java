package com.example.nur.robotichandproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ButtonControlFragment extends Fragment {


    public ButtonControlFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewButtonControl = inflater.inflate(R.layout.fragment_button_control, container, false);

        return viewButtonControl;
    }

}

