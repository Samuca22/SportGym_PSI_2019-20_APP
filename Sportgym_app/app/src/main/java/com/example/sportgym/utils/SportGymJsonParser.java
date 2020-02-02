package com.example.sportgym.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.sportgym.modelos.Plano;
import com.example.sportgym.modelos.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SportGymJsonParser {
    public static ArrayList<Plano> parserJsonPlanosTreino(JSONObject response, Context context) {
        ArrayList<Plano> listaPlanos = new ArrayList<>();

        try {
            JSONArray array = response.getJSONArray("PlanosTreino");
            for (int i = 0; i < array.length(); i++) {
                JSONObject plano = array.getJSONObject(i);
                int idPlano = plano.getInt("IDplano");
                String nome = plano.getString("nome");
                String descricao = plano.getString("descricao");
                int tipo = plano.getInt("tipo");


                Plano auxPlano = new Plano(nome, descricao);
                auxPlano.setId(idPlano);
                auxPlano.setTipo(tipo);
                listaPlanos.add(auxPlano);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaPlanos;
    }

    public static ArrayList<Plano> parserJsonPlanosNutricao(JSONObject response, Context context) {
        ArrayList<Plano> listaPlanos = new ArrayList<>();

        try {
            JSONArray array = response.getJSONArray("PlanosNutricao");
            for (int i = 0; i < array.length(); i++) {
                JSONObject plano = array.getJSONObject(i);
                int idPlano = plano.getInt("IDplano");
                String nome = plano.getString("nome");
                String descricao = plano.getString("descricao");
                int tipo = plano.getInt("tipo");


                Plano auxPlano = new Plano(nome, descricao);
                auxPlano.setId(idPlano);
                auxPlano.setTipo(tipo);
                listaPlanos.add(auxPlano);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaPlanos;
    }

    public static User parserJsonUser(String response, Context context) {
        User auxUser = null;

        try {
            JSONObject user = new JSONObject(response);
            int idUser = user.getInt("id");
            String token = user.getString("access_token");
            int nSocio = user.getInt("nSocio");
            String primeiroNome = user.getString("PrimeiroNome");
            String apelido = user.getString("Apelido");
            String telefone = user.getString("Telefone");
            String dataNascimento = user.getString("DataNascimento");
            int altura = user.getInt("Altura");
            float peso = (float) user.getDouble("Peso");

            auxUser = new User(idUser, token, nSocio, primeiroNome, apelido, telefone, dataNascimento, peso, altura);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return auxUser;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        return nInfo != null && nInfo.isConnected();
    }
}
