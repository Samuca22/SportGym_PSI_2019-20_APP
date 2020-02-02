package com.example.sportgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView tv_lembrar_utilizador, tv_esqueceu_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_lembrar_utilizador = findViewById(R.id.tv_lembrar_utilizador);


        //tv_esqueceu_password.setPaintFlags(tv_esqueceu_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void onClickEsqueceuPassword(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "TextView Pressionada", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClickLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
