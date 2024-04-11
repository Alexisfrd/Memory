package com.example.projetmemory;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.projetmemory.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {
    private ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = findViewById(R.id.editTextName);
                RadioGroup radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);

                if (editTextName.getText().toString().trim().length() > 0 && radioGroupDifficulty.getCheckedRadioButtonId() != -1) {
                    Intent intent = new Intent(HomePage.this, MainActivityGAME.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(HomePage.this, "Veuillez entrer votre nom et choisir une difficultÃ©", Toast.LENGTH_SHORT).show();
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