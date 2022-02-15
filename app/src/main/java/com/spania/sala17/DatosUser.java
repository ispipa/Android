package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosUser extends AppCompatActivity
{
    Button btTinder;
    TextView bienvenida;
    String welcome;
    Button btEventos;
    Button btcompra;
    String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_user);

        //aqui vuelvo de la seccion de eventos
        Intent volverAtras = getIntent();
        Intent registros = getIntent();
        String nombreRegistro = registros.getStringExtra("nombreUser");

        btTinder = findViewById(R.id.btTinder);
        btEventos = findViewById(R.id.btEventos);
        bienvenida = findViewById(R.id.bienvenida);
        btcompra=findViewById(R.id.btCompra);
        Intent j = getIntent();
        welcome = j.getStringExtra("nombreUser");
        idUser= j.getStringExtra("iduser");

        //si viene de registros seteo un texto al textView, si viene de login seteo otro
        if(!nombreRegistro.equals(""))
        {
            bienvenida.setText("Willkommen, " + nombreRegistro + "!");
        }
        else
            {
                bienvenida.setText("Willkommen, " + welcome + "!");
            }

        //Pasar de esta activity a la del Tinder
        btTinder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(DatosUser.this, Tinder.class);
                i.putExtra("iduser",idUser);
                startActivity(i);
            }
        });
        //Pasar de esta activity a la del eventos
        btEventos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent a = new Intent(DatosUser.this, Eventos.class);
                a.putExtra("iduser",idUser);
                System.out.println("Recibimos algo?: " + idUser);
                startActivity(a);
            }
        });
        //Pasar de esta activity a la del comprar copas
        btcompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(DatosUser.this, Copas.class);
                e.putExtra("iduser",String.valueOf(idUser));
                startActivity(e);
            }
        });

    }
}