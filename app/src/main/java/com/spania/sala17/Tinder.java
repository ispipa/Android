package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class Tinder extends AppCompatActivity {
ArrayList<String>s;
ArrayList<String>nombre = new ArrayList<String>();
ArrayAdapter arrayAdapter;
TextView nbs;

int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);
        nbs = findViewById(R.id.nombress);

        //Recojo el intent de la pantalla de datos del usuario
        Intent i = getIntent();

        nombre.add("maria");
        nombre.add("noe");
        nombre.add("Sand");
        nombre.add("Ana");
        nombre.add("mamahuevo");
        nombre.add("lucifer");
        nombre.add("lucia");
        //nbs.setText(nombre.get(0));

        s=new ArrayList<String>();
        s.add("one");
        s.add("dos");
        s.add("tres");
        s.add("cuatro");
        s.add("cinco");
        s.add("seis");
        s.add("siete");
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
}