package com.example.sportgym.vistas;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sportgym.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComoUsarAppFragment extends Fragment {
    TextView tv_title_appBar;

    public ComoUsarAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        tv_title_appBar = toolbar.findViewById(R.id.tv_title_appBar);
        tv_title_appBar.setText("COMO USAR A APP");

        return inflater.inflate(R.layout.fragment_como_usar_app, container, false);
    }

}
