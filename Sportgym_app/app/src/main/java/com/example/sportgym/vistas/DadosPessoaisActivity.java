package com.example.sportgym.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sportgym.R;
import com.example.sportgym.modelos.SingletonSportgym;
import com.example.sportgym.utils.SportGymJsonParser;

public class DadosPessoaisActivity extends AppCompatActivity {
    private Button btn_alterarUsername, btn_alterarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);
        btn_alterarUsername = findViewById(R.id.btn_alterarUsername);
        btn_alterarPassword = findViewById(R.id.btn_alterarPassword);


        
        btn_alterarUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUsernameDialog();
            }
        });

        btn_alterarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPasswordDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingletonSportgym.getInstance(getApplicationContext()).getUsername(getApplicationContext(), SportGymJsonParser.isConnectionInternet(getApplicationContext()));
    }

    private void openUsernameDialog() {
        AlterarUsernameDialog alterarUsernameDialog = new AlterarUsernameDialog();
        alterarUsernameDialog.show(getSupportFragmentManager(), "alterarUsernameDialog");
    }

    private void openPasswordDialog() {
        AlterarPasswordDialog alterarPasswordDialog = new AlterarPasswordDialog();
        alterarPasswordDialog.show(getSupportFragmentManager(), "alterarPasswordDialog");
    }
}
