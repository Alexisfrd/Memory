
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

        // Enregistrement du nom du joueur et de son Score
        editor.putString("namePlayer", "John");
        editor.putInt("Score", 1000);
        editor.apply();
        // Création du fragment avec les données sauvegardées


        fragScore = new ArrayList<>();
        namePlayer1 =pref.getString("namePlayer", "");
        score = String.valueOf(pref.getInt("Score", 0));

        fragScore.add(Score.newInstance(namePlayer1,score));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(Score frag : fragScore) {
            ft.add(R.id.fragment_score,frag);
        }
        ft.commit();
    }
}
