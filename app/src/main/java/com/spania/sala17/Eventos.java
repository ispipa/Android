package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Evento;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  Eventos extends AppCompatActivity
{
    Button comprar;
    Long idUserLong;
    String idUser = "";
    String todoEvento = "";
    public static ArrayList<Entrada> listadoEntradas = new ArrayList<>();
    public static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);


        //recibo intent de datos user
        Intent a = getIntent();
        //recoger Id del usuario
        idUser = a.getStringExtra("iduser");
        idUserLong = Long.parseLong(idUser);
        System.out.println(idUser + " ojito que estamos aqui");
        String comprando;


        ListView listView = findViewById(R.id.listView);
        //listadoEntradas.add(new Entrada("Barceló 23 de Marzo", R.drawable.pari));


        Intent llegar = getIntent();
        String textoRecogido = llegar.getStringExtra("infoEntrada");
        todoEvento = idUserLong.toString();
        String vipRecogido = llegar.getStringExtra("vipSI");
        comprando = llegar.getStringExtra("comprando");
        String idRecibido = llegar.getStringExtra("iduser");

        //aqui va lo de listview en caso de crasheo
        listadoEntradas.add(new Entrada(textoRecogido, R.drawable.pari, vipRecogido));
        adapter = new CustomAdapter(listadoEntradas, this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        System.out.println("añade entrada");


        //hago get de los eventos que ya existen
        Retrofit retrofitGet = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ConsultaApi consultaGet = retrofitGet.create(ConsultaApi.class);
        Call<Evento> callGet = consultaGet.findEvento(idUserLong);
        System.out.println("hacemos get");
        callGet.enqueue(new Callback<Evento>()
        {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response)
            {
                System.out.println(response.code());
                if(response.isSuccessful())
                {
                    System.out.println("Todo ok");
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t)
            {
                System.out.println("Conexión fallida: " + t.toString());
            }
        });

        System.out.println("boolean recogido: " + comprando);

        if( comprando != null && comprando.compareTo("true") == 0 )
        {
            System.out.println("entrado al if");
            System.out.println("vamos al long");
            Long idUsuario = Long.parseLong(idRecibido);
            //guardar entrada en base de datos
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
            ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
            System.out.println("estamos posteando " + textoRecogido);
            Call<Evento> call = consultaApi.insertarEvento(textoRecogido, idUsuario);
            System.out.println("posteado(?");
            call.enqueue(new Callback<Evento>()
            {
                @Override
                public void onResponse(Call<Evento> call, Response<Evento> response)
                {
                    if (response.isSuccessful())
                    {
                        System.out.println("todo ok");
                    }
                }

                @Override
                public void onFailure(Call<Evento> call, Throwable t)
                {
                    System.out.println("No conexión");
                }
            });
        }
        else
        {
            System.out.println("boolean es null");
        }

        //retornar = findViewById();
        comprar = findViewById(R.id.btCompra);

        //voy a dar funciones al boton
        comprar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent k = new Intent(Eventos.this, CompraEntradas.class);
                k.putExtra("iduser", idUser);
                startActivity(k);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.datos:
                Toast.makeText(Eventos.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent p = new Intent(this, DatosUser.class);
                startActivity(p);
                break;
            case R.id.tinder:
                Toast.makeText(Eventos.this,"Entrando a tinder",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent i = new Intent(this, Copas.class);
                Toast.makeText(Eventos.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(Eventos.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(Eventos.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(Eventos.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}