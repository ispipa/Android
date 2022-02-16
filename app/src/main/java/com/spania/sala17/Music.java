package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.spania.sala17.adapter.AdapterMusic;
import com.spania.sala17.pojo.ObjetoMusica;
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


import java.util.ArrayList;


public class Music extends AppCompatActivity
{
    //-------------------------------
    String cancionUsuario = "";
    int i = 0;
    ArrayList<ObjetoMusica> objetoMusicas = new ArrayList<>();
    String artist;
    String artistAlbun;
    String artisMusic;
    //-------------------------------
    ImageView imaAlbun;
    SearchView buscarMusica;
    RecyclerView recyclerView;
    //-------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        //---------------------------------------------
        imaAlbun = findViewById(R.id.imagenAlbun);
        buscarMusica = findViewById(R.id.buscadorMusica);
        recyclerView = findViewById(R.id.listaMusica);
        //---------------------------------------------

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
                    URL url = new URL("https://deezerdevs-deezer.p.rapidapi.com/search?limit=24&&q=" + cancionUsuario);
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
                    objetoMusicas = new ArrayList<>();
                    for (i = 0; i < array.length() ; i++)
                    {
                        JSONObject music = array.getJSONObject(i);
                        artist= (music.getJSONObject("artist").getString("name"));
                        artistAlbun= (music.getJSONObject("album").getString("cover_medium"));
                        artisMusic= (music.getString("link"));

                        /*if(artist.contains(music.getJSONObject("artist").getString("name")) &&
                                artistAlbun.contains( music.getJSONObject("album").getString("cover_medium"))&&
                                artisMusic.contains(music.getString("link")))
                        {
                            artist.remove(i);
                            artistAlbun.remove(i);
                            objetoMusicas.remove(i);
                        }else
                        {
                            objetoMusicas.add(new ObjetoMusica(artisMusic.get(i),artist.get(i),artistAlbun.get(i)));
                        }*/
                        objetoMusicas.add(new ObjetoMusica(artisMusic,artist,artistAlbun));
                    }
                    System.out.println(objetoMusicas.get(1).getNombreCancion());
                    System.out.println(objetoMusicas.get(1).getNombreArtista());
                    System.out.println(objetoMusicas.get(1).getUrlCancion());
                    System.out.println(array.length());
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                                    AdapterMusic adapterMusic = new AdapterMusic(objetoMusicas, Music.this);
                                    recyclerView.setAdapter(adapterMusic);
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
                //buscar(s);
                cancionUsuario  = s;
                System.out.println(cancionUsuario);
                reponseDeezers();
                return false;
            }
        });
    }

    /*private void buscar(String s)
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
    }*/
}
