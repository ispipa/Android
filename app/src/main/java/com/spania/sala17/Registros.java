package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Registros extends AppCompatActivity
{

    EditText[] datosUsuario;
    ArrayList<String> datosObtenidos = new ArrayList<>();
    Button registro;
    TextView sing;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        //------------------------------------------------
        datosUsuario = new EditText[]{findViewById(R.id.nomUsuario),findViewById(R.id.apeUsuario), findViewById(R.id.emailUsuario),findViewById(R.id.sexoUsuario)};
        registro = findViewById(R.id.registro);
        sing = findViewById(R.id.singn);
        //------------------------------------------------
        onClik();
        //------------------------------------------------
    }
    private void onClik()
    {
        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(validarDatos(datosUsuario))
                {
                    Toast.makeText(Registros.this,"Por favor, rellene todos los campos",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Intent pasarAdatos = new Intent(Registros.this, DatosUser.class);
                        pasarAdatos.putExtra("nombreUser", datosObtenidos.get(0));
                        startActivity(pasarAdatos);
                    }
            }
        });
    }
    public boolean validarDatos(EditText[] campos)
    {
        for (int i = 0; i < campos.length; i++)
        {
            String cadena = campos[i].getText().toString();
            if (cadena.trim().isEmpty())
            {
                cont++;
                return true;
            }
            else
                {
                    cont=0;
                    datosObtenidos.clear();
                }
        }
        datosObtenidos.add(datosUsuario[cont].getText().toString());
        return  false;
    }
}
