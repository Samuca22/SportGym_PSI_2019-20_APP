package com.example.sportgym.vistas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sportgym.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcercaSportGymFragment extends Fragment {
    TextView tv_title_appBar;

    public AcercaSportGymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        tv_title_appBar = toolbar.findViewById(R.id.tv_title_appBar);
        tv_title_appBar.setText("ACERCA DO SPORTGYM");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_sport_gym, container, false);
    }



}
