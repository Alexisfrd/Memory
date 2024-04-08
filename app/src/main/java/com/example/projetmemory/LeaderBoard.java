package com.example.projetmemory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmemory.databinding.ActivityLeaderBoardBinding;

public class LeaderBoard extends AppCompatActivity {

    private ActivityLeaderBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           binding = ActivityLeaderBoardBinding.inflate(getLayoutInflater());
           setContentView(binding.getRoot());
        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(LeaderBoard.this, HomePage.class);
                    startActivity(intent);
            }
        });
    }


}