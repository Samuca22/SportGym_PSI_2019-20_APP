package com.example.sportgym.vistas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sportgym.R;
import com.example.sportgym.modelos.SingletonSportgym;

public class AlterarUsernameDialog extends AppCompatDialogFragment {
    EditText et_alterarUsername;
    String username;
    Button btn_gravarUsername, btn_cancelarAlteracaoUsername;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alterar_username_layout, null);
        et_alterarUsername = view.findViewById(R.id.et_alterarUsername);
        btn_gravarUsername = view.findViewById(R.id.btn_gravarUsername);
        btn_cancelarAlteracaoUsername = view.findViewById(R.id.btn_cancelarAlteracaoUsername);

        builder.setView(view);
        atualizarUsername();

        btn_gravarUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = et_alterarUsername.getText().toString();
                if(!user_name.equals(username) && !user_name.equals("") && user_name.length() >= 4)
                {
                    SingletonSportgym.getInstance(getActivity()).putAlterarUsernameAPI(getContext(), user_name);
                    getActivity().finish();
                    getActivity().startActivity(getActivity().getIntent());
                    getDialog().dismiss();

                }
            }
        });

        btn_cancelarAlteracaoUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });



        return builder.create();
    }

    private void atualizarUsername() {
        username = SingletonSportgym.getInstance(getActivity().getApplicationContext()).getUsername();
        et_alterarUsername.setText(username);
    }
}
