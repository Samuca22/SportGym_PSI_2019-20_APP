package com.example.sportgym.modelos;

import androidx.annotation.NonNull;

public class Plano {
    private int id;
    private String nome;
    private String descricao;
    private int tipo;

    public Plano(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
