package com.example.sportgymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DefinicoesFragment()).commit();
    }

    public void onClickDefinicoes(View view) {

    }
}
