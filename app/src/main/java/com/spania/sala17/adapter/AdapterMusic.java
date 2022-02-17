package com.spania.sala17.adapter;


import static com.spania.sala17.Music.prepararMediaPlayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spania.sala17.Music;
import com.spania.sala17.R;
import com.spania.sala17.pojo.ObjetoMusica;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder>
{
    public static ArrayList<ObjetoMusica> musicaDatos;
    private Context ct;
    public static  boolean clickUsuario = false;

    public AdapterMusic(ArrayList<ObjetoMusica> musicaDatos, Context ct)
    {
        this.musicaDatos = musicaDatos;
        this.ct = ct;
    }
    /*public  void buscar(String imaCancion, String musciLink)
    {
        musicaDatos = new ArrayList<>();
        for (ObjetoMusica obj : musicaDatos)
        {
            if (obj.getImaCancion().toLowerCase().contains(imaCancion.toLowerCase()) && obj.getArtisMusicLink().toLowerCase().contains(musciLink.toLowerCase())) {
                musicaDatos.add(obj);
                Picasso.get().load(obj.getImaCancion()).into(Music.imaAlbun);
                Music.prepararMediaPlayer(obj.getArtisMusicLink());
                //Music.adapterMusic = new AdapterMusic(obj, ct);
                //Music.recyclerView.setAdapter(Music.adapterMusic);
                System.out.println(obj);
            }
        }
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_musci,parent,false);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                int itemPosition = Music.recyclerView.getChildLayoutPosition(view);
                clickUsuario = true;
                String imaMusica = String.valueOf(musicaDatos.get(itemPosition).getImaCancion());
                String duracionCancion = String.valueOf(musicaDatos.get(itemPosition).getArtisMusicLink());
                //String duracionCancion ="https://cdns-preview-b.dzcdn.net/stream/c-be4df919180127a82ca1bae8ce57c38b-6.mp3 ";
                //String duracionCancion ="spotify:track:5PycBIeabfvX3n9ILG7Vrv ";
                System.out.println(duracionCancion);
                Music.actualizarBoolean();
                if(clickUsuario)
                {
                    Picasso.get().load(imaMusica).into(Music.imaAlbun);
                    prepararMediaPlayer(duracionCancion);
                }

                Music.actualizarBoolean();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.textCancion.setText(musicaDatos.get(position).getNombreCancion());
        holder.textArtista.setText(musicaDatos.get(position).getNombreArtista());
        Picasso.get().load(musicaDatos.get(position).getImaCancion()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return musicaDatos.size();
    }

    /*ArrayList<ObjetoMusica> musica;
        Context ct;
        TextView textView ;
        ImageView imageView ;*/
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView textArtista;
        private TextView textCancion;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imaMusica);
            textArtista = itemView.findViewById(R.id.artista);
            textCancion = itemView.findViewById(R.id.nombreMusica);
        }
    }

}
