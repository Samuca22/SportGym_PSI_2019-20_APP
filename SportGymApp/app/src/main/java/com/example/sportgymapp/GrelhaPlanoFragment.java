package com.example.sportgymapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrelhaPlanoFragment extends Fragment {
    private GridView gridView;



    public GrelhaPlanoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grelha_plano, container, false);


        gridView = view.findViewById(R.id.grid_view);

      //GrelhaPlanoAdaptador adaptador = new GrelhaPlanoAdaptador(GrelhaPlanoFragment.this,);
     // gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });





        return view ;
    }

}
