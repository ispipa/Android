package com.spania.sala17;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{
    ArrayList<Entrada> entradas;
    Context ctx;


    public CustomAdapter(ArrayList<Entrada> entradas, Context ctx)
    {
        this.entradas = entradas;
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return entradas.size();
    }

    @Override
    public Entrada getItem(int i)
    {
        return entradas.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //recogidaDatos

        View viewInflado = LayoutInflater.from(ctx).inflate(R.layout.item_entrada, null);

        TextView txtNombre = viewInflado.findViewById(R.id.entrada);
        txtNombre.setText(entradas.get(i).descipción);
        ImageView imagen = viewInflado.findViewById(R.id.imagen);
        imagen.setImageResource(entradas.get(i).getImg());
        TextView txtVip = viewInflado.findViewById(R.id.vip);
        txtVip.setText(entradas.get(i).vip);

        return viewInflado;
    }
}
