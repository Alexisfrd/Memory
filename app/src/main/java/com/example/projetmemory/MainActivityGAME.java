package com.example.projetmemory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;

public class MainActivityGAME extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ActivityMainBinding binding;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private Chronometer chronometer;
    private int score = 0;
    private int[] allImages = {
            R.drawable.jocelyn,
            R.drawable.alexis,
            R.drawable.axel,
            R.drawable.antoine,
            R.drawable.valentin,
            R.drawable.corentin,
            R.drawable.arthur,
            R.drawable.baptiste,
            R.drawable.florian,
            R.drawable.maxime,
            R.drawable.raphael,
            R.drawable.tom,
            R.drawable.adrien,
            R.drawable.alexandre,
            R.drawable.andreas,
            R.drawable.anthony,
            R.drawable.anthony2,
            R.drawable.antoine2,
            R.drawable.antonin,
            R.drawable.antonin2,
            R.drawable.armand,
            R.drawable.armand2,
            R.drawable.arthur2,
            R.drawable.baptiste,
            R.drawable.baptiste2,
            R.drawable.bastien,
            R.drawable.clemence,
            R.drawable.enzo,
            R.drawable.etienne,
            R.drawable.evan,
            R.drawable.florian,
            R.drawable.florian2,
            R.drawable.florian3,
            R.drawable.guillaume,
            R.drawable.jules,
            R.drawable.justin,
            R.drawable.killian,
            R.drawable.leo,
            R.drawable.loan,
            R.drawable.marin,
            R.drawable.mathieu,
            R.drawable.mathis,
            R.drawable.maxime,
            R.drawable.maxime2,
            R.drawable.milaine,
            R.drawable.nathan,
            R.drawable.nathanael,
            R.drawable.raphael,
            R.drawable.romain,
            R.drawable.steve,
            R.drawable.theo,
            R.drawable.timothe,
            R.drawable.tom2
    };

    private TextView scoreTextView;
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

    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12;
    private int clickedCard1, clickedCard2;
    private int flippedCard1, flippedCard2;
    private int cardCounter = 0;

    private long elapsedMillis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();

                // Convertit les millisecondes en secondes
                int seconds = (int) (elapsedMillis / 1000);

                // Met à jour le TextView avec le temps écoulé en secondes
                //timerTextView.setText("Temps écoulé: " + seconds + " secondes");
            }
        });
        scoreTextView = findViewById(R.id.textView);

        // Récupérer l'intent qui a démarré cette activité
        Intent intent = getIntent();

        // Récupérer l'objet Donnees à partir de l'intent
        Donnees donnees = (Donnees) intent.getSerializableExtra("donnees");

        // Vérifier si l'objet Donnees est null
        if (donnees != null) {
            // Récupérer le pseudo du joueur à partir de l'objet Donnees
            String pseudo = donnees.getName();

            // Récupérer le TextView textPseudo
            TextView textPseudo = findViewById(R.id.textPseudo);

            // Afficher le pseudo dans le TextView
            textPseudo.setText(pseudo);
        } else {
            // Gérer le cas où l'objet Donnees est null
            // Par exemple, afficher un message d'erreur ou définir un pseudo par défaut
        }
        pref = getSharedPreferences("playerData", MODE_PRIVATE);
        editor = pref.edit();
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
                score++; // Incrémentez le Score
                scoreTextView.setText("Score: " + score); // Mettez à jour le TextView du Score

                // Vérifiez si toutes les paires ont été trouvées
                if (score == 6) {
                    // Affichez un message de félicitations
                    Toast.makeText(MainActivityGAME.this, "Bien joué ! Vous avez gagné !", Toast.LENGTH_SHORT).show();
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
        Random random = new Random();
        Set<Integer> usedImages = new HashSet<>();

        while (usedImages.size() < 6) {
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
                    if (score == 6) {

                        // sauvegarde du score et du pseudo du joueur dans les SharedPreferences
                        // sauvegarde du score et du pseudo du joueur dans les SharedPreferences

                        editor.putString(binding.textPseudo.getText().toString() + "_name", binding.textPseudo.getText().toString());
                        editor.putInt(binding.textPseudo.getText().toString() + "_score", (int) elapsedMillis);
                        editor.apply();


                        /*
                        editor.putString("namePlayer", binding.textPseudo.getText().toString());
                        editor.putInt("Score", binding.chrono.getText().toString().length());
                        editor.apply();


                         */
                        resetGame();

                        Intent intent = new Intent(MainActivityGAME.this, LeaderBoard.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    private void resetGame() {
        for (ImageView iv : new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12}) {
            iv.setImageResource(R.drawable.dos);
            iv.setEnabled(true);
            iv.setVisibility(View.VISIBLE);
        }

        initialisation(); // Réinitialise les cartes

        // Réinitialise le Score et met à jour le TextView
        score = 0;
        scoreTextView.setText("Score: " + score);
    }

}