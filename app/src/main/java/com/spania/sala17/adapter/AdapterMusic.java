package com.spania.sala17.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spania.sala17.R;
import com.spania.sala17.pojo.MusicaObjeto;

import java.util.List;

public class AdapterMusic  extends RecyclerView.Adapter<AdapterMusic.viewholdermusicaobjeto>
{
    List<MusicaObjeto> musicaList;

    public AdapterMusic(List<MusicaObjeto> musicaList)
    {
        this.musicaList = musicaList;
    }

    @NonNull
    @Override
    public viewholdermusicaobjeto onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_music,parent,false);
        viewholdermusicaobjeto holder = new viewholdermusicaobjeto(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdermusicaobjeto holder, int position)
    {
        MusicaObjeto musicaObjeto = musicaList.get(position);
        holder.nombreCancion.setText(musicaObjeto.getArtistAlbun());
        holder.nombreCancion.setText(musicaObjeto.getArtist());
    }

    @Override
    public int getItemCount()
    {
        return musicaList.size();
    }

    public class viewholdermusicaobjeto extends RecyclerView.ViewHolder
    {
        TextView nombreCancion,nombreArtista;
        public viewholdermusicaobjeto(@NonNull View itemView)
        {
            super(itemView);
            nombreCancion = itemView.findViewById(R.id.nombreCancion);
            nombreArtista = itemView.findViewById(R.id.nombreArtista);
        }
    }
}
