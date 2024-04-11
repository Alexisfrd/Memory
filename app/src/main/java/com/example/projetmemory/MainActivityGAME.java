package com.example.projetmemory;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmemory.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityGAME extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private Chronometer chronometer;

    //PARTIE D'AXEL(JEU)
    private List<Integer> cardList;
    private int[] cardArray = {R.drawable.jocelyn,
            R.drawable.jocelyn,
            R.drawable.alexis,
            R.drawable.alexis,
            R.drawable.axel,
            R.drawable.axel,
            R.drawable.antoine,
            R.drawable.antoine,
            R.drawable.valentin,
            R.drawable.valentin,
            R.drawable.corentin,
            R.drawable.corentin
    };

    //Partie Alexis
    private TextView scoreTextView;
    private int score = 0;
    private int[] allImages = {
            R.drawable.jocelyn,
            R.drawable.alexis,
            R.drawable.axel,
            R.drawable.antoine,
            R.drawable.valentin,
            R.drawable.corentin
    };

    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12;
    private int clickedCard1, clickedCard2;
    private int flippedCard1, flippedCard2;
    private int cardCounter = 0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.button), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        final float scale = getResources().getDisplayMetrics().density;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthMax = (int) (metrics.widthPixels / scale);
        int heightMax = (int) (metrics.heightPixels / scale);


        //PARTIE AXEL JEU
        initialisation();

        Button restartButton = findViewById(R.id.button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score == 6) {
                    resetGame(); // Appel à la méthode de réinitialisation du jeu
                }
            }
        });
        //Moi
        chronometer = findViewById(R.id.chrono);

        //timerTextView = findViewById(R.id.timer_textView);

        //Démarre le chronomètre
        chronometer.start();
        // Écoute les événements de chronomètre pour mettre à jour le TextView
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                // Obtient le temps écoulé en millisecondes
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();

                // Convertit les millisecondes en secondes
                int seconds = (int) (elapsedMillis / 1000);

                // Met à jour le TextView avec le temps écoulé en secondes
                //timerTextView.setText("Temps écoulé: " + seconds + " secondes");
            }
        });


    }

    private void revealCard(ImageView iv, int cardTag) {
        if (!iv.isEnabled()) {
            return; // Ne rien faire si la carte est déjà retournée
        }

        if (cardCounter == 0) {
            clickedCard1 = cardArray[cardTag];
            flippedCard1 = cardTag;
            iv.setImageResource(cardArray[cardTag]);
            iv.setEnabled(false);
            cardCounter = 1;
        } else if (cardCounter == 1) {
            clickedCard2 = cardArray[cardTag];
            flippedCard2 = cardTag;
            iv.setImageResource(cardArray[cardTag]);
            iv.setEnabled(false);

            if (clickedCard1 == clickedCard2) {
                iv.setVisibility(View.INVISIBLE);
                findViewById(getIdFromTag(flippedCard1)).setVisibility(View.INVISIBLE);
            } else {
                final ImageView iv1 = findViewById(getIdFromTag(flippedCard1));
                final ImageView iv2 = findViewById(getIdFromTag(flippedCard2));
                iv1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv1.setImageResource(R.drawable.dos);
                        iv1.setEnabled(true);
                        iv2.setImageResource(R.drawable.dos);
                        iv2.setEnabled(true);
                    }
                }, 1000); // Retourner les cartes après 1 seconde
            }

            cardCounter = 0;
        }
    }

    private int getIdFromTag(int tag) {
        switch (tag) {
            case 0:
                return R.id.imageButton2;
            case 1:
                return R.id.imageButton3;
            case 2:
                return R.id.imageButton4;
            case 3:
                return R.id.imageButton5;
            case 4:
                return R.id.imageButton6;
            case 5:
                return R.id.imageButton7;
            case 6:
                return R.id.imageButton8;
            case 7:
                return R.id.imageButton9;
            case 8:
                return R.id.imageButton10;
            case 9:
                return R.id.imageButton11;
            case 10:
                return R.id.imageButton12;
            case 11:
                return R.id.imageButton13;
            default:
                return 0;
        }
    }

    private void initialisation() {
        cardList = new ArrayList<>();
        cardList.add(0);
        cardList.add(1);
        cardList.add(2);
        cardList.add(3);
        cardList.add(4);
        cardList.add(5);
        cardList.add(6);
        cardList.add(7);
        cardList.add(8);
        cardList.add(9);
        cardList.add(10);
        cardList.add(11);

        Collections.shuffle(cardList, new Random());

        List<Integer> tempImages = new ArrayList<>();
        int[] imageCounts = new int[cardArray.length];

        for (Integer index : cardList) {
            if (imageCounts[index] < 2) {
                tempImages.add(cardArray[index]); // Ajoutez chaque image une fois
                imageCounts[index]++;
            }
        }

        // Mélangez la liste temporaire
        Collections.shuffle(tempImages);

        // Remplissez cardArray avec les images mélangées
        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = tempImages.get(i);
        }

        card1 = findViewById(R.id.imageButton2);
        card2 = findViewById(R.id.imageButton3);
        card3 = findViewById(R.id.imageButton4);
        card4 = findViewById(R.id.imageButton5);
        card5 = findViewById(R.id.imageButton6);
        card6 = findViewById(R.id.imageButton7);
        card7 = findViewById(R.id.imageButton8);
        card8 = findViewById(R.id.imageButton9);
        card9 = findViewById(R.id.imageButton10);
        card10 = findViewById(R.id.imageButton11);
        card11 = findViewById(R.id.imageButton12);
        card12 = findViewById(R.id.imageButton13);

        card1.setTag("0");
        card2.setTag("1");
        card3.setTag("2");
        card4.setTag("3");
        card5.setTag("4");
        card6.setTag("5");
        card7.setTag("6");
        card8.setTag("7");
        card9.setTag("8");
        card10.setTag("9");
        card11.setTag("10");
        card12.setTag("11");

        for (ImageView iv : new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12}) {
            iv.setImageResource(R.drawable.dos);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cardTag = Integer.parseInt((String) v.getTag());
                    revealCard((ImageView) v, cardTag);
                }
            });
        }
    }
    private void resetGame() {
        for (ImageView iv : new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12}) {
            iv.setImageResource(R.drawable.dos);
            iv.setEnabled(true);
            iv.setVisibility(VISIBLE); // Assurez-vous que la carte est visible
        }

        initialisation(); // Réinitialise les cartes

        // Réinitialise le score et met à jour le TextView
        score = 0;
        scoreTextView.setText("Score: " + score);
    }
}

    //CACAVAL
