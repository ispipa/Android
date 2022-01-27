package com.spania.sala17;

public class Entrada
{
    String descipción;
    int img;
    String vip;

    public Entrada(String descipción, int img, String vip)
    {
        this.descipción = descipción;
        this.img = img;
        this.vip = vip;
    }

    public String getVip()
    {
        return vip;
    }

    public String getDescipción()
    {
        return descipción;
    }

    public int getImg() {
        return img;
    }
}
