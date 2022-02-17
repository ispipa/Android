package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class PantallaInicio extends AppCompatActivity
{




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        /*ImageView imageView = findViewById(R.id.pantallaInicio);
        LottieAnimationView lottie = findViewById(R.id.pantallaInicio);
        //imageView.animate().translationY(-14000).setDuration(2700).setStartDelay(0);
        //lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);*/
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent a = new Intent(PantallaInicio.this,MainActivity.class);
                startActivity(a);
            }
        },3000);
    }
}