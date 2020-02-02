package com.example.sportgym.vistas;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sportgym.Adaptadores.ListaPlanoAdaptador;
import com.example.sportgym.R;
import com.example.sportgym.listeners.PlanosListener;
import com.example.sportgym.modelos.Plano;
import com.example.sportgym.modelos.SingletonSportgym;
import com.example.sportgym.utils.SportGymJsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPlanoFragment extends Fragment implements PlanosListener {

    public final int CRIAR = 0;
    public final int VER = -1;
    ListView listView;
    private ListaPlanoAdaptador listaPlanoAdaptador;
    private FloatingActionButton fab_planoTreino;

    public ListaPlanoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_plano, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        TextView tv_title_appBar = toolbar.findViewById(R.id.tv_title_appBar);
        tv_title_appBar.setText("PLANOS DE TREINO");
        listView = view.findViewById(R.id.list_view);
        listView.setDivider(null);


        //Float Action Button para Adicionar novo plano
        fab_planoTreino = view.findViewById(R.id.fabPlanoTreino);

        SingletonSportgym.getInstance(getContext()).setPlanosListener(this);

        //Permite selecionar um item da listView e abrir o PlanoSelecionadoFragmento
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plano plano = (Plano) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), PlanoSelecionadoActivity.class);
                intent.putExtra("ID", plano.getId());
                startActivityForResult(intent, VER);
            }
        });

        fab_planoTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlanoSelecionadoActivity.class);
                startActivityForResult(intent, CRIAR);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SingletonSportgym.getInstance(getContext()).getAllPlanosTreinoAPI(getContext(), SportGymJsonParser.isConnectionInternet(getContext()));
    }

    @Override
    public void onRefreshPlanos(ArrayList<Plano> planos) {
        ListaPlanoAdaptador listaPlanoAdaptador = new ListaPlanoAdaptador(getContext(), planos);
        listView.setAdapter(listaPlanoAdaptador);
    }
}
