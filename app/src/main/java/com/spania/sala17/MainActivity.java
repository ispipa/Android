package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    EditText email;
    EditText pss;
    String nombre;
    Button btRegistro;
    String letra;
    boolean paso=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        pss = findViewById(R.id.password);
        btRegistro = findViewById(R.id.reg);

        TextView tvMultiColor = (TextView) findViewById(R.id.sala);
        Spannable wordToSpan = new SpannableString("Sala 17");
        wordToSpan.setSpan(new ForegroundColorSpan(Color.RED), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordToSpan.setSpan(new ForegroundColorSpan(Color.RED), 4,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordToSpan.setSpan(new ForegroundColorSpan(Color.YELLOW),2 ,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMultiColor.setText(wordToSpan);
        //forgot();
        btRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent registrarse = new Intent(MainActivity.this, Registros.class);
                startActivity(registrarse);
            }
        });

    }
    public void onclick(View v )
    {
        nombre = email.getText().toString();
        letra=pss.getText().toString();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario>call=consultaApi.findUserEmail(nombre);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Usuario usuario= response.body();
                    String auxpass=usuario.getPassUsuario();
                    if (letra.equals(auxpass)) {
                        String nombreUser = usuario.getNombreUsuario();
                        Intent primerIntent = new Intent(MainActivity.this, DatosUser.class);
                        primerIntent.putExtra("nombreUser", nombreUser);
                        primerIntent.putExtra("iduser",String.valueOf(usuario.getIdUsuario()));
                        startActivity(primerIntent);
                        paso=true;
                    }
                }
                if (paso==false){
                    Toast.makeText(MainActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String nombreUser = nombre;
    }
//prueba de hacer merges
    //confirmacion de funcionamiento de merges
}