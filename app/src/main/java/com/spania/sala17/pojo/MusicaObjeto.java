package com.spania.sala17.pojo;

public class MusicaObjeto
{
    private String artist;
    private String artistAlbun;
    private String urlMusic;

    public MusicaObjeto(String artist) {
        this.artist = artist;
        this.artistAlbun = artistAlbun;
        this.urlMusic = urlMusic;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtistAlbun() {
        return artistAlbun;
    }

    public void setArtistAlbun(String artistAlbun) {
        this.artistAlbun = artistAlbun;
    }

    public String getUrlMusic() {
        return urlMusic;
    }

    public void setUrlMusic(String urlMusic) {
        this.urlMusic = urlMusic;
    }
}
