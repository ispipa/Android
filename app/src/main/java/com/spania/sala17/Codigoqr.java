package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Codigoqr extends AppCompatActivity
{
TextView ver;
ImageView codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoqr);
        ver= findViewById(R.id.ver);
        codigo = findViewById(R.id.imageqr);

        Intent i = getIntent();
        String vere = i.getStringExtra("message");
        ver.setText(vere);
        try
        {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bit = barcodeEncoder.encodeBitmap(ver.getText().toString(), BarcodeFormat.QR_CODE,1250,1250);
                    codigo.setImageBitmap(bit);
        }
        catch (Exception e)
                {
                    e.printStackTrace();
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
            case R.id.datos:
                Toast.makeText(Codigoqr.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent p = new Intent(this, DatosUser.class);
                startActivity(p);
                break;
            case R.id.tinder:
                Toast.makeText(Codigoqr.this,"Entrando a tinder",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent i = new Intent(this, Copas.class);
                Toast.makeText(Codigoqr.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(Codigoqr.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(Codigoqr.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(Codigoqr.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    }
