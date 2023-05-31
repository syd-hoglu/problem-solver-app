package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class farmerProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_problem);
    }

    public void toFarmerMain(View view) {
        Intent farmerMain = new Intent(this, farmerMain.class);
        startActivity(farmerMain);

    }
}