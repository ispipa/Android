package com.spania.sala17;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Element;

import com.stripe.android.PaymentConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PagosLanzados extends AppCompatActivity
{
    private String url;
   private OkHttpClient httpClient;
   private String publishable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setPublishableKey();
        setContentView(R.layout.activity_pagos_lanzados);
        url = "http://10.0.2.2:4242/";
    }

    private void alerta(String titulo, String mensaje)
        {
          runOnUiThread(new Runnable()
          {
              @Override
              public void run()
              {
                  AlertDialog.Builder builder = new AlertDialog.Builder(PagosLanzados.this);
                  builder.setTitle(titulo)
                          .setMessage(mensaje);
                  builder.setPositiveButton("Ok",null);
                  builder.create().show();
              }
          });
        }
        private void setPublishableKey()
        {
            Request respuesta = new Request.Builder().url(url + " config").build();

            httpClient.newCall(respuesta).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    alerta("Peticion erronea","Error: " + e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    if(!response.isSuccessful())
                    {
                        alerta("Peticion erronea","Error: " + response);
                    }
                    else
                        {
                          String responseDatos = response.body().toString();
                            try
                            {
                                JSONObject objeto    = new JSONObject(responseDatos);
                                publishable = objeto.getString("publishableKey");
                                PaymentConfiguration.init(getApplicationContext(),publishable);
                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                }
            });
        }
}