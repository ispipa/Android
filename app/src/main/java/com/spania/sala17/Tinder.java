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
        courseModalArrayList.add(new CourseModal("C++", "30 days", "20 Tracks", "C++ Self Paced Course", R.drawable.cub));
        courseModalArrayList.add(new CourseModal("Java", "30 days", "20 Tracks", "Java Self Paced Course", R.drawable.cub));
        courseModalArrayList.add(new CourseModal("Python", "30 days", "20 Tracks", "Python Self Paced Course", R.drawable.cub));
        courseModalArrayList.add(new CourseModal("DSA", "30 days", "20 Tracks", "DSA Self Paced Course", R.drawable.cub));
        courseModalArrayList.add(new CourseModal("PHP", "30 days", "20 Tracks", "PHP Self Paced Course", R.drawable.cub));

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
