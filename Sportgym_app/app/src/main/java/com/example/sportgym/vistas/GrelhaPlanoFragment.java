package com.example.sportgym.vistas;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sportgym.Adaptadores.GrelhaPlanoAdaptador;
import com.example.sportgym.R;
import com.example.sportgym.listeners.PlanosListener;
import com.example.sportgym.modelos.MosquittoCallBack;
import com.example.sportgym.modelos.Plano;
import com.example.sportgym.modelos.SingletonSportgym;
import com.example.sportgym.utils.SportGymJsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrelhaPlanoFragment extends Fragment implements PlanosListener {
    public final int CRIAR = 0;
    public final int VER = -1;
    GridView gridView;

    private FloatingActionButton fab_planoNutricao;


    public GrelhaPlanoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grelha_plano, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        TextView tv_title_appBar = toolbar.findViewById(R.id.tv_title_appBar);
        tv_title_appBar.setText("PLANOS DE NUTRIÇÃO");
        gridView = view.findViewById(R.id.grid_view);

        //Float Action Button para Adicionar novo plano
        fab_planoNutricao = view.findViewById(R.id.fab_planoNutricao);

        SingletonSportgym.getInstance(getContext()).setPlanosListener(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plano plano = (Plano) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), PlanoSelecionadoActivity.class);
                intent.putExtra("ID", plano.getId());
                startActivityForResult(intent, VER);
            }
        });

        fab_planoNutricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlanoSelecionadoActivity.class);
                startActivityForResult(intent, CRIAR);
            }
        });


        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client = new MqttAndroidClient(getContext(), "tcp://10.0.2.2:1883", clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    System.out.println("success");
                    client.setCallback( new MosquittoCallBack() );
                    try {
                        client.subscribe("EDICAO_PLANO", 1);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    try {
                        client.subscribe("INSERCAO_PLANO", 1);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    try {
                        client.subscribe("APAGAR_PLANO", 1);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    System.out.println("error");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SingletonSportgym.getInstance(getContext()).getAllPlanosNutricaoAPI(getContext(), SportGymJsonParser.isConnectionInternet(getContext()));
    }

    @Override
    public void onRefreshPlanos(ArrayList<Plano> planos) {
        GrelhaPlanoAdaptador grelhaPlanoAdaptador = new GrelhaPlanoAdaptador(getContext(), planos);
        gridView.setAdapter(grelhaPlanoAdaptador);
    }
}
