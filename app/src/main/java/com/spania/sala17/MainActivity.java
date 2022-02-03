package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText email;
    EditText pss;
    String nombre;
    Button btRegistro;
    String letra;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        pss = findViewById(R.id.password);
        btRegistro = findViewById(R.id.reg);

        TextView tvMultiColor = (TextView) findViewById(R.id.sala);
        Spannable wordToSpan = new SpannableString("Sala 17");
        wordToSpan.setSpan(new ForegroundColorSpan(Color.RED), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordToSpan.setSpan(new ForegroundColorSpan(Color.RED), 4,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordToSpan.setSpan(new ForegroundColorSpan(Color.YELLOW),2 ,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMultiColor.setText(wordToSpan);
        //forgot();
        btRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*Intent registrarse = new Intent(MainActivity.this, Registro.class);
                startActivity(registrarse);*/
            }
        });

    }
    public void onclick(View v )
    {
        nombre = email.getText().toString();
        if(!nombre.equals(""))
        {
            String nombreUser = nombre;
            Intent primerIntent = new Intent(MainActivity.this, DatosUser.class);
            primerIntent.putExtra("nombreUser", nombreUser);
            startActivity(primerIntent);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String nombreUser = nombre;
    }
//prueba de hacer merges
    //confirmacion de funcionamiento de merges
}