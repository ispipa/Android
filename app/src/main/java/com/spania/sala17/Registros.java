package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Registros extends AppCompatActivity
{

    TextView[] datosUsuario;
    ArrayList<String> datosObtenidos = new ArrayList<>();
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
        onClik();
        //------------------------------------------------
    }
    private void comprobacion()
    {
        for (int i = 0; i < datosUsuario.length; i++)
        {
            if(!datosUsuario[i].getText().toString().equals(""))
            {
                datosObtenidos.add(datosUsuario[i].getText().toString());
                System.out.println(datosObtenidos.get(i));
                Intent pasarAdatos = new Intent(Registros.this, DatosUser.class);
                pasarAdatos.putExtra("nombreUser", datosObtenidos.get(0));
                startActivity(pasarAdatos);
            }
            else
            {
                Toast.makeText(Registros.this,"Por favor, rellene todos los campos",Toast.LENGTH_SHORT).show();
            }
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