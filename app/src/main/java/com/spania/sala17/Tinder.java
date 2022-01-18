package com.spania.sala17;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

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
                Toast.makeText(Tinder.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(Tinder.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();
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
}
