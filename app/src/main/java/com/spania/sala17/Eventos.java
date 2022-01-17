package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Eventos extends AppCompatActivity
{
    TextView primeraEntrada;
    TextView segundaEntrada;
    TextView terceraEntrada;
    TextView cuartaEntrada;
    Button comprar;
    Button retornar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        Intent llegar = getIntent();
        String textoRecogido = llegar.getStringExtra("infoEntrada");

        //referencio los textView que tengo y al boton
        primeraEntrada = findViewById(R.id.entrada1);
        primeraEntrada.setText(textoRecogido);

        segundaEntrada = findViewById(R.id.entrada2);
        terceraEntrada = findViewById(R.id.entrada3);
        cuartaEntrada = findViewById(R.id.entrada4);
        comprar = findViewById(R.id.btCompra);
        retornar = findViewById(R.id.retorno);

        Intent a = getIntent();

        //voy a dar funciones al boton
        comprar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent k = new Intent(Eventos.this, CompraEntradas.class);
                startActivity(k);
            }
        });

        retornar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent volverAtras = new Intent(Eventos.this, DatosUser.class);
                startActivity(volverAtras);
            }
        });
    }
}