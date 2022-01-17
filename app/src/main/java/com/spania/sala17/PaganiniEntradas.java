package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaganiniEntradas extends AppCompatActivity
{
    TextView recepcion;
    Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paganini_entradas);

        recepcion = findViewById(R.id.recepcion);
        btOk = findViewById(R.id.btOk);

        Intent i = getIntent();
        String texto = i.getStringExtra("infoEntrada");

        recepcion.setText(texto);

        btOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent volver = new Intent(PaganiniEntradas.this, Eventos.class);
                volver.putExtra("infoEntrada", texto);
                startActivity(volver);
            }
        });
    }
}