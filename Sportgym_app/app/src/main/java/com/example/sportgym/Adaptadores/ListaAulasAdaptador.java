package com.example.sportgym.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportgym.R;
import com.example.sportgym.modelos.Aula;

import java.util.ArrayList;

public class ListaAulasAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Aula> aulas;
    private LayoutInflater inflater;

    public ListaAulasAdaptador(Context context, ArrayList<Aula> aulas) {
        this.context = context;
        this.aulas = aulas;
    }

    @Override
    public int getCount() {
        return aulas.size();
    }

    @Override
    public Object getItem(int position) {
        return aulas.get(position);
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
            convertView = inflater.inflate(R.layout.item_lista_aula, null);

        }
        ViewHolderLista viewHolder = (ViewHolderLista) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(position);
        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvTipo, tvSala, tvHorario;
        ImageView image_aula;

        public ViewHolderLista(View view) {
            tvTipo = view.findViewById(R.id.tv_tipo);
            tvSala = view.findViewById(R.id.tv_sala);
            tvHorario = view.findViewById(R.id.tv_horario);
            image_aula = view.findViewById(R.id.image_aula);
        }

        public void update(int position) {

            Aula aula = aulas.get(position);
            tvTipo.setText(aula.getTipo());
            tvSala.setText(aula.getSala());
            tvHorario.setText(aula.getHorario());
                if (position == 0 || position == 4 || position == 8 || position == 12 || position == 16) {
                    image_aula.setImageResource(R.drawable.aulas1);
                }
                if (position == 1 || position == 5 || position == 9 || position == 13) {
                    image_aula.setImageResource(R.drawable.aulas2);
                }
                if (position == 2 || position == 6 || position == 10 || position == 14) {
                    image_aula.setImageResource(R.drawable.aulas3);
                }
                if (position == 3 || position == 7 || position == 11 || position == 15) {
                    image_aula.setImageResource(R.drawable.aulas4);
                }

        }


    }

}
