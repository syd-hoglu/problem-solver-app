package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class bigPuzzleProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_puzzle_problem2);
    }
    public void toBigPuzzleMain(View view) {
        Intent bigPuzzleMain = new Intent(this, bigPuzzleMain.class);
        startActivity(bigPuzzleMain);

    }
}