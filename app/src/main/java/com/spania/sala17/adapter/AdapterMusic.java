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
                //String duracionCancion ="https://e-cdnt-proxy-8.dzcdn.net/media/1/0cda1a4b29a06a23250505d6e2d3afda2c06d4597a6d5af2f65698029b0638e688f5eb72d73d368e91e8a81f308b5b3c36572dccd980f483665bae364dd931194495b12927116b767c35c6bb84ab52cc?sJJXmAgP0r-CnTPyB7P0qv3vDhGENNsj8g7b8SOwinbufjqlEOB1pPZWPsoczL9HDqkT4K1b2wsAV_rzNTHMqxV9ZiS3juffN00aX6iZvap4Q1CMF7UO2UtCaBlZ_GnnLL7QFegSfmiPc6fJ_T910FADp9DHm73mepc8rDrA69Fc5X2zspKGzEAYXaWzT8LBpaQLYqtXL01K9uaP5UvkjMeE0x4rwXOwVXwpR5JBtWTPewWf8TdipYrESPcrZb9k2ZuuwmN1J193MK2w4x_QH8ZAxqG0zdnoQDELlTp3l7bt9-oM5o9xa3s64wYUf4Y5g4vM-StTzFvIdQ/;stream.mp3";
                //String duracionCancion ="https://e-cdnt-proxy-8.dzcdn.net/media/1/0cda1a4b29a06a23250505d6e2d3afda2c06d4597a6d5af2f65698029b0638e688f5eb72d73d368e91e8a81f308b5b3c36572dccd980f483665bae364dd931194495b12927116b767c35c6bb84ab52cc?sJJXmAgP0r-CnTPyB7P0qv3vDhGENNsj8g7b8SOwinbufjqlEOB1pPZWPsoczL9HDqkT4K1b2wsAV_rzNTHMqxV9ZiS3juffN00aX6iZvap4Q1CMF7UO2UtCaBlZ_GnnLL7QFegSfmiPc6fJ_T910FADp9DHm73mepc8rDrA69Fc5X2zspKGzEAYXaWzT8LBpaQLYqtXL01K9uaP5UvkjMeE0x4rwXOwVXwpR5JBtWTPewWf8TdipYrESPcrZb9k2ZuuwmN1J193MK2w4x_QH8ZAxqG0zdnoQDELlTp3l7bt9-oM5o9xa3s64wYUf4Y5g4vM-StTzFvIdQ ";
                System.out.println(duracionCancion);
                Music.actualizarBoolean();
                if(clickUsuario)
                {
                    Picasso.get().load(imaMusica).into(Music.imaAlbun);
                    prepararMediaPlayer(duracionCancion);
                    Music.imaAlbun.setVisibility(View.VISIBLE);
                    Music.seekBar.setVisibility(View.VISIBLE);
                    Music.inicioCancion.setVisibility(View.VISIBLE);
                    Music.finalCancion.setVisibility(View.VISIBLE);
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
