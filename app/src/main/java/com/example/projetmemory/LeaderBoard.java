
package com.example.projetmemory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.projetmemory.databinding.ActivityLeaderBoardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderBoard extends AppCompatActivity {

    private ActivityLeaderBoardBinding binding;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private List<Score> fragScore;

    private String namePlayer1;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialisation des SharedPreferences
        pref = getSharedPreferences("playerData", MODE_PRIVATE);
        editor = pref.edit();

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderBoard.this, HomePage.class);
                startActivity(intent);
            }
        });


        // Création du fragment avec les données sauvegardées

/*
        fragScore = new ArrayList<>();
        fragScore.add(Score.newInstance("nom1", "score1"));
        fragScore.add(Score.newInstance("nom2", "score2"));
        fragScore.add(Score.newInstance("nom3", "score3"));
        fragScore.add(Score.newInstance("nom4", "score4"));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Score frag : fragScore) {
            ft.add(R.id.fragment_score, frag);
        }
        ft.commit();

 */

        // Récupérez toutes les entrées des SharedPreferences
        Map<String, ?> allEntries = pref.getAll();

// Créez une nouvelle liste pour stocker vos fragments
        fragScore = new ArrayList<>();

// Parcourez chaque entrée
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Vérifiez si l'entrée est un nom
            if (entry.getKey().endsWith("_name")) {
                // Récupérez le nom
                String name = (String) entry.getValue();

                // Récupérez le score correspondant
                int score = pref.getInt(name + "_score", 0);

                // Créez un nouveau fragment avec le nom et le score
                Score scoreFragment = Score.newInstance(name, String.valueOf(score));

                // Ajoutez le fragment à votre liste
                fragScore.add(scoreFragment);
            }
        }

// Commencez une nouvelle transaction de fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

// Ajoutez chaque fragment de votre liste à la transaction
        for (Score frag : fragScore) {
            ft.add(R.id.fragment_score, frag);
        }

// Validez la transaction
        ft.commit();

    }
}