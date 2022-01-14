package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Copas extends AppCompatActivity {
TextView [] calcopa=new TextView[7];
TextView [] nomcopa=new TextView[7];
Button []sum=new Button[2];
Button []res= new Button[2];
Button pagar;
ImageView []fotocopa= new ImageView[2];
    int total;
    int copa = 0;
String [] id={"calcopa","nomcopa"};
String [] nomcop={"RonCoal","RonLimon","VodkaLimon","VodkaNaranja","VodkaCola","JaggerRedBull"};
int [] calcop={7,7,7,7,7,8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copas);
        pagar = findViewById(R.id.button);
        /*for (int i = 0; i < nomcopa.length; i++) {
            calcopa[i] = findViewById(R.id.calcopa[i]);
            nomcopa[i]=findViewById(R.id.nomcopa);
            fotocopa[i] = findViewById(R.id.fotocopa);
        }
        calcopa = findViewById(R.id.calcopa);


        fotocopa = findViewById(R.id.fotocopa);
        fotocopa.setImageResource(R.drawable.cub);


        res = findViewById(R.id.rest);
        sum = findViewById(R.id.sum);*/
        int temp;
        for (int i = 0; i < id.length; i++) {
            for (int k = 0; k < nomcop.length; k++) {
                temp =getResources().getIdentifier(id[i]+k,"id",getPackageName());
                if (i==0){
                    calcopa[k]=findViewById(temp);
                    calcopa[k].setText(String.valueOf(calcop[k]));
                }else{
                    nomcopa[k]=findViewById(temp);
                    nomcopa[k].setText(nomcop[k]);
                }

            }

        }


    }

















    /*public void sumar(View v){
        copa ++;
        total = copa * 9;
        calcopa.setText(copa + " x 9 = "+total+"€");
        pagar.setText("El total es de " + total+"€");
    }
    public void restar(View v){
        if (copa <= 0){
           Toast.makeText(this,"No puedes seleccionar numeros negaticos!", Toast.LENGTH_SHORT).show();
        }else {
            copa--;
            total = copa * 9;
            calcopa.setText(copa + " x 9 = " + total + "€");
            pagar.setText("El total es de "+total+"€");
        }
    }*/
    public void pagar(View v){
        Intent i = new Intent(this,codigoqr.class);
        if(copa >= 1){
            i.putExtra("message","RonCola =  " + copa + "\n" + total);
        }else{

        }


        startActivity(i);
    }





























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.tinder:
                Toast.makeText(Copas.this,"Entrando a tinder",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent ma = new Intent(this, MainActivity.class);
                Toast.makeText(Copas.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(ma);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(Copas.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(Copas.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(Copas.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}