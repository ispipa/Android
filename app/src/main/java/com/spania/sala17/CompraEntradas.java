package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompraEntradas extends AppCompatActivity
{
    TextView primeraEntrada;
    TextView segundaEntrada;
    TextView terceraEntrada;
    TextView cuartaEntrada;
    TextView quintaEntrada;
    TextView sextaEntrada;

    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_entradas);

        Intent i = getIntent();

        primeraEntrada = findViewById(R.id.entrada1);
        segundaEntrada = findViewById(R.id.entrada2);
        terceraEntrada = findViewById(R.id.entrada3);
        cuartaEntrada = findViewById(R.id.entrada4);
        quintaEntrada = findViewById(R.id.textView10);
        sextaEntrada = findViewById(R.id.textView11);

        bt2 = findViewById(R.id.btCompra2);
        bt3 = findViewById(R.id.btCompra3);
        bt4 = findViewById(R.id.btCompra4);
        bt5 = findViewById(R.id.btCompra5);
        bt6 = findViewById(R.id.btCompra6);
        bt7 = findViewById(R.id.btCompra7);

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = primeraEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, PaganiniEntradas.class);
                a.putExtra("infoEntrada", infoEntrada);
                startActivity(a);

            }
        });
    }
}