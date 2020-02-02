package com.example.sportgym.modelos;

public class Aula {


    private int id ;
    private String tipo, sala, horario;


    public Aula(int id, String tipo, String sala, String horario) {
        this.id = id;
        this.tipo = tipo;
        this.sala = sala;
        this.horario = horario;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSala() {
        return sala;
    }

    public String getHorario() {
        return horario;
    }

}
