package com.example.sportgym.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class SportGymDBHelper extends SQLiteOpenHelper {
    private static final String DB_NOME = "sportgymDB";
    private static final int DB_VERSAO = 1;
    private final SQLiteDatabase database;
    private static final String ID = "id";

    // Tabela Plano
    private static final String TABELA_NOME_PLANO = "plano";
    private static final String NOME = "nome";
    private static final String DESCRICAO = "descricao";
    private static final String TIPO = "tipo";

    // Tabela Aula
    private static final String IDAula = "id";
    private static final String TABELA_NOME_AULA = "aula";
    private static final String TIPOAULA = "tipoAula";
    private static final String SALA = "sala";
    private static final String HORARIO = "horario";


    public SportGymDBHelper(Context context) {
        super(context, DB_NOME, null, DB_VERSAO);
        this.database = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPlanoTable = "CREATE TABLE " + TABELA_NOME_PLANO +
                "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT NOT NULL, " +
                DESCRICAO + " STRING NOT NULL, " +
                TIPO + " INTEGER NOT NULL);";
        db.execSQL(createPlanoTable);

        String createAulaTable = "CREATE TABLE " + TABELA_NOME_AULA +
                "( " + IDAula + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIPOAULA + " STRING NOT NULL, " +
                SALA + " STRING NOT NULL, " +
                HORARIO + " STRING NOT NULL);";
        db.execSQL(createAulaTable);

        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Cycling - Segunda-Feira', 'Sala Azul', '09:00 - 10:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Barriga Killer - Segunda-Feira', 'Sala Verde', '10:30 - 12:00')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Mega Calorie Burn -Segunda-Feira', 'Sala Verde', '15:00 - 16:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Body Attack - Segunda-Feira', 'Sala Vermelha', '21:00 - 22:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('TRX - Terça-Feira', 'Sala Verde', '13:30 - 15:00')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Fat Burn - Terça-Feira', 'Sala Vermelha', '18:00 - 19:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Cross Moves - Quarta-Feira', 'Sala Azul', '12:00 - 13:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Pilates - Quarta-Feira', 'Sala Azul', '15:00 - 16:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('CrossFit - Quarta-Feira', 'Sala Verde', '21:00 - 22:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Pump - Quinta-Feira', 'Sala Vermelha', '10:30 - 12:00')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Power Jump - Quinta-Feira', 'Sala Azul', '15:00 - 16:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Hit - Quinta-Feira', 'Sala Verde', '21:00 - 22:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Adaptive Box - Sexta-Feira', 'Sala Vermelha', '09:00 - 10:300')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Combine Training - Sexta-Feira', 'Sala Vermelha', '13:30 - 15:00')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Boot Camp - Sexta-Feira', 'Sala Vermelha', '18:00 - 19:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Insanity - Sexta-Feira', 'Sala Verde', '19:30 - 21:00')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('X-Celerate - Sábado', 'Sala Azul', '12:00 - 13:30')");
        db.execSQL("INSERT INTO " + TABELA_NOME_AULA + "(TIPOAULA, SALA, HORARIO) VALUES ('Combat - Sábado', 'Sala Azul', '13:30 - 15:00')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_NOME_AULA);
}


    public void adicionarPlanosBD(Plano plano){
        ContentValues values = new ContentValues();
        values.put(NOME, plano.getNome());
        values.put(DESCRICAO, plano.getDescricao());
        values.put(TIPO, plano.getTipo());

        this.database.insert(TABELA_NOME_PLANO, null, values);
    }

    public void removerAllPlanosBD() {
        this.database.delete(TABELA_NOME_PLANO, null, null);
    }

    public ArrayList<Plano> getPlanos(){
        ArrayList<Plano> planos = new ArrayList<>();

        Cursor cursor = this.database.query(TABELA_NOME_PLANO, new String[]{ID, NOME, DESCRICAO, TIPO},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Plano auxPlano = new Plano(cursor.getString(1), cursor.getString(2));
                planos.add(auxPlano);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return planos;
    }

    public ArrayList<Aula> getAulas() {
        ArrayList<Aula> aulas = new ArrayList<>();

        Cursor cursor = this.database.query(TABELA_NOME_AULA,
                new String[]{ID, TIPOAULA, SALA, HORARIO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Aula auxAula = new Aula(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                aulas.add(auxAula);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return aulas;
    }
}
