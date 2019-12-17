package com.example.sportgym;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sportgym.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Button btn_bem_estar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_bem_estar = view.findViewById(R.id.btn_bem_estar);

        btn_bem_estar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BemEstarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
