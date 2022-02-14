package com.spania.sala17;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class Music extends AppCompatActivity
{
    //-------------------------------
    String cancionUsuario = "";
    int i = 0;
    int j = 0;
    //-------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        reponseDeezers();
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
                    URL url = new URL("https://deezerdevs-deezer.p.rapidapi.com/search?q=diavla");
                    HttpsURLConnection urlConnection =  (HttpsURLConnection) url.openConnection();

                    urlConnection.setRequestProperty("x-rapidapi-host","deezerdevs-deezer.p.rapidapi.com");
                    urlConnection.setRequestProperty("x-rapidapi-key","aa1f3addc0mshd9ae334ff631ab4p12703bjsna3fa377c581f");
                    //urlConnection.setRequestMethod("GET");
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
                    //JSONObject array2 = jsonObject.getJSONObject("artist");
                    for (i = 0, j = 0 ; (i < array.length()) ; i++)
                    {
                        System.out.println(array.getJSONObject(i).getString("title"));
                        System.out.println(array.getJSONObject(i).getString("artist"));
                        //System.out.println(array.getJSONObject(j).getString("name"));
                    }
                }
                catch (IOException | JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}