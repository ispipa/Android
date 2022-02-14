package com.spania.sala17;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spania.sala17.interfaces.ConsultaApi;
import com.spania.sala17.models.Bebida;
import com.spania.sala17.models.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Copas extends AppCompatActivity {
    TextView [] calcopa=new TextView[26];
    TextView [] nomcopa=new TextView[26];
    Button []sum=new Button[26];
    Button []rest= new Button[26];
    Button pagar, pedir,comprar;
    ImageView []fotocopa= new ImageView[26];
    int total;

    String [] id={"calcopa","nomcopa","sum","rest","fotocopa"};
    String listanombre[]=new String[26];
    int listaprecio[]=new int[26];
    int resulcopa[]=new int[26];
    int cant[]=new int[26];
    int idcopa[]=new int[26];
    String img[]=new String[26];

    boolean pedirB=false;
    Long idUser;
    List<Integer> limitcant=new ArrayList<>();
    List<Integer> idcant=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copas);
        pagar = findViewById(R.id.button);
        pedir=findViewById(R.id.pedir1);
        comprar=findViewById(R.id.comprar);
        //asignacion de limites de sum y res
        comprar.setEnabled(false);

        Intent intent=getIntent();
        String aux=intent.getStringExtra("iduser");
        idUser= Long.parseLong(aux);

        autofinbyid();
        fined();
        botones();
    }
    //general----------------------------------------------------------------------------------
    private void botones(){
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirB=false;

                comprar.setEnabled(false);
                pedir.setEnabled(true);
                pagar.setText("pagar");
                for (int i = 0; i < idcopa.length; i++) {
                    calcopa[i].setText("0 x "+listaprecio[i]+" = 0");
                }
            }
        });
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fineddatos(idUser);

                pedir.setEnabled(false);
                comprar.setEnabled(true);
                pagar.setText("pedir");

            }
        });

    }
    //auto findbyid
    private void autofinbyid(){
        int temp;
        for (int i = 0; i < id.length; i++) {
            for (int k = 0; k < nomcopa.length; k++) {
                temp =getResources().getIdentifier(id[i]+k,"id",getPackageName());
                switch(i){
                    case 0:
                        calcopa[k]=findViewById(temp);
                        break;
                    case 1:
                        nomcopa[k]=findViewById(temp);
                        break;
                    case 2:
                        sum[k]=findViewById(temp);
                        break;
                    case 3:
                        rest[k]=findViewById(temp);
                        break;
                    case 4:
                        fotocopa[k]=findViewById(temp);
                        break;
                }
            }
        }
    }
    //catalogo de las bebidas y su precio de la base de datos.
    private void fined(){
        long cod;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
        for (int i = 0; i < listanombre.length; i++) {
            cod=i+1;
            //cambiar baseurl cada vez que la instacia se reinicie
            Call<Bebida> call = consultaApi.findB((cod));
            int finalI = i;
            call.enqueue(new Callback<Bebida>() {
                @Override
                public void onResponse(Call<Bebida> call, Response<Bebida> response) {
                    try {
                        if (response.isSuccessful()) {
                            Bebida bebida = response.body();
                            nomcopa[finalI].setText(bebida.getNombreBebida());
                            Picasso.get().load(bebida.getImgBebida()).into(fotocopa[finalI]);
                            img[finalI]=bebida.getImgBebida();
                            listaprecio[finalI]=bebida.getPrecio();
                            listanombre[finalI]=bebida.getNombreBebida();
                            idcopa[finalI]=(int)bebida.getId_bebida();
                            calcopa[finalI].setText("0 x "+bebida.getPrecio()+" = 0");
                        }
                    } catch (Exception ex) {
                        Toast.makeText(Copas.this, "pepe", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Bebida> call, Throwable t) {
                    Toast.makeText(Copas.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    //autobusqueda de boton y calculo suma
    public void sumar(View v){
        if (pedirB==false){
            for (int i = 0; i < sum.length; i++) {
                if(cant[i]<99){
                    if (v.getId()==getResources().getIdentifier(id[2]+i,"id",getPackageName())){
                        resulcopa[i]+=listaprecio[i];
                        cant[i]=resulcopa[i]/listaprecio[i];
                        calcopa[i].setText(cant[i]+" x "+listaprecio[i]+" = "+resulcopa[i]+"€");
                    }
                }
        }

        }else if(pedirB){
        for (int i = 0; i < limitcant.size(); i++) {
                if (limitcant.get(i)>0){
                    if((v.getId()==getResources().getIdentifier(id[2]+i,"id",getPackageName()))&&(cant[i]<limitcant.get(i))) {
                        resulcopa[i] += listaprecio[i];
                        cant[i] = resulcopa[i] / listaprecio[i];
                        calcopa[i].setText("Cantidad " + cant[i] + " / " + limitcant.get(i));

                    }
                }
            }
        }
        total();
    }

    //autobusqueda de boton y calculo resta
    public void restar(View v){
        for (int i = 0; i < rest.length; i++) {
            if ((cant[i]>0)&&(pedirB==false)){
                if (v.getId()==getResources().getIdentifier(id[3]+i,"id",getPackageName())){
                    resulcopa[i]-=listaprecio[i];
                    cant[i]=resulcopa[i]/listaprecio[i];
                    calcopa[i].setText(cant[i]+" x "+listaprecio[i]+" = "+resulcopa[i]+"€");
                }
            }
            else if(pedirB){
                if((v.getId()==getResources().getIdentifier(id[3]+i,"id",getPackageName()))&&(cant[i]>0)){

                    resulcopa[i]-=listaprecio[i];
                    cant[i]=resulcopa[i]/listaprecio[i];
                    calcopa[i].setText("Cantidad "+cant[i]+" / "+limitcant.get(i));
                }
            }
        }
        total();
    }
    //boton total con la suma total de las bebidas
    private void total()
    {
        total=0;
        for (int i = 0; i < resulcopa.length; i++)
        {
            total+=resulcopa[i];
        }
        pagar.setText("El total es de "+total+"€");
    }
    //pagar y guardar lo datos
    public void pagar(View v) {
        if (pedirB==false){
            String auxpedido = "";
            for (int k = 0; k < nomcopa.length; k++) {
                if (cant[k] >= 1) {
                    String ids = String.valueOf(k+1);
                    if (!idcant.isEmpty()){
                        if (idcant.get(k) == k) {
                            cant[k] = +limitcant.get(k);
                        }}
                    String cants = String.valueOf(cant[k]);
                    if ((ids.length() == 1) && (cants.length() < 10)) {
                        ids = "." + ids;
                    }
                    if (cants.length() == 1) {
                        cants = "." + cants;
                    }
                    auxpedido += ids;
                    auxpedido += cants;
                }
                cant[k]=0;
            }

            insertar(idUser, auxpedido);

        }else{
            pedir();
        }
    }
    //pedir bebidas en qr y pasar los datos
    private void pedir(){
        Intent i = new Intent(this,Codigoqr.class);
        for (int k = 0; k < nomcopa.length; k++) {
            if (cant[k]<=0) {
                i.putExtra("ocupado"+k,"no");
            }else{
                i.putExtra("ocupado"+k,"si");
                i.putExtra("nombre"+k, listanombre[k]);
                i.putExtra("cant"+k, String.valueOf(cant[k]));
            }
            i.putExtra("idUser", String.valueOf(idUser));
            startActivity(i);
        }

    }

    //comprar bebidas---------------------------------------------------------------------------------------------------------------------------
    //Insertar pedido en ususario
    private void insertar(long id,String pedido){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario>call=consultaApi.insertBebida(id,pedido);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Copas.this, "Comprado", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(Copas.this, "Error intentelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //seleccionar bebidas---------------------------------------------------------------------------------------------------------
    private void fineddatos(long cod)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-54-205-129-91.compute-1.amazonaws.com:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ConsultaApi consultaApi = retrofit.create(ConsultaApi.class);
        Call<Usuario> call = consultaApi.findUser(cod);
        call.enqueue(new Callback<Usuario>()
        {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if (response.isSuccessful())
                {
                    Usuario usuario= response.body();
                    String auxpedido=usuario.getPedido();
                    if (auxpedido!=null){
                        //obtener cantcopas
                        int aux=0;
                        String aux2="";
                        int aux3=0;
                        for (int j = 0; j < auxpedido.length(); j++)
                        {
                            aux++;
                            aux2+=auxpedido.charAt(j);
                            if (aux==2)
                            {
                                if (0==aux3%2)
                                {
                                    idcant.add(Integer.parseInt(aux2.replace(".","")));

                                }else
                                {
                                    limitcant.add(Integer.parseInt(aux2.replace(".","")));
                                }
                                aux3++;
                                aux=0;
                                aux2="";
                            }
                        }
                        pedirB=true;
                    }
                    sumapedir();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t)
            {
                Toast.makeText(Copas.this, "Fallo obtecion de datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sumapedir(){
            for (int i = 0; i < idcopa.length; i++) {
                for (int j = 0; j < idcant.size(); j++) {
                    if (idcopa[i]==idcant.get(j)){
                        cant[i]=0;
                            calcopa[i].setText("Cantidad "+cant[i]+" / "+limitcant.get(i));
                    }
                }
            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}