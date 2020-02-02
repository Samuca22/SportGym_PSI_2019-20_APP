package com.example.sportgym.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sportgym.R;
import com.example.sportgym.modelos.SingletonSportgym;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    TextView tv_Nome, tv_NSocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingletonSportgym.getInstance(getApplicationContext());
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);

        fragmentManager = getSupportFragmentManager();
        carregarNavHeader();
        carregarFragmentoInicial();
    }

    private void carregarFragmentoInicial() {
        Fragment fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    private void carregarNavHeader() {
        View hView = navigationView.getHeaderView(0);
        tv_Nome = hView.findViewById(R.id.tv_Nome);
        tv_NSocio = hView.findViewById(R.id.tv_NSocio);


        SharedPreferences user_preferences = getSharedPreferences("user", MODE_PRIVATE);
        String primeiroNome = user_preferences.getString("primeiroNome", "xxxxxx");
        String apelido = user_preferences.getString("apelido", "xxxxxx");
        int nSocio = user_preferences.getInt("nSocio", 9999);


        tv_Nome.setText(primeiroNome + " " + apelido);
        tv_NSocio.setText("Nº Sócio - " + nSocio);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dadosPessoais:
                Intent intent = new Intent(MainActivity.this, DadosPessoaisActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_comoUsar:
                Fragment fragment = new ComoUsarAppFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_acerca:
                Fragment fragmento = new AcercaSportGymFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragmento).addToBackStack(null).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Terminar Sessão")
                        .setMessage("Tem a certeza que pretende terminar sessão?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences remember_me = getSharedPreferences("remember_checkBox", MODE_PRIVATE);
                                SharedPreferences.Editor editor = remember_me.edit();
                                editor.putBoolean("remember_me", false);
                                editor.apply();

                                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(logout);
                                finish();
                            }
                        }).show();
                drawerLayout.closeDrawers();
                break;

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}
