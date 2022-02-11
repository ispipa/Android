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
    TextView vipOno;
    TextView leureleh;
    Button btOk;
    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paganini_entradas);

        recepcion = findViewById(R.id.recepcion);
        vipOno = findViewById(R.id.vipOno);
        leureleh = findViewById(R.id.leureleh);
        btOk = findViewById(R.id.btOk);

        Intent i = getIntent();
        texto = i.getStringExtra("info");
        String vipSI = i.getStringExtra("booleano");
        String precio = i.getStringExtra("precio");

        int precioPagos = Integer.parseInt(precio);

        if(vipSI.equals("true"))
        {
            vipSI = "VIP: SI";
        }
        else
            {
                vipSI = "VIP: NO";
            }

        recepcion.setText(texto);
        vipOno.setText(vipSI);
        leureleh.setText(precio);

        String finalVipSI = vipSI;
        btOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*Eventos.listadoEntradas.add(new Entrada(texto, R.drawable.pari));
                Eventos.adapter.notifyDataSetChanged();*/
                finish();
                Intent volver = new Intent(PaganiniEntradas.this, Eventos.class);
                volver.putExtra("infoEntrada", texto);
                volver.putExtra("vipSI", finalVipSI);
                startActivity(volver);
            }
        });
    }

    /*@Override
    protected void onDestroy()
    {
        super.onDestroy();
        MainActivity.guardarDatos();
    }*/
}