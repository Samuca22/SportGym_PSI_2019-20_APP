package com.example.sportgym;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

<<<<<<< HEAD:app/src/main/java/com/example/sportgym/BemEstarActivity.java
import com.example.sportgym.R;
=======
import android.os.Bundle;
import android.view.View;
>>>>>>> Samuel-Design:SportGymApp/app/src/main/java/com/example/sportgymapp/MainActivity.java

public class BemEstarActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/sportgym/BemEstarActivity.java
        setContentView(R.layout.activity_bem_estar);
=======
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DefinicoesFragment()).commit();
    }

    public void onClickDefinicoes(View view) {

>>>>>>> Samuel-Design:SportGymApp/app/src/main/java/com/example/sportgymapp/MainActivity.java
    }
}
