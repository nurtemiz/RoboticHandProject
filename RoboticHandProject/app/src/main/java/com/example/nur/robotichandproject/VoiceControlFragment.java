package com.example.nur.robotichandproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VoiceControlFragment extends Fragment {

    public VoiceControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVoiceControl = inflater.inflate(R.layout.fragment_voice_control, container, false);
        return viewVoiceControl;
    }

}
