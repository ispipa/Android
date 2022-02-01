package com.spania.sala17.interfaces;



import com.spania.sala17.models.Bebida;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConsultaApi {
    @GET("bebida/idBebida/{idBebida}")
    public Call<Bebida> find(@Path("idBebida")Long idBebida);


}
