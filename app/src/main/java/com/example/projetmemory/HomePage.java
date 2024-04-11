package com.example.projetmemory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmemory.databinding.ActivityHomepageBinding;

public class HomePage extends AppCompatActivity {
    private ActivityHomepageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = findViewById(R.id.editTextName);
                RadioGroup radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);

                if (editTextName.getText().toString().trim().length() > 0 && radioGroupDifficulty.getCheckedRadioButtonId() != -1) {
                    Intent intent = new Intent(HomePage.this, MainActivity.class);
                    intent.putExtra("playerName", editTextName.getText().toString()); // Ajoutez le nom du joueur à l'intent
                    startActivity(intent);
                } else {
                    Toast.makeText(HomePage.this, "Veuillez entrer votre nom et choisir une difficulté", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.buttonLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(HomePage.this, LeaderBoard.class);
                    startActivity(intent);
            }
        });

    }

}
