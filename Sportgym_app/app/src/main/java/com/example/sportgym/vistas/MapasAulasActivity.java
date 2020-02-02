package com.example.sportgym.vistas;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportgym.Adaptadores.ListaAulasAdaptador;
import com.example.sportgym.R;
import com.example.sportgym.modelos.Aula;
import com.example.sportgym.modelos.SingletonSportgym;

import java.util.ArrayList;

public class MapasAulasActivity extends AppCompatActivity {
    private ListView list_view_aulas;
    private ArrayList<Aula> listaAulas;
    private ListaAulasAdaptador listaAulasAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas_aulas);
        list_view_aulas = findViewById(R.id.list_view_aulas);

        listaAulas = SingletonSportgym.getInstance(getApplicationContext()).getAulasBD();
        listaAulasAdaptador = new ListaAulasAdaptador(MapasAulasActivity.this, listaAulas);
        list_view_aulas.setAdapter(listaAulasAdaptador);

    }
}
