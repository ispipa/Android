package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatosUser extends AppCompatActivity
{
    Button btTinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_user);
        btTinder = findViewById(R.id.btTinder);

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
    }
}