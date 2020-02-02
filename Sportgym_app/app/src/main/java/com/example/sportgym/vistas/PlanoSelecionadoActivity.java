package com.example.sportgym.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportgym.R;
import com.example.sportgym.modelos.Plano;
import com.example.sportgym.modelos.SingletonSportgym;
import com.example.sportgym.utils.SportGymJsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PlanoSelecionadoActivity extends AppCompatActivity {

    private EditText et_nomePlano, et_descricaoPlano;
    FloatingActionButton fab_gravar;
    private Plano plano;
    int idPlano;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    public static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_selecionado);

        idPlano = getIntent().getIntExtra("ID", 0);
        plano = SingletonSportgym.getInstance(getApplicationContext()).getPlano(idPlano);

        et_nomePlano = findViewById(R.id.et_nomePlano);
        et_descricaoPlano = findViewById(R.id.et_descricaoPlano);

        spinner = findViewById(R.id.spinner1);

        String [] tipoPlano = new String[]{"Treino", "Nutrição"};

        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, tipoPlano);
        spinner.setAdapter(adapter);

        fab_gravar = findViewById(R.id.fab_gravar);

        fab_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plano == null) {
                    if (et_nomePlano.getText().length() != 0 && et_descricaoPlano.getText().length() != 0) {
                        plano = new Plano(et_nomePlano.getText().toString(), et_descricaoPlano.getText().toString());
                        SharedPreferences sharedPrefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                        String token = sharedPrefs.getString("token", null);
                        int id = sharedPrefs.getInt("id", 0);
                        if(spinner.getSelectedItem().toString() == "Treino")
                        {
                            SingletonSportgym.getInstance(getApplicationContext()).adicionarPlanoTreinoAPI(getApplicationContext(), SportGymJsonParser.isConnectionInternet(getApplicationContext()), plano);
                        } else {
                            SingletonSportgym.getInstance(getApplicationContext()).adicionarPlanoNutricaoAPI(getApplicationContext(), SportGymJsonParser.isConnectionInternet(getApplicationContext()), plano);
                        }
                        finish();
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                        //É necessário pedir permissão em dispositivos com Marshmallow ou acima
                        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                            //Permissão não foi concedida
                            String [] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, STORAGE_CODE);
                        }
                        else {
                            //A permissão já foi concedida -> chamar metodo para gravar o PDF
                            gravarPDF();
                        }
                    }
                    else {
                        //Se a versão do OS < Marshmallow não é necessário pedir permissão -> chamar método para gravar o PDF
                        gravarPDF();
                    }
                }
            }
        });

        if (plano == null) {
            setTitle("Criar novo plano");

        } else {
            //Dá como título o nome do Plano selecionado
            setTitle(plano.getNome());

            et_nomePlano.setCursorVisible(false);
            et_nomePlano.setLongClickable(false);
            et_nomePlano.setClickable(false);
            et_nomePlano.setFocusable(false);
            et_nomePlano.setSelected(false);
            et_nomePlano.setKeyListener(null);
            et_nomePlano.setBackgroundResource(android.R.color.transparent);

            et_descricaoPlano.setCursorVisible(false);
            et_descricaoPlano.setLongClickable(false);
            et_descricaoPlano.setClickable(false);
            et_descricaoPlano.setFocusable(false);
            et_descricaoPlano.setSelected(false);
            et_descricaoPlano.setKeyListener(null);
            et_descricaoPlano.setBackgroundResource(android.R.color.transparent);

            et_descricaoPlano.setText(plano.getDescricao());
            et_nomePlano.setText(plano.getNome());

            fab_gravar.setImageResource(R.drawable.ic_donwload);

            int tipo = plano.getTipo();

            if(tipo == 0) {
                spinner.setSelection(0);
            } else {
                spinner.setSelection(1);
            }
            //Permite com que a EditText seja "scrollable"
            et_descricaoPlano.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    private void gravarPDF() {
        //Cria objeto da Class Document
        Document mDoc = new Document();
        //Nome do ficheiro PDF
        String mFileName = plano.getNome();
        //Path do ficheiro PDF
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //Abre o documento para escrever
            mDoc.open();
            //
            String getNome = plano.getNome();
            String getDescricao = plano.getDescricao();

            mDoc.addTitle("Plano de Treino\n" + plano.getNome());

            //Adiciona um paragrafo ao documento
            mDoc.add(new Paragraph(getNome));
            mDoc.add(new Paragraph("\n\n"));
            mDoc.add(new Paragraph(getDescricao));

            //Fecha o documento
            mDoc.close();
            Toast.makeText(this, mFileName + ".pdf" + "\nFoi guardado com sucesso", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //Se houver algum erro, mostrar a mensagem da exception
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Lida com o resultado da permissão

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //A permissão foi concedida do popup -> chamar método gravarPDF()
                    gravarPDF();
                }
                else {
                    //Permissão não foi concedida no popup -> mostra a mensagem de erro
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
