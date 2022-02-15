package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OpcionesCompra extends AppCompatActivity
{
    TextView mostracion;
    TextView infoRecogida;
    RadioButton vip;
    RadioButton noVip;
    Button btPay;
    String a;
    String precio;
    String info;
    String booleano;
    boolean vipSI;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_compra);
        mostracion = findViewById(R.id.txtMostrar);
        infoRecogida = findViewById(R.id.infoAnterior);
        btPay = findViewById(R.id.btPay);
        vip = findViewById(R.id.btVip);
        noVip = findViewById(R.id.btNoVip);
        radioGroup = findViewById(R.id.radioGroup);
        Intent i = getIntent();
        info = i.getStringExtra("infoEntrada");
        String idRecogido = i.getStringExtra("iduser");
        infoRecogida.setText(info);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if(i == R.id.btVip)
                {
                    precio = "25";
                    vipSI = true;
                    a = "Entrada VIP!";
                    booleano = String.valueOf(vipSI);
                    mostracion.setText(a);
                }
                else if (i == R.id.btNoVip)
                {
                    precio = "15";
                    vipSI = false;
                    a = "Entrada básica!";
                    booleano = String.valueOf(vipSI);
                    mostracion.setText(a);
                }
            }
        });
        btPay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent pagacion = new Intent(OpcionesCompra.this, CheckoutActivity.class);
                pagacion.putExtra("precio", precio);
                pagacion.putExtra("info", info);
                pagacion.putExtra("booleano", booleano);
                pagacion.putExtra("iduser", idRecogido);
                startActivity(pagacion);
                /*else
                    {
                        Toast.makeText(getApplicationContext(), "Por favor, pulse una opción válida.",  Toast.LENGTH_LONG).show();
                    }*/
            }
        });
    }
}