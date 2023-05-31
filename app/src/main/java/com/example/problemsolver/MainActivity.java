package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toFarmer(View view){
        Intent farmer = new Intent(this, farmerProblem.class);
        startActivity(farmer);
    }
    public void toPuzzle(View view){
        Intent puzzle = new Intent(this, puzzleProblem.class);
        startActivity(puzzle);

    }
    public void toBigPuzzle(View view){
        Intent bigPuzzle = new Intent(this, bigPuzzleProblem.class);
        startActivity(bigPuzzle);

    }
}