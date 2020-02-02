package com.example.sportgym.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sportgym.R;
import com.example.sportgym.modelos.Plano;

import java.util.ArrayList;

public class GrelhaPlanoAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Plano> planos;
    private LayoutInflater inflater;

    public GrelhaPlanoAdaptador(Context context, ArrayList<Plano> planos){
        this.context = context;
        this.planos = planos;
    }

    @Override
    public int getCount() {
        return planos.size();
    }

    @Override
    public Object getItem(int position) {
        return planos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null ){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grelha_plano, null);
        }

        Plano plano = planos.get(position);
        TextView tv_item = convertView.findViewById(R.id.tv_item);
        tv_item.setText(plano.getNome());

        return convertView;
    }
}
