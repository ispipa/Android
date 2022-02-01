package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class codigoqr extends AppCompatActivity {
TextView ver;
ImageView codigo;
    Intent i;
    String vere="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoqr);
        ver= findViewById(R.id.ver);
        codigo = findViewById(R.id.imageqr);
        obtenerdato();
        ver.setText(vere);
        try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bit = barcodeEncoder.encodeBitmap(ver.getText().toString(), BarcodeFormat.QR_CODE,1250,1250);
                    codigo.setImageBitmap(bit);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    private void obtenerdato(){

           i = getIntent();
        for (int j = 0; j < 26; j++) {
            if (i.getStringExtra("cod"+j).equals("si")){
                vere+=i.getStringExtra("nombre"+j)+" = ";
                vere+=i.getStringExtra("cantidad"+j)+"\n";
            }
        }
            vere += "\n"+"Total  "+i.getStringExtra("total")+" €";
    }
    }

