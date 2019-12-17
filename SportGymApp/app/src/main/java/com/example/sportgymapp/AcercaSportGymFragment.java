package com.example.sportgymapp.DataBase;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportgymapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcercaSportGymFragment extends Fragment {


    public AcercaSportGymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_sport_gym, container, false);
    }

}
