package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registros extends AppCompatActivity
{
    //------------------------------------------------
    EditText[] datosUsuario;
    ArrayList<String> datosObtenidos = new ArrayList<>();
    //------------------------------------------------
    Button registro;
    TextView sing;
    //------------------------------------------------
    int cont = 0;
    String cadena="";
    //------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        //------------------------------------------------
        datosUsuario = new EditText[]
                {findViewById(R.id.nomUsuario),
                findViewById(R.id.apeUsuario),
                findViewById(R.id.emailUsuario),
                findViewById(R.id.sexoUsuario),
                findViewById(R.id.contraUsuario),
                findViewById(R.id.telefoUsuario)};
        registro = findViewById(R.id.registro);
        sing = findViewById(R.id.singn);
        //------------------------------------------------
        onClik();
        //------------------------------------------------
    }
    private void onClik()
    {
        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(validarDatos(datosUsuario))
                {
                    Toast.makeText(Registros.this,"Por favor, rellene todos los campos",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build();
                        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
                        Call<Usuario> call = consultaApi.insertarDatosUusario(
                                datosObtenidos.get(0),
                                datosObtenidos.get(1),
                                datosObtenidos.get(2),
                                datosObtenidos.get(3),
                                datosObtenidos.get(4),
                                datosObtenidos.get(5));
                        call.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response)
                            {
                                if (response.isSuccessful())
                                {
                                    Toast.makeText(Registros.this,"PasadoDatos",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t)
                            {
                                Toast.makeText(Registros.this,"Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent pasarAdatos = new Intent(Registros.this, DatosUser.class);
                        pasarAdatos.putExtra("nombreUser", datosObtenidos.get(0));
                        startActivity(pasarAdatos);
                        datosObtenidos.clear();
                    }
            }
        });
    }
    public boolean validarDatos(EditText[] campos)
    {
        for (int i = 0; i < campos.length; i++)
        {
           cadena = campos[i].getText().toString();

            if (cadena.trim().isEmpty())
            {

                cont++;
                return true;
            }
            else
                {
                    datosObtenidos.add(cadena);
                    cont=0;
                    //datosObtenidos.clear();
                }
        }
        //datosObtenidos.add(datosUsuario[cont].getText().toString());
        System.out.println(datosObtenidos);
        return  false;
    }
}
