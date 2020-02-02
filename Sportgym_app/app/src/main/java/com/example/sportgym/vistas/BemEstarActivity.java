package com.example.sportgym.vistas;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportgym.R;
import com.example.sportgym.modelos.SingletonSportgym;

public class BemEstarActivity extends AppCompatActivity {
    final double CONVERSAO = 0.01;
    final double IMC_NORMAL_MIN = 18.5;
    final double IMC_NORMAL_MAX = 24.9;
    final String ABAIXO_PESO = "Abaixo do Peso Normal";
    final String NORMAL = "Normal";
    final String ACIMA_PESO = "Acima do peso Normal";
    final String OBESIDADE = "Obesidade";
    final String CALCULE = "Calcule o seu IMC";
    int altura;
    float peso;
    EditText et_peso, et_altura;
    TextView tv_resutado_imc, tv_resultado_msg, tv_peso_ideal, tv_peso, tv_title_bemEstar;
    Button btn_calc_imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_estar);
        tv_title_bemEstar = findViewById(R.id.tv_title_bemEstar);
        tv_title_bemEstar.setText("O SEU BEM ESTAR");
        et_peso = findViewById(R.id.et_peso);
        et_altura = findViewById(R.id.et_altura);
        tv_resutado_imc = findViewById(R.id.tv_resutado_imc);
        tv_resultado_msg = findViewById(R.id.tv_resultado_msg);
        tv_peso_ideal = findViewById(R.id.tv_peso_ideal);
        tv_peso = findViewById(R.id.tv_peso);
        btn_calc_imc = findViewById(R.id.btn_calc_imc);

        SharedPreferences user_preferences = getSharedPreferences("user", MODE_PRIVATE);
        peso = user_preferences.getFloat("peso", 0);
        altura = user_preferences.getInt("altura", 0);
        et_peso.setText(String.format("%.1f", peso));
        et_altura.setText(altura+"");



        /*** Carregar Resultado Inicial ***/
        calcularIMC(altura, peso);

        btn_calc_imc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altura = Integer.parseInt(et_altura.getText().toString());
                peso = Float.parseFloat(et_peso.getText().toString());
                calcularIMC(altura, peso);
            }
        });


    }

    public void calcularIMC(int altura, float peso) {
        float resultado_IMC;
        double peso_ideal_min, peso_ideal_max;

        // esconder keyboard
        et_altura.onEditorAction(EditorInfo.IME_ACTION_DONE);


        /*** Calculo IMC e mensagem de resultado ***/

        if(altura == 0 || peso == 0)
        {
            resultado_IMC = 0;
        } else{
            resultado_IMC = (float) (peso / ((altura * CONVERSAO) * (altura * CONVERSAO)));
        }

        /*** Calculo peso ideal  ***/

        peso_ideal_min = IMC_NORMAL_MIN * ((altura * CONVERSAO) * (altura * CONVERSAO));
        peso_ideal_max = IMC_NORMAL_MAX * ((altura * CONVERSAO) * (altura * CONVERSAO));

        /*** Mostrar Resultados ***/

        tv_peso.setText(String.format("%.1fKg", peso));
        tv_resutado_imc.setText(String.format("%.2f", resultado_IMC));
        tv_peso_ideal.setText(String.format("%.2fKg - %.2fKg", peso_ideal_min, peso_ideal_max));
        if (resultado_IMC < 18.5 && resultado_IMC != 0) {
            tv_resutado_imc.setTextColor(Color.parseColor("#ff0000"));
            tv_resultado_msg.setText(ABAIXO_PESO);
            tv_resultado_msg.setTextColor(Color.parseColor("#ff0000"));
        } else if (resultado_IMC >= 18.5 && resultado_IMC <= 24.9) {
            tv_resutado_imc.setTextColor(Color.parseColor("#00ff00"));
            tv_resultado_msg.setText(NORMAL);
            tv_resultado_msg.setTextColor(Color.parseColor("#00ff00"));
        } else if (resultado_IMC >= 25 && resultado_IMC < 30) {
            tv_resutado_imc.setTextColor(Color.parseColor("#ff8800"));
            tv_resultado_msg.setText(ACIMA_PESO);
            tv_resultado_msg.setTextColor(Color.parseColor("#ff8800"));
        } else if (resultado_IMC >= 30) {
            tv_resutado_imc.setTextColor(Color.parseColor("#ff0000"));
            tv_resultado_msg.setText(OBESIDADE);
            tv_resultado_msg.setTextColor(Color.parseColor("#ff0000"));
        } else {
            tv_resutado_imc.setTextColor(Color.parseColor("#00ff00"));
            tv_resultado_msg.setText(CALCULE);
            tv_resultado_msg.setTextColor(Color.parseColor("#00ff00"));
        }

        gravarNovosDados(altura, peso);
    }

    /*** Gravar novos dados na shared e fazer PUT Request ***/
    private void gravarNovosDados(int altura, float peso) {
        SharedPreferences user_preferences = this.getSharedPreferences("user", MODE_PRIVATE);
        user_preferences.edit().putInt("altura", altura).apply();
        user_preferences.edit().putFloat("peso", peso).apply();
        SingletonSportgym.getInstance(getApplicationContext()).putAlterarPesoAlturaAPI(getApplicationContext(), peso, altura);
    }
}
