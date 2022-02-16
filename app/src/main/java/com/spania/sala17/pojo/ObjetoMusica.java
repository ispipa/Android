package com.spania.sala17.pojo;

public class ObjetoMusica
{
    String nombreCancion;
    String nombreArtista;
    String urlCancion;

    public ObjetoMusica(String nombreCancion, String nombreArtista, String urlCancion)
    {
        this.nombreCancion = nombreCancion;
        this.nombreArtista = nombreArtista;
        this.urlCancion = urlCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getUrlCancion() {
        return urlCancion;
    }

    public void setUrlCancion(String urlCancion) {
        this.urlCancion = urlCancion;
    }
}
