package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class puzzleProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_problem);
    }
    public void toPuzzleMain(View view) {
        Intent puzzleMain = new Intent(this, puzzleMain.class);
        startActivity(puzzleMain);

    }
}