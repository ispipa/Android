package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompraEntradas extends AppCompatActivity
{
    TextView primeraEntrada;
    TextView segundaEntrada;
    TextView terceraEntrada;
    TextView cuartaEntrada;
    TextView quintaEntrada;
    TextView sextaEntrada;
    TextView septimaEntrada;
    TextView octavaEntrada;

    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;
    Button bt8;
    Button bt9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_entradas);

        Intent i = getIntent();
        String idRecogido = i.getStringExtra("iduser");

        primeraEntrada = findViewById(R.id.entrada);
        primeraEntrada.setText("Chill en Badulaque");
        segundaEntrada = findViewById(R.id.entrada2);
        segundaEntrada.setText("Fiesta fin de exámenes");
        terceraEntrada = findViewById(R.id.entrada3);
        terceraEntrada.setText("Redada del chupito");
        cuartaEntrada = findViewById(R.id.entrada4);
        cuartaEntrada.setText("Fiesta bienvenida del verano");
        quintaEntrada = findViewById(R.id.textView10);
        quintaEntrada.setText("Fiesta año nuevo");
        sextaEntrada = findViewById(R.id.textView11);
        sextaEntrada.setText("Locura absoluta");
        septimaEntrada = findViewById(R.id.textView12);
        septimaEntrada.setText("Back to the kalimotxo");
        octavaEntrada = findViewById(R.id.textView13);
        octavaEntrada.setText("Stand up cubata");

        bt2 = findViewById(R.id.btCompra2);
        bt3 = findViewById(R.id.btCompra3);
        bt4 = findViewById(R.id.btCompra4);
        bt5 = findViewById(R.id.btCompra5);
        bt6 = findViewById(R.id.btCompra6);
        bt7 = findViewById(R.id.btCompra7);
        bt8 = findViewById(R.id.btCompra8);
        bt9 = findViewById(R.id.btCompra9);

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = primeraEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = segundaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = terceraEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = cuartaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = quintaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = sextaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);

            }
        });
        bt8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = septimaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String infoEntrada = octavaEntrada.getText().toString();
                Intent a = new Intent(CompraEntradas.this, OpcionesCompra.class);
                a.putExtra("infoEntrada", infoEntrada);
                a.putExtra("iduser", idRecogido);
                startActivity(a);
            }
        });
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
            case R.id.datos:
                Toast.makeText(CompraEntradas.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent p = new Intent(this, DatosUser.class);
                startActivity(p);
                break;
            case R.id.tinder:
                Toast.makeText(CompraEntradas.this,"Entrando a tinder aaaaa",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent i = new Intent(this, Copas.class);
                Toast.makeText(CompraEntradas.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(CompraEntradas.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(CompraEntradas.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(CompraEntradas.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}