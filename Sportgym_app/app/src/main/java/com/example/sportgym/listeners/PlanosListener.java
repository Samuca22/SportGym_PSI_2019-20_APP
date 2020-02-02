package com.example.sportgym.listeners;

import com.example.sportgym.modelos.Plano;

import java.util.ArrayList;

public interface PlanosListener {
    void onRefreshPlanos(ArrayList<Plano> planos);
}
