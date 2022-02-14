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
    String cadena="";
    Retrofit retrofit;
    ConsultaApi consultaApi;
    String usuarioEmail="";
    String emailUsuario= "";
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
        //------------------------------------------------
        retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
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
                    extracted2();
                    extracted();
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
                return true;
            }
            else
            {
                datosObtenidos.add(cadena);
            }
        }
        System.out.println(datosObtenidos.get(0)+datosObtenidos.get(1)+datosObtenidos.get(4)+datosObtenidos.get(2)+datosObtenidos.get(5)+datosObtenidos.get(3));
        return  false;
    }
    public void extracted2()
    {
        emailUsuario  = datosObtenidos.get(2);
        System.out.println(emailUsuario);
        consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario> call = consultaApi.findUserEmail(emailUsuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if (response.isSuccessful())
                {
                    Usuario usuario = response.body();
                    usuarioEmail = String.valueOf(usuario.getEmailUsuario());
                    System.out.println("Email puesto : " + datosObtenidos.get(2) + "\n\"" + "Email base datos = " + usuarioEmail);
                    if(datosObtenidos.get(2).compareTo(usuarioEmail) == 0)
                    {
                        Toast.makeText(Registros.this,"un usuario con el mismo email ya esta registrado",Toast.LENGTH_SHORT).show();
                        datosObtenidos.clear();
                        System.out.println(datosObtenidos);
                    }
                    else
                    {
                        extracted();
                    }
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t)
            {
                Toast.makeText(Registros.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void extracted()
    {
        consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario> call = consultaApi.insertarDatosUusario(
                datosObtenidos.get(0),
                datosObtenidos.get(1),
                datosObtenidos.get(4),
                datosObtenidos.get(2),
                datosObtenidos.get(5),
                datosObtenidos.get(3));

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if (response.isSuccessful())
                {
                    /*Toast.makeText(Registros.this,"PasadoDatos",Toast.LENGTH_LONG).show();
                    if(datosObtenidos.size() == 6)
                    {
                        Toast.makeText(Registros.this,"PasadoDatos",Toast.LENGTH_LONG).show();
                        Intent pasarAdatos = new Intent(Registros.this, DatosUser.class);
                        pasarAdatos.putExtra("nombreUser", datosObtenidos.get(0));
                        startActivity(pasarAdatos);
                    }*/
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t)
            {
                if(datosObtenidos.size() == 6)
                {
                    Toast.makeText(Registros.this,"PasadoDatos",Toast.LENGTH_LONG).show();
                    Intent pasarAdatos = new Intent(Registros.this, DatosUser.class);
                    pasarAdatos.putExtra("nombreUser", datosObtenidos.get(0));
                    startActivity(pasarAdatos);
                }
            }
        });
    }
}
