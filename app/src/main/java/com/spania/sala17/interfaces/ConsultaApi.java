package com.spania.sala17.interfaces;



import com.spania.sala17.models.Bebida;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConsultaApi {
    @GET("bebida/bebida_id/{id_bebida}")
    public Call<Bebida> find(@Path("id_bebida")Long id_bebida);

}
