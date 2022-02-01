package com.spania.sala17.interfaces;



import com.spania.sala17.models.Bebida;
import com.spania.sala17.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConsultaApi {
    @GET("bebida/idBebida/{idBebida}")
    public Call<Bebida> findB(@Path("idBebida")Long idBebida);

    @GET("usuario/idUsuario/{idUsuario}")
    public Call<Usuario> findUser(@Path("idUsuario")Long idUsuario);


    @FormUrlEncoded
    @PUT("usuario/pedido/{pedido}")
    Call<Usuario> insertarbebida(@Path("id")Long id, @Field("pedido") String pedido);




}
