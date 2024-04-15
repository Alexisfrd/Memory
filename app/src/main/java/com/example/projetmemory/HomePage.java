package com.example.projetmemory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.projetmemory.databinding.ActivityHomepageBinding;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    private ActivityHomepageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*binding.buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextName = findViewById(R.id.editTextName);
                RadioGroup radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);

                if (editTextName.getText().toString().trim().length() > 0 && radioGroupDifficulty.getCheckedRadioButtonId() != -1) {
                    if (binding.radioButtonEasy.isChecked()) {
                        Intent intent = new Intent(HomePage.this, MainActivityGAME.class);
                        intent.putExtra("Easy", binding.radioButtonEasy.isChecked());
                        sendBroadcast(intent);
                    } else if (binding.radioButtonMedium.isChecked()) {
                        Intent intent = new Intent(HomePage.this, ActivityGameMedium.class);
                        intent.putExtra("Medium", binding.radioButtonMedium.isChecked());
                        sendBroadcast(intent);
                    } else binding.radioButtonHard.isChecked();
                    {
                        Intent intent = new Intent(HomePage.this, ActivityGameHard.class);
                        intent.putExtra("Hard", binding.radioButtonMedium.isChecked());
                        sendBroadcast(intent);
                    }

                } else {
                    Toast.makeText(HomePage.this, "Veuillez entrer votre nom et choisir une difficultÃ©", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        binding.buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = findViewById(R.id.pseudo);
                RadioGroup radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
                RadioButton radioButtonEasy = findViewById(R.id.radioButtonEasy);
                RadioButton radioButtonMedium = findViewById(R.id.radioButtonMedium);
                RadioButton radioButtonHard = findViewById(R.id.radioButtonHard);

                Donnees donnees = new Donnees(binding.pseudo.getText().toString());


                if (editTextName.getText().toString().trim().length() > 0 && radioButtonEasy.isChecked()) {
                    Intent intent = new Intent(HomePage.this, MainActivityGAME.class);
                    intent.putExtra("donnees", donnees);
                    startActivity(intent);
                } else if(editTextName.getText().toString().trim().length() > 0 && radioButtonHard.isChecked()){
                    Intent intent = new Intent(HomePage.this,ActivityGameMedium.class);
                    intent.putExtra("donnees", donnees);
                    startActivity(intent);
                } else if(editTextName.getText().toString().trim().length() > 0 && radioButtonMedium.isChecked()){
                    Intent intent = new Intent(HomePage.this,ActivityGameHard.class);
                    intent.putExtra("donnees", donnees);
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

    @Override
    protected void onResume() {
        super.onResume();
        binding.radioButtonEasy.setOnClickListener(this);
        binding.radioButtonMedium.setOnClickListener(this);
        binding.radioButtonHard.setOnClickListener( this);

    }


    @Override
    public void onClick(View view) {

    }
}
