package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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


public class Music<adapterMusic> extends AppCompatActivity
{
    //-------------------------------
    String cancionUsuario = "";
    int i = 0;
    public static ArrayList<ObjetoMusica> objetoMusicas = new ArrayList<>();
    String artist;
    String imaCancion;
    String artisMusicLink;
    String tituloCancion;
    //-------------------------------
    public static ImageView imaAlbun;
    SearchView buscarMusica;
   public static RecyclerView recyclerView;
    //-------------------------------
    public static SeekBar seekBar;
      static MediaPlayer mediaPlayer;
        static Handler handler = new Handler();
      public  static TextView inicioCancion;
      public  static TextView finalCancion;
   public  static AdapterMusic adapterMusic;
   //public static  boolean cambio = true;

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
        mediaPlayer = new MediaPlayer();
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        inicioCancion = findViewById(R.id.inicioDuracionMusica);
        finalCancion = findViewById(R.id.finalDuracionMusica);
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
                        imaCancion= (music.getJSONObject("album").getString("cover_big"));
                        artisMusicLink= (music.getString("preview"));
                        tituloCancion = (music.getString("title"));
                        objetoMusicas.add(new ObjetoMusica(tituloCancion,artist,imaCancion,artisMusicLink));
                    }
                    System.out.println(array.length());
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ponerimagenAlbun();

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
    public void ponerimagenAlbun()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(Music.this));
        adapterMusic = new AdapterMusic(objetoMusicas,Music.this);
        recyclerView.setAdapter(adapterMusic);
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
                if(cancionUsuario.equals(""))
                {
                    System.out.println("sdfsdfds");
                    objetoMusicas.clear();
                    adapterMusic.notifyDataSetChanged();
                    //actualizarSeekBar();
                }
                else
                {
                    reponseDeezers();
                }
                return false;
            }
        });
    }
    public static void actualizarBoolean()
    {
        if(AdapterMusic.clickUsuario)
        {
            System.out.println("sdfsdfds");
            objetoMusicas.clear();
            adapterMusic.notifyDataSetChanged();
            //actualizarSeekBar();
        }
        else
            {
                System.out.println("sdfsdfds123");
                AdapterMusic.clickUsuario = false;
           }

    }
    public static void prepararMediaPlayer(String duracion)
    {

        try
        {
            if(AdapterMusic.clickUsuario)
            {
                if (mediaPlayer.isPlaying())
                {
                    handler.removeCallbacks(actualizacion);
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(duracion);
                    mediaPlayer.prepare();
                    finalCancion.setText(cambioMilliSegundosATimer(mediaPlayer.getDuration()));
                    mediaPlayer.start();
                    actualizarSeekBar();

                } else
                    {
                    //cambio = true;
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(duracion);
                    mediaPlayer.prepare();
                    finalCancion.setText(cambioMilliSegundosATimer(mediaPlayer.getDuration()));
                    mediaPlayer.start();
                    actualizarSeekBar();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private static Runnable actualizacion = new Runnable()
    {
        @Override
        public void run()
        {
            long duracionEsperada = mediaPlayer.getCurrentPosition();
            inicioCancion.setText(cambioMilliSegundosATimer(duracionEsperada));
            actualizarSeekBar();

        }
    };
    private static void actualizarSeekBar()
    {
        if(mediaPlayer.isPlaying())
        {
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration())*100));
            handler.postDelayed(actualizacion, 1000);
        }
        else
            {
               imaAlbun.setVisibility(View.INVISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
               inicioCancion.setVisibility(View.INVISIBLE);
               finalCancion.setVisibility(View.INVISIBLE);
                seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration())*100));
                handler.postDelayed(actualizacion, 1000);
            }
    }
    public static String cambioMilliSegundosATimer(long milisegundos)
    {
        String tiempo = "";
        String segundosTiempo ="";
        int horas = (int) (milisegundos / (1000 * 60 * 60));
        int minutos = (int) (milisegundos % (1000 * 60 * 60)) / (1000 * 60);
        int  segundos = (int) ((milisegundos % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if(horas >0)
        {
            tiempo = horas + ":";
        }
        if(segundos < 10)
        {
            segundosTiempo = "0" + segundos;
        }
        else
            {
                segundosTiempo = "" +segundos;
            }
        tiempo = tiempo + minutos + ":" + segundosTiempo;
        return tiempo;
    }

   /* public  static void buscar(ArrayList<String> cancionesPantall)
    {
        System.out.println("Esto es lo que tiene canciaoens: " + cancionesPantall);
        for (int i = 0; i <cancionesPantall.size() ; i++)
        {
            int j = i +1;
                Picasso.get().load(cancionesPantall.get(i)).into(imaAlbun);
                prepararMediaPlayer(cancionesPantall.get(i));
                //Music.adapterMusic = new AdapterMusic(obj, ct);
                //Music.recyclerView.setAdapter(Music.adapterMusic);
            if(i < j)
            {
                    cancionesPantall.remove(i -1);
            }
            System.out.println(cancionesPantall);
        }
        System.out.println(cancionesPantall);
    }*/
   @Override
   protected void onPause()
   {
       super.onPause();
       mediaPlayer.reset();
   }
}

