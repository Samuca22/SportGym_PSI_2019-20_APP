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

public class AlterarPasswordDialog extends AppCompatDialogFragment {
    EditText et_alterarPasswordAtual, et_alterarPasswordNova, et_alterarPasswordConfirmar;
    Button btn_gravarPassword, btn_cancelarAlteracaoPassword;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alterar_password_layout, null);
        et_alterarPasswordAtual = view.findViewById(R.id.et_alterarPasswordAtual);
        et_alterarPasswordNova = view.findViewById(R.id.et_alterarPasswordNova);
        et_alterarPasswordConfirmar = view.findViewById(R.id.et_alterarPasswordConfirmar);
        btn_gravarPassword = view.findViewById(R.id.btn_gravarPassword);
        btn_cancelarAlteracaoPassword = view.findViewById(R.id.btn_cancelarAlteracaoPassword);

        builder.setView(view);

        btn_gravarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password_atual = et_alterarPasswordAtual.getText().toString();
                String password_nova = et_alterarPasswordNova.getText().toString();
                String password_confirmar = et_alterarPasswordConfirmar.getText().toString();

                if(!password_atual.equals("") && !password_nova.equals("") && !password_confirmar.equals(""))
                {
                    if(password_atual.length() >= 4 && password_nova.length() >= 4)
                    {
                        if(password_confirmar.equals(password_nova))
                        {
                            SingletonSportgym.getInstance(getActivity()).putAlterarPasswordAPI(getContext(), password_nova, password_atual);
                            getDialog().dismiss();
                        } else {
                            et_alterarPasswordConfirmar.setError("Passwords Não Coincidem");
                        }
                    }



                }
            }
        });

        btn_cancelarAlteracaoPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return builder.create();
    }
}
