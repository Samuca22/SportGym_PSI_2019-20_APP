package com.example.sportgym.vistas;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sportgym.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Button btn_bem_estar, btn_planos_nutricao, btn_planos_treino, btn_mapa_aulas;
    private TextView tv_title_appBar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        tv_title_appBar = toolbar.findViewById(R.id.tv_title_appBar);
        tv_title_appBar.setText("MENU PRINCIPAL");

        btn_planos_treino = view.findViewById(R.id.btn_planos_treino);
        btn_planos_nutricao = view.findViewById(R.id.btn_planos_nutricao);
        btn_mapa_aulas = view.findViewById(R.id.btn_mapa_aulas);
        btn_bem_estar = view.findViewById(R.id.btn_bem_estar);



        btn_planos_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaPlanoFragment fragment = new ListaPlanoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
            }
        });
        btn_planos_nutricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrelhaPlanoFragment fragment = new GrelhaPlanoFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
            }
        });
        btn_mapa_aulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapasAulasActivity.class);
                startActivity(intent);
            }
        });
        btn_bem_estar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BemEstarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
