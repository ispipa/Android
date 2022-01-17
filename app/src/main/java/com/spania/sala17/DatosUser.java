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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_user);
        btTinder = findViewById(R.id.btTinder);
        btEventos = findViewById(R.id.btEventos);
        bienvenida = findViewById(R.id.bienvenida);
        Intent j = getIntent();
        welcome = j.getStringExtra("nombreUser");
        bienvenida.setText("Willkommen, " + welcome + "!");
        //Pasar de esta activity a la del Tinder
        btTinder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(DatosUser.this, Tinder.class);
                startActivity(i);
            }
        });
        btEventos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent a = new Intent(DatosUser.this, Eventos.class);
                startActivity(a);
            }
        });
    }
}