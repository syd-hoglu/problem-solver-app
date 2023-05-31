package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.problemsolver.domains.bigpuzzle.BigPuzzleProblem;
import com.example.problemsolver.framework.graph.Vertex;
import com.example.problemsolver.framework.problem.Benchmark;
import com.example.problemsolver.framework.problem.Problem;
import com.example.problemsolver.framework.problem.State;
import com.example.problemsolver.framework.solution.AStarSolver;
import com.example.problemsolver.framework.solution.SolvingAssistant;

import java.util.LinkedList;

public class bigPuzzleMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_puzzle_main);
        Problem problem = new BigPuzzleProblem();
        SolvingAssistant solvingAssistant = new SolvingAssistant(problem);
        TextView startView = (TextView) findViewById(R.id.puzzle15_solve_start);
        startView.setText(problem.getCurrentState().toString());
        TextView moveCount = (TextView) findViewById(R.id.puzzle15_moves_count);
        moveCount.setText("0");
        LinkedList<Button> buttonList = new LinkedList();
        TextView messageView = (TextView) findViewById(R.id.puzzle15_message);
        Button resetButton = (Button) findViewById(R.id.puzzle15_reset);
        resetButton.setPadding(5, 5, 5, 5);
        resetButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        Button solveButton = (Button) findViewById(R.id.puzzle15_solve);
        solveButton.setPadding(5, 5, 5, 5);
        solveButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        Button nextButton = (Button) findViewById(R.id.puzzle15_next);
        nextButton.setPadding(5, 5, 5, 5);
        nextButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        nextButton.setEnabled(false);
        Spinner benches = (Spinner) findViewById(R.id.puzzle15_benchmarks);
        AStarSolver aStar = new AStarSolver(problem);
        TextView stats = (TextView) findViewById(R.id.puzzle15_stats);
        String[] nameArray = new String[problem.getBenchmarks().size()];
        for (int i = 0; i < problem.getBenchmarks().size(); i++){
            nameArray[i] = problem.getBenchmarks().get(i).toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(benches.getContext(), android.R.layout.simple_spinner_dropdown_item, nameArray);
        benches.setAdapter(adapter);
        resetButton.setOnClickListener(view -> {
            solvingAssistant.reset(); //resets the state
            startView.setText(problem.getCurrentState().toString()); //resets the visual
            solveButton.setEnabled(true);
            nextButton.setEnabled(false);
            moveCount.setText("0");//resets move count
            messageView.setText(" ");
            stats.setText(" ");
            benches.setEnabled(true);
        });
        solveButton.setOnClickListener(f -> {
            solveButton.setEnabled(false);
            nextButton.setEnabled(true);
            benches.setEnabled(false);
            aStar.solve();
            aStar.getSolution().next();
            stats.setText(aStar.getStatistics().toString());
        });
        nextButton.setOnClickListener(g -> {
            String newMove = "";
            State prev = problem.getInitialState();
            if (aStar.getSolution().hasNext()) {
                Vertex nextVert = aStar.getSolution().next();
                Vertex pred = nextVert.getPredecessor();
                State nextS = (State) nextVert.getData();
                if (pred != null) {
                    prev = (State) pred.getData();
                }
                for (String n : problem.getMover().getMoveNames()) {
                    State target = (State) problem.getMover().doMove(n, prev);
                    if (nextS.equals(target)) {
                        newMove = n;
                    }
                }
                solvingAssistant.tryMove(newMove); //trys the move and updates the state
                startView.setText(problem.getCurrentState().toString());
                StringBuilder builder = new StringBuilder();
                builder.append(solvingAssistant.getMoveCount());
                String numMoves = builder.toString();
                moveCount.setText(numMoves);
                if (problem.getCurrentState().equals(problem.getFinalState())) { //check to see if problem is solved
                    String msg = getString(R.string.win);
                    messageView.setText(msg);
                    nextButton.setEnabled(false);
                }
            }
        });
        benches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Benchmark b = problem.getBenchmarks().get(i);
                problem.setInitialState(b.getStart());
                resetButton.performClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

    }
}