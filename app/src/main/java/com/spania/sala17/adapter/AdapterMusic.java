package com.spania.sala17.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spania.sala17.R;
import com.spania.sala17.pojo.ObjetoMusica;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.MiViewHolder>
{
    ArrayList<ObjetoMusica> musica;
    Context ct;

    public AdapterMusic(ArrayList<ObjetoMusica> musica, Context ct)
    {
        this.musica = musica;
        this.ct = ct;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(ct).inflate(R.layout.activity_music,null);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMusic.MiViewHolder holder, int position)
    {
        TextView textView = holder.itemView.findViewById(R.id.datosMusica);
        ImageView imageView = holder.itemView.findViewById(R.id.imaMusica);
        textView.setText(
                musica.get(position).getNombreCancion() +
                        "\n"+
                 musica.get(position).getNombreArtista());
        Picasso.get().load(musica.get(position).getUrlCancion()).into(imageView);
    }

    @Override
    public int getItemCount()
    {
        return musica.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder
    {
        public MiViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
