package com.example.sportgym.modelos;

public class User {
    private int id;
    private String token;
    private int nSocio;
    private String primeiroNome;
    private String apelido;
    private String telefone;
    private String dataNascimento;
    private float peso;
    private int altura;


    public User(int id, String token, int nSocio, String primeiroNome, String apelido, String telefone, String dataNascimento, float peso, int altura) {
        this.id = id;
        this.token = token;
        this.nSocio = nSocio;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }


    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public int getnSocio() {
        return nSocio;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public float getPeso() {
        return peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setId(int id) {
        this.id = id;
    }
}
