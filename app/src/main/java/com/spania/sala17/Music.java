package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.spania.sala17.adapter.AdapterMusic;
import com.spania.sala17.pojo.MusicaObjeto;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class Music extends AppCompatActivity
{
    //-------------------------------
    ArrayList<MusicaObjeto> listaMusic;
    AdapterMusic adapterMusic;
    String cancionUsuario = "";
    int i = 0;
    String artist ="";
    String artistAlbun= "";
    //-------------------------------
    ImageView imaAlbun;
    SearchView buscarMusica;
    RecyclerView rv;
    LinearLayoutManager lm;
    //-------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        //---------------------------------------------
        imaAlbun = findViewById(R.id.imagenAlbun);
        buscarMusica = findViewById(R.id.buscadorMusica);
        rv = findViewById(R.id.rv);
        lm = new LinearLayoutManager(this);
        //---------------------------------------------
        rv.setLayoutManager(lm);
        listaMusic = new ArrayList<>();
        adapterMusic = new AdapterMusic(listaMusic);
        rv.setAdapter(adapterMusic);
        //---------------------------------------------
        buscarCanciones();
    }
    public void reponseDeezers()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                                                                                            // despues del q se ponde el parametro de requerido para buscar la musica.
                    URL url = new URL("https://deezerdevs-deezer.p.rapidapi.com/search?q=" + cancionUsuario);
                    System.out.println("Esta es la url que se le envia :" + url);
                    HttpsURLConnection urlConnection =  (HttpsURLConnection) url.openConnection();

                    urlConnection.setRequestProperty("x-rapidapi-host","deezerdevs-deezer.p.rapidapi.com");
                    urlConnection.setRequestProperty("x-rapidapi-key","aa1f3addc0mshd9ae334ff631ab4p12703bjsna3fa377c581f");
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bf = new BufferedReader(isr);
                    String linea = "";
                    String resultado ="";
                    while((linea = bf.readLine())!= null)
                    {
                        resultado+=linea;
                    }
                    JSONObject jsonObject = new JSONObject(resultado);
                    JSONArray array = jsonObject.getJSONArray("data");
                     artist = array.getJSONObject(0).getJSONObject("artist").getString("name");
                     artistAlbun = array.getJSONObject(0).getJSONObject("album").getString("cover_medium");

                    for (i = 0; i < array.length() ; i++)
                    {
                        System.out.println(array.getJSONObject(i).getString("title") + " " + " esto vale i: " + i);
                        //System.out.println(artist);
                        //System.out.printf(artistAlbun);
                    }

                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            System.out.println("Estos son los paramentso de entrada a la lista: " + artist+ "  " + artistAlbun);
                            MusicaObjeto musicaObjeto = new MusicaObjeto(artist);
                            listaMusic.add(musicaObjeto);
                            System.out.println("Esto esta en la lista musica: " + listaMusic.get(0).toString());
                            adapterMusic.notifyDataSetChanged();
                            //ponerimagenAlbun(artistAlbun);
                        }
                    });
                }
                catch (IOException | JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*private String preparaUrlImagen(String imagenAlbum)
    {
        System.out.println(" Recibo en prepararImagen :" +  "\n" + imagenAlbum);
        int posicionJPG = imagenAlbum.indexOf(".jpg");
        String nuevoNombreImagenAlbum = imagenAlbum.substring(0,posicionJPG + 4);
        //System.out.println(" Estoy en prepararImagen :" +  "\n" + nuevoNombreImagenAlbum);
        return nuevoNombreImagenAlbum;
    }*/
    public void ponerimagenAlbun(String imagenAlbun)
    {
        //System.out.println(" Estoy en ponerimagenAlbun :" +  "\n" + imagenAlbun);
        Picasso.get().load(imagenAlbun).into(imaAlbun);
    }
    public  void buscarCanciones()
    {
        buscarMusica.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                reponseDeezers();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s)
            {
                buscar(s);
                cancionUsuario  = s;
                System.out.println(cancionUsuario);
                reponseDeezers();
                return false;
            }
        });
    }

    private void buscar(String s)
    {
        ArrayList<MusicaObjeto>myList = new ArrayList<>();
        for (MusicaObjeto obj : myList)
        {
            if(obj.getArtistAlbun().toLowerCase().contains(s.toLowerCase()))
            {
                myList.add(obj);
            }
        }
        AdapterMusic adapterMusic = new AdapterMusic(myList);
        rv.setAdapter(adapterMusic);
    }
}
