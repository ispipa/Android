package com.spania.sala17;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spania.sala17.models.Bebida;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<Bebida>bebidas;
    Context ctx;
    codigoqr codigoqrs=new codigoqr();

    public CustomAdapter(ArrayList<Bebida> bebidas, Context ctx) {
        this.bebidas = bebidas;
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return bebidas.size();
    }

    @Override
    public Object getItem(int i)
    {
        return bebidas.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //geera un view nuevo a partir del layout de "Item_pkm" generado.
        View viewinflado= LayoutInflater.from(ctx).inflate(R.layout.item_qr,null);
        TextView nombre = viewinflado.findViewById(R.id.texb);
        ImageView imgbe = viewinflado.findViewById(R.id.imgb);
        nombre.setText("0 X "+bebidas.get(i).getNombreBebida()+" = 0 ");
        Picasso.get().load(bebidas.get(i).getImgBebida()).into(imgbe);
        return viewinflado;
    }

}
