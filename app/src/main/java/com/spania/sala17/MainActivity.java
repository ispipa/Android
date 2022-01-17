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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        pss = findViewById(R.id.password);
        //forgot();

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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tinder:
                Toast.makeText(MainActivity.this,"Entrando a tinder aaaaa",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent i = new Intent(this, Copas.class);
                Toast.makeText(MainActivity.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(MainActivity.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(MainActivity.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//prueba de hacer merges
    //confirmacion de funcionamiento de merges
}