package com.example.sportgym;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportgym.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Button btn_bem_estar;
    Button btn_mapa_aulas;
    Button btn_planos_treino;
    Button btn_planos_nutricao;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btn_bem_estar = view.findViewById(R.id.btn_bem_estar);
        btn_mapa_aulas = view.findViewById(R.id.btn_mapa_aulas);
        btn_planos_nutricao = view.findViewById(R.id.btn_planos_nutricao);
        btn_planos_treino = view.findViewById(R.id.btn_planos_treino);

        btn_bem_estar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BemEstarActivity.class);
                startActivity(intent);
            }
        });

        btn_mapa_aulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapasAulasActivity.class);
                startActivity(intent);
            }
        });

        btn_planos_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrelhaPlanoFragment fragment = new GrelhaPlanoFragment();

                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
                }
            }
        });


        return view;
    }

}
