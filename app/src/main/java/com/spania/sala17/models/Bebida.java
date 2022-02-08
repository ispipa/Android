package com.spania.sala17.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bebida {
    @SerializedName("idBebida")
    @Expose
    private long idBebida;
    @SerializedName("nombreBebida")
    @Expose
    private String nombreBebida;
    @SerializedName("precio")
    @Expose
    private int precio;

    @SerializedName("imgBebida")
    @Expose
    private String imgBebida;


    public long getId_bebida() {
        return idBebida;
    }

    public void setId_bebida(long id_bebida) {
        this.idBebida = id_bebida;
    }

    public String getNombreBebida() {
        return nombreBebida;
    }

    public void setNombreBebida(String nombreBebida) {
        this.nombreBebida = nombreBebida;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImgBebida() {
        return imgBebida;
    }

    public void setImgBebida(String imgBebida) {
        this.imgBebida = imgBebida;
    }


}
