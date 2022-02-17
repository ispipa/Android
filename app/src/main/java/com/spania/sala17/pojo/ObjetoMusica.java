package com.spania.sala17.pojo;

import java.util.ArrayList;

public class ObjetoMusica extends ArrayList<ObjetoMusica> {
    String nombreCancion;
    String nombreArtista;
    String imaCancion;
    String artisMusicLink;

    public ObjetoMusica(String nombreCancion, String nombreArtista, String imaCancion, String artisMusicLink)
    {
        this.nombreCancion = nombreCancion;
        this.nombreArtista = nombreArtista;
        this.imaCancion = imaCancion;
        this.artisMusicLink = artisMusicLink;
    }

    public ObjetoMusica() {
    }

    public String getNombreCancion() {
        return nombreCancion;
    }


    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getImaCancion() {
        return imaCancion;
    }

    /*public void setImaCancion(String imaCancion) {
        this.imaCancion = imaCancion;
    }*/

    public String getArtisMusicLink() {
        return artisMusicLink;
    }

    /*public void setArtisMusicLink(String artisMusicLink) {
        this.artisMusicLink = artisMusicLink;
    }*/

    @Override
    public String toString()
    {
        return "ObjetoMusica{" +
                "nombreCancion='" + nombreCancion + '\'' +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", imaCancion='" + imaCancion + '\'' +
                ", artisMusicLink='" + artisMusicLink + '\'' +
                '}';
    }
}
