package com.example.sportgym;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.example.sportgym.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrelhaPlanoFragment extends Fragment {
    private GridView gridView;
    private GrelhaPlanoAdaptador grelhaPlanoAdaptador;


    public GrelhaPlanoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grelha_plano, container, false);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Treino Força");
        arrayList.add("Emagrecer");


        gridView = view.findViewById(R.id.grid_view);

        grelhaPlanoAdaptador = new GrelhaPlanoAdaptador(getContext(), arrayList);
        gridView.setAdapter(grelhaPlanoAdaptador);


        return view;

    }


}

