package com.example.projetmemory;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetmemory.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ActivityGameMedium extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private Chronometer chronometer;
    private int score = 0;
    private int[] allImages = {
            R.drawable.jocelyn_medium,
            R.drawable.alexis_medium,
            R.drawable.axel_medium,
            R.drawable.antoine_medium,
            R.drawable.valentin_medium,
            R.drawable.corentin_medium,
            R.drawable.arthur_medium,
            R.drawable.baptiste_medium,
            R.drawable.florian_medium,
            R.drawable.maxime_medium,
            R.drawable.raphael_medium,
            R.drawable.tom_medium,
            R.drawable.adrien_medium,
            R.drawable.alexandre_medium,
            R.drawable.andreas_medium,
            R.drawable.anthony_medium,
            R.drawable.anthony2_medium,
            R.drawable.antoine2_medium,
            R.drawable.antonin_medium,
            R.drawable.antonin2_medium,
            R.drawable.armand_medium,
            R.drawable.armand2_medium,
            R.drawable.arthur2_medium,
            R.drawable.baptiste_medium,
            R.drawable.baptiste2_medium,
            R.drawable.bastien_medium,
            R.drawable.clemence_medium,
            R.drawable.enzo_medium,
            R.drawable.etienne_medium,
            R.drawable.evan_medium,
            R.drawable.florian_medium,
            R.drawable.florian2_medium,
            R.drawable.florian3_medium,
            R.drawable.guillaume_medium,
            R.drawable.jules_medium,
            R.drawable.justin_medium,
            R.drawable.killian_medium,
            R.drawable.leo_medium,
            R.drawable.loan_medium,
            R.drawable.marin_medium,
            R.drawable.mathieu_medium,
            R.drawable.mathis_medium,
            R.drawable.maxime_medium,
            R.drawable.maxime2_medium,
            R.drawable.milaine_medium,
            R.drawable.nathan_medium,
            R.drawable.nathanael_medium,
            R.drawable.raphael_medium,
            R.drawable.romain_medium,
            R.drawable.steve_medium,
            R.drawable.theo_medium,
            R.drawable.timoth__medium,
            R.drawable.tom2_medium
    };

    private TextView scoreTextView;
    private List<Integer> cardList;
    private int[] cardArray = {R.drawable.jocelyn_medium,
            R.drawable.jocelyn_medium,
            R.drawable.alexis_medium,
            R.drawable.alexis_medium,
            R.drawable.axel_medium,
            R.drawable.axel_medium,
            R.drawable.antoine_medium,
            R.drawable.antoine_medium,
            R.drawable.valentin_medium,
            R.drawable.valentin_medium,
            R.drawable.corentin_medium,
            R.drawable.corentin_medium
    };

    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24;
    private int clickedCard1, clickedCard2;
    private int flippedCard1, flippedCard2;
    private int cardCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_medium);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialisation();
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
        scoreTextView = findViewById(R.id.textView);
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
                score++; // Incrémentez le score
                scoreTextView.setText("Score: " + score); // Mettez à jour le TextView du score

                // Vérifiez si toutes les paires ont été trouvées
                if (score == 12) {
                    // Affichez un message de félicitations
                    Toast.makeText(ActivityGameMedium.this, "Bien joué ! Vous avez gagné !", Toast.LENGTH_SHORT).show();
                }
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
                return R.id.imageButton14;
            case 1:
                return R.id.imageButton15;
            case 2:
                return R.id.imageButton16;
            case 3:
                return R.id.imageButton17;
            case 4:
                return R.id.imageButton18;
            case 5:
                return R.id.imageButton19;
            case 6:
                return R.id.imageButton20;
            case 7:
                return R.id.imageButton21;
            case 8:
                return R.id.imageButton23;
            case 9:
                return R.id.imageButton24;
            case 10:
                return R.id.imageButton25;
            case 11:
                return R.id.imageButton26;
            case 12:
                return R.id.imageButton27;
            case 13:
                return R.id.imageButton28;
            case 14:
                return R.id.imageButton29;
            case 15:
                return R.id.imageButton30;
            case 16:
                return R.id.imageButton31;
            case 17:
                return R.id.imageButton32;
            case 18:
                return R.id.imageButton33;
            case 19:
                return R.id.imageButton34;
            case 20:
                return R.id.imageButton35;
            case 21:
                return R.id.imageButton36;
            case 22:
                return R.id.imageButton37;
            case 23:
                return R.id.imageButton38;

            default:
                return 0;
        }
    }

    private void initialisation() {
        cardList = new ArrayList<>();
        Random random = new Random();
        Set<Integer> usedImages = new HashSet<>();

        while (usedImages.size() < 12) {
            int index = random.nextInt(allImages.length);
            int imageId = allImages[index];

            if (!usedImages.contains(imageId)) {
                cardList.add(imageId);
                cardList.add(imageId);

                usedImages.add(imageId);
            }
        }

        Collections.shuffle(cardList, new Random());
        List<Integer> tempImages = new ArrayList<>(cardList);
        Collections.shuffle(tempImages, random);

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = tempImages.get(i);
        }

        card1 = findViewById(R.id.imageButton14);
        card2 = findViewById(R.id.imageButton15);
        card3 = findViewById(R.id.imageButton16);
        card4 = findViewById(R.id.imageButton17);
        card5 = findViewById(R.id.imageButton18);
        card6 = findViewById(R.id.imageButton19);
        card7 = findViewById(R.id.imageButton20);
        card8 = findViewById(R.id.imageButton21);
        card9 = findViewById(R.id.imageButton23);
        card10 = findViewById(R.id.imageButton24);
        card11 = findViewById(R.id.imageButton25);
        card12 = findViewById(R.id.imageButton26);
        card13 = findViewById(R.id.imageButton27);
        card14 = findViewById(R.id.imageButton28);
        card15 = findViewById(R.id.imageButton29);
        card16= findViewById(R.id.imageButton30);
        card17 = findViewById(R.id.imageButton31);
        card18 = findViewById(R.id.imageButton32);
        card19 = findViewById(R.id.imageButton33);
        card20 = findViewById(R.id.imageButton34);
        card21 = findViewById(R.id.imageButton35);
        card22 = findViewById(R.id.imageButton36);
        card23 = findViewById(R.id.imageButton37);
        card24 = findViewById(R.id.imageButton38);

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
        card13.setTag("12");
        card14.setTag("13");
        card15.setTag("14");
        card16.setTag("15");
        card17.setTag("16");
        card18.setTag("17");
        card19.setTag("18");
        card20.setTag("19");
        card21.setTag("20");
        card22.setTag("21");
        card23.setTag("22");
        card24.setTag("23");

        for (ImageView iv : new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24}) {
            iv.setImageResource(R.drawable.dos);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cardTag = Integer.parseInt((String) v.getTag());
                    revealCard((ImageView) v, cardTag);
                    if (score == 12) {
                        resetGame();
                        Intent intent = new Intent(ActivityGameMedium.this, LeaderBoard.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    private void resetGame() {
        for (ImageView iv : new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, card21, card22, card23, card24}) {
            iv.setImageResource(R.drawable.dos);
            iv.setEnabled(true);
            iv.setVisibility(View.VISIBLE);
        }

        initialisation(); // Réinitialise les cartes

        // Réinitialise le score et met à jour le TextView
        score = 0;
        scoreTextView.setText("Score: " + score);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            boolean modeEasy = intent.getBooleanExtra("Easy",false);
            boolean modeMedium = intent.getBooleanExtra("Medium",false);
            boolean modeHard = intent.getBooleanExtra("Hard",false);

            if (modeEasy) {
                //mettre le code du jeu en 12 cartes
            }
            if(modeMedium){
                //mettre le code du jeu en 24 cartes
            }
            if(modeHard){
                //mettre le code du jeu en 48 cartes
            }
        }
    }
}