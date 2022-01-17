package com.spania.sala17;

import static com.spania.sala17.R.id.nomUsuario;
import static com.spania.sala17.R.id.south;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Registros extends AppCompatActivity
{

    TextView[] datosUsuario;
    String[] datosObtenidos;
    Button registro;
    TextView sing;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        //------------------------------------------------
        datosUsuario = new TextView[]{findViewById(R.id.nomUsuario),findViewById(R.id.apeUsuario), findViewById(R.id.emailUsuario),findViewById(R.id.sexoUsuario)};
        registro = findViewById(R.id.registro);
        sing = findViewById(R.id.singn);
        //------------------------------------------------
        /*DividerItemDecoration decoration = new DividerItemDecoration(Registros.this,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getDrawable());
        sing.add*/
        //------------------------------------------------
        onClik();
        //------------------------------------------------
    }
    private void comprobacion()
    {
        for (int i = 0; i < datosUsuario.length; i++)
        {
            if(!datosUsuario[i].getText().toString().equals(""))
            {
                cogerDatos();
            }
            else
            {
                Toast.makeText(Registros.this,"Por favor, rellene todos los campos",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void cogerDatos()
    {
        datosObtenidos = new String[4];
        for (int i = 0 ; i < datosUsuario.length ; i++)
        {
            datosObtenidos[i] = datosUsuario[i].getText().toString();
        }
    }
    private void onClik()
    {
        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                comprobacion();
            }
        });
    }
}