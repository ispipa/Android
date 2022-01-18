package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class Tinder extends AppCompatActivity {
ArrayList<String>s;
ArrayList<String>nombre = new ArrayList<String>();
ArrayAdapter arrayAdapter;

boolean Female = false;
int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);



        s=new ArrayList<String>();
        if(Female == false) {
            s.add("one");
            s.add("dos");
            s.add("tres");
            s.add("cuatro");
            s.add("cinco");
            s.add("seis");
            s.add("siete");
            s.add("one");
            s.add("dos");
            s.add("tres");
            s.add("cuatro");
            s.add("cinco");
            s.add("seis");
            s.add("siete");
        }else{
            s.add("ola");
            s.add("ola");
            s.add("tres");
            s.add("cuatro");
            s.add("cinco");
            s.add("seis");
            s.add("siete");
        }
        SwipeFlingAdapterView swipeFlingAdapterView=(SwipeFlingAdapterView) findViewById(R.id.card);



        arrayAdapter = new ArrayAdapter<String>(this,R.layout.detail,R.id.text,s);
        swipeFlingAdapterView.setAdapter(arrayAdapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                s.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(Tinder.this,"dislike",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(Tinder.this,"like",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });
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
                Toast.makeText(Tinder.this,"Entrando a tinder ",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent i = new Intent(this, Copas.class);
                Toast.makeText(Tinder.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(Tinder.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(Tinder.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(Tinder.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}