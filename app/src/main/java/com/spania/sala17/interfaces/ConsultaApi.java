package com.spania.sala17.interfaces;



import com.spania.sala17.models.Bebida;
import com.spania.sala17.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConsultaApi {
    @Headers("user-key: dXNlcjoxMjM0")
    @GET("bebida/idBebida/{idBebida}")
    public Call<Bebida> findB(@Path("idBebida")Long idBebida);

    @GET("usuario/idUsuario/{idUsuario}")
    public Call<Usuario> findUser(@Path("idUsuario")Long idUsuario);

    @FormUrlEncoded
    @PUT("usuario/idUsuarioPedido/{idUsuario}")
    Call<Usuario> insertBebida(@Path("idUsuario")Long idUsuario,@Field("pedido")String pedido);

    @POST("usuario")
    Call<Usuario>insertarDatosUusario(@Field("nombreUsuario")String nombreUsuario,
                                      @Field("apellidoUsuario")String apellidoUsuario,
                                      @Field("passUsuario")String passUsuario,
                                      @Field("emailUsuario")String emailUsuario,
                                      @Field("telefonoUsuario")String telefonoUsuario,
                                      @Field("generoUsuario")String generoUsuario);




    @POST("evento")
    Call<Evento> insertarEvento(@Field("nombreEvento") String nombreEvento, @Field("usuario") long usuario);
}
