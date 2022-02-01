package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Bebida;
import com.spania.sala17.models.Usuario;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class codigoqr extends AppCompatActivity {
    Button pedir,salir;
    ListView listaB;
TextView ver,cantselect;
ImageView codigo;
Retrofit retrofit;
ConsultaApi consultaApi;
String pedido;
List<String>nombreB;
List<Long>idB;
List<Integer>cantB;
List<Integer>precioB;
List<String>fotoB;
List<String> si;
ArrayList<Bebida>listaBebidas=new ArrayList<>();
public static ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoqr);
        ver = findViewById(R.id.ver);
        codigo = findViewById(R.id.imageqr);
        pedir=findViewById(R.id.pedir);
        salir=findViewById(R.id.salir);
        listaB=findViewById(R.id.listacop);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaBebidas);
        listaB.setAdapter(adapter);
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        consultaApi = retrofit.create(ConsultaApi.class);
        Intent i=new Intent();
        String aux=i.getStringExtra("idUser");
        for (int j = 0; j < 26; j++) {
            //si.add(i.getStringExtra("si"+j));
            if (i.getStringExtra("si"+j)=="si"){
                cantB.add(Integer.parseInt(i.getStringExtra("cantB"+j)));
                idB.add(Long.parseLong(i.getStringExtra("idB"+j)));

            }
        }
        Log.i("pepe","1");
        botones();
        }
        //obtenerdato(aux);

    /*private void obtenerdato(long id){
        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario> call = consultaApi.findUser(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Usuario usuario=response.body();
                    pedido=usuario.getPedido();
                    System.out.println(pedido);
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(codigoqr.this, "fallo actualice", Toast.LENGTH_LONG).show();
            }
        });
    }*/
    private void botones(){
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantselect.getText();
              qr();

            }
        });
    }

    private void obtenerbebida(long id){
        for (int i = 0; i < si.size(); i++) {
            Call<Bebida> call = consultaApi.findB(id);
            call.enqueue(new Callback<Bebida>() {
                @Override
                public void onResponse(Call<Bebida> call, Response<Bebida> response) {
                    if(response.isSuccessful()){
                        Bebida bebida= response.body();
                        fotoB.add(bebida.getImgBebida());
                        precioB.add(bebida.getPrecio());
                        nombreB.add(bebida.getNombreBebida());
                    }
                }
                @Override
                public void onFailure(Call<Bebida> call, Throwable t) {
                    Toast.makeText(codigoqr.this, "fallo obtener datos", Toast.LENGTH_SHORT).show();
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CustomAdapter adapter=new CustomAdapter(listaBebidas,codigoqr.this);
                listaB.setAdapter(adapter);
            }
        });
    }




    private void qr(){
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bit = barcodeEncoder.encodeBitmap(ver.getText().toString(), BarcodeFormat.QR_CODE,1250,1250);
                codigo.setImageBitmap(bit);
            }catch (Exception e){
                e.printStackTrace();
            };
        }
}



