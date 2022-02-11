package com.spania.sala17.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evento
{
    @SerializedName("idEvento")
    @Expose
    private long idEvento;

    @SerializedName("nombreEvento")
    @Expose
    private String nombreEvento;

    @SerializedName("usuario")
    @Expose
    private long usuario;

    public long getIdEvento()
    {
        return idEvento;
    }

    public void setIdEvento(long idEvento)
    {
        this.idEvento = idEvento;
    }

    public String getNombreEvento()
    {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento)
    {
        this.nombreEvento = nombreEvento;
    }

    public long getUsuario()
    {
        return usuario;
    }

    public void setUsuario(long usuario)
    {
        this.usuario = usuario;
    }
}
