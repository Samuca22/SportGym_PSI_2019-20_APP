package com.example.sportgym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sportgym.R;

import java.util.ArrayList;

public class GrelhaPlanoAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> planos;
    private TextView tv_titulo_gv;

    public GrelhaPlanoAdaptador(Context context, ArrayList<String> planos) {
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
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grelha_plano, null);
        }

        tv_titulo_gv = convertView.findViewById(R.id.tv_titulo_gv);
        tv_titulo_gv.setText(planos.get(position));

        return convertView;
    }
}
