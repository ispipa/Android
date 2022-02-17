package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Bebida;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Codigoqr extends AppCompatActivity
{
    Button pedir;

    TextView ver,cantselect;
    ImageView codigo;
    Retrofit retrofit;
    ConsultaApi consultaApi;
    String pedido;
    String pedidos="";
    String idUser;
    public static ArrayAdapter<Bebida> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoqr);
        ver = findViewById(R.id.ver);
        codigo = findViewById(R.id.imageqr);
        pedir=findViewById(R.id.pedirotravez);



        Intent i=getIntent();
        //String aux=i.getStringExtra("idUser");
        idUser=i.getStringExtra("idUser");
        for (int j = 0; j < 26; j++) {
            pedido=i.getStringExtra("ocupado"+j);
            if (pedido.equals("si")){
                pedidos+=i.getStringExtra("nombre"+j)+" X "+i.getStringExtra("cant"+j)+"\n";
                System.out.println(pedidos);
            }
            ver.setText(pedidos);
            qr();
        }
        botones();
    }

    private void botones(){
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(Codigoqr.this, Copas.class);
                e.putExtra("iduser",String.valueOf(idUser));
                startActivity(e);
                //finish()

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
