package com.spania.sala17;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.daprlabs.cardstack.SwipeDeck;
import com.spania.sala17.CourseModal;
import com.spania.sala17.DeckAdapter;
import com.spania.sala17.R;

import java.util.ArrayList;

public class Tinder extends AppCompatActivity {
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    static final String CHANNEL_ID = "canal";
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FE3C72")));

        // on below line we are initializing our array list and swipe deck.
        courseModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        courseModalArrayList.add(new CourseModal("Ana", "24 años", "Soltera", "Sobre mí: Me gusta cantar y hablar, chicos con el pelo desordenado, leer el diario los domingos por la mañana, y las voces divertidas.", R.drawable.chica_1));
        courseModalArrayList.add(new CourseModal("Susana", "26 años", "Es complicado", "Todas las chicas felices son iguales; cada chica infeliz es infeliz a su manera. Mi tipo de infeliz está lleno de humor de autoestima, doble IPAs, y en realidad es bastante radical.\n" +
                "Avísenme si quieren ser miserables juntos.", R.drawable.chica_2));
        courseModalArrayList.add(new CourseModal("Sandra", "28 años", "Soltera", "Vivo toda mi vida creando situaciones que eventualmente me llevarán a la frase «Y luego las risas siguieron…»; Esperando que ésta también funcione.", R.drawable.chica_3));
        courseModalArrayList.add(new CourseModal("Lucia", "27 años", "En una relación", "Soy muy importante. Tengo muchos libros encuadernados en cuero y mi apartamento huele a caoba rica. ¿A qué huele tu apartamento?", R.drawable.chica_4));
        courseModalArrayList.add(new CourseModal("Alexia", "26 años", "Soltera", "Deberías saber que odio a los ancianos, a los niños y a los perros;\n" +
                "En realidad, me encantan todas esas cosas, pero si sabes de qué película es esa frase, deberíamos salir.", R.drawable.chica_5));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                //Toast.makeText(Tinder.this, "Dislike", Toast.LENGTH_SHORT).show();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void cardSwipedRight(int position) {
                NotificationChannel channel = null;
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(Tinder.this, courseModalArrayList.get(position).getCourseName(), Toast.LENGTH_SHORT).show();
                channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.chica_5)
                        .setContentTitle("Sala17")
                        .setContentText("Has enviado una copa a @"+courseModalArrayList.get(position).getCourseName() )
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(1, builder.build());


            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(Tinder.this, "No more courses present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.tinder:
                Toast.makeText(Tinder.this,"Entrando a tinder",Toast.LENGTH_SHORT).show();
                Intent t = new Intent(this, Tinder.class);
                startActivity(t);
                break;
            case R.id.copas:
                Intent me = new Intent(this, MainActivity.class);
                Toast.makeText(Tinder.this, "Entrando a copas", Toast.LENGTH_SHORT).show();
                startActivity(me);
                break;
            case R.id.eventos:
                Intent e = new Intent(this,Eventos.class);
                startActivity(e);
                Toast.makeText(Tinder.this,"Entrando a eventos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                Intent m = new Intent(this,Music.class);
                startActivity(m);
                Toast.makeText(Tinder.this, "Entrando a Spotify",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(Tinder.this,"Reinicie la aplicacion", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
