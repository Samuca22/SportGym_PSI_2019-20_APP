package com.example.sportgym.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sportgym.R;
import com.example.sportgym.modelos.User;
import com.example.sportgym.utils.SportGymJsonParser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_username, et_password;
    CheckBox checkBox_rememberMe;
    private final String mUrlLogin = "http://10.0.2.2/SportGym_PSI_2019-20/sportgym/frontend/web/v1/";
    private static RequestQueue volleyQueue;
    private static final String USER_INFO = "user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        checkBox_rememberMe = findViewById(R.id.checkBox_rememberMe);
        volleyQueue = Volley.newRequestQueue(this);

        SharedPreferences remember_me = getSharedPreferences("remember_checkBox", MODE_PRIVATE);
        boolean checkbox_rememberMe = remember_me.getBoolean("remember_me", false);
        if(checkbox_rememberMe)
        {
            entrarApp();
        }

        checkBox_rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences remember_me = getSharedPreferences("remember_checkBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = remember_me.edit();
                    editor.putBoolean("remember_me", true);
                    editor.apply();
                }
                if(!buttonView.isChecked())
                {
                    SharedPreferences remember_me = getSharedPreferences("remembem_checkBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = remember_me.edit();
                    editor.putBoolean("remember_me", false);
                    editor.apply();
                }
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String credenciais = conversaoBase64(et_username.getText().toString(), et_password.getText().toString());
                login(LoginActivity.this, SportGymJsonParser.isConnectionInternet(LoginActivity.this), credenciais);
            }
        });
    }

    private String conversaoBase64(String username, String password) {
        final String user_pass = username + ":" + password;

        byte[] data = new byte[0];

        try {
            data = user_pass.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }

    public void login(final Context context, boolean isConnected, final String credentials) {

        if (!isConnected) {
            Toast.makeText(context, "Sem Conexão à internet", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.GET, mUrlLogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    User user = SportGymJsonParser.parserJsonUser(response, context);
                    guardarDadosUser(user);
                    entrarApp();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Credenciais Incorretas", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json; charset=UTF-8");
                    params.put("Authorization", "Basic " + credentials);
                    return params;
                }
            };
            volleyQueue.add(request);
        }
    }

    private void guardarDadosUser(User user) {
        SharedPreferences user_preferences = getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = user_preferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("token", user.getToken());
        editor.putInt("nSocio", user.getnSocio());
        editor.putString("primeiroNome", user.getPrimeiroNome());
        editor.putString("apelido", user.getApelido());
        editor.putString("telefone", user.getTelefone());
        editor.putString("dataNascimento", user.getDataNascimento());
        editor.putFloat("peso", user.getPeso());
        editor.putInt("altura", user.getAltura());

        editor.apply();
    }

    public void entrarApp(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}
