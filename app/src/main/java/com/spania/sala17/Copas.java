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
TextView cal;
Button sum;
Button res;
Button pagar;
ImageView cub;
    int total;
    int copa = 0;
//crls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copas);
        cal = findViewById(R.id.Cal);
        res = findViewById(R.id.rest);
        sum = findViewById(R.id.sum);
        pagar = findViewById(R.id.button);
        cub = findViewById(R.id.cubata);
        cub.setImageResource(R.drawable.cub);

    }
    public void sumar(View v){
        copa ++;
        total = copa * 9;
        cal.setText(copa + " x 9 = "+total+"€");
        pagar.setText("El total es de " + total+"€");
    }
    public void restar(View v){
        if (copa <= 0){
           Toast.makeText(this,"No puedes seleccionar numeros negativos!", Toast.LENGTH_SHORT).show();
        }else {
            copa--;
            total = copa * 9;
            cal.setText(copa + " x 9 = " + total + "€");
            pagar.setText("El total es de "+total+"€");
        }
    }
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