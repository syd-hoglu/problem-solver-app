package com.example.problemsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.problemsolver.domains.farmer.FarmerProblem;
import com.example.problemsolver.framework.graph.Vertex;
import com.example.problemsolver.framework.problem.Problem;
import com.example.problemsolver.framework.problem.State;
import com.example.problemsolver.framework.solution.AStarSolver;
import com.example.problemsolver.framework.solution.SolvingAssistant;

import java.util.LinkedList;

public class farmerMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);
        Problem problem = new FarmerProblem();
        SolvingAssistant solvingAssistant = new SolvingAssistant(problem);
        TextView startView = (TextView) findViewById(R.id.farmer_solve_start);
        startView.setText(problem.getCurrentState().toString());
        TextView moveCount = (TextView) findViewById(R.id.farmer_moves_count);
        moveCount.setText("0");
        LinearLayout movesView = (LinearLayout) findViewById(R.id.farmer_moves);
        LinkedList<Button> buttonList = new LinkedList();
        TextView messageView = (TextView) findViewById(R.id.farmer_message);
        Button resetButton = (Button) findViewById(R.id.farmer_reset);
        resetButton.setPadding(5, 5, 5, 5);
        resetButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        Button solveButton = (Button) findViewById(R.id.farmer_solve);
        solveButton.setPadding(5, 5, 5, 5);
        solveButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        Button nextButton = (Button) findViewById(R.id.farmer_next);
        nextButton.setPadding(5, 5, 5, 5);
        nextButton.setBackgroundColor(Color.parseColor("#FFBB86FC"));
        nextButton.setEnabled(false);
        AStarSolver aStar = new AStarSolver(problem);
        TextView stats = (TextView) findViewById(R.id.farmer_stats);
        for (int i = 0; i < problem.getMover().getMoveNames().size(); i++) { //for loop to add buttons dynamically
            String name = problem.getMover().getMoveNames().get(i); //dynamically get move names
            Button move = new Button(movesView.getContext()); //dynamically creates buttons with move name
            move.setMinWidth(150);
            move.setId(i);
            buttonList.add(move);
            move.setPadding(5, 5, 5, 5);
            move.setBackgroundColor(Color.parseColor("#FF64A144"));
            move.setText(name);
            move.setTextColor(Color.parseColor("#FFD0FDB7"));
            move.setOnClickListener(e -> { //start button actions
                messageView.setText(" ");
                solvingAssistant.tryMove(name); //trys the move and updates the state
                if (!solvingAssistant.isMoveLegal()) { //check to see if illegal move text needs to be displayed
                    String msg = getString(R.string.illegal);
                    messageView.setText(msg);
                }
                startView.setText(problem.getCurrentState().toString()); //update display
                StringBuilder builder = new StringBuilder();
                builder.append(solvingAssistant.getMoveCount());
                String numMoves = builder.toString();
                moveCount.setText(numMoves); //update move count
                if (problem.getCurrentState().equals(problem.getFinalState())) { //check to see if problem is solved
                    for (Button m : buttonList) {
                        m.setEnabled(false);
                    }
                    String msg = getString(R.string.win);
                    messageView.setText(msg);

                }
            }); //end button action
            movesView.addView(move); //adds new button to the box
        }
        resetButton.setOnClickListener(view -> {
            solvingAssistant.reset(); //resets the state
            startView.setText(problem.getCurrentState().toString()); //resets the visual
            for (Button m : buttonList) {
                m.setEnabled(true);
                m.setTextColor(Color.parseColor("#FFD0FDB7"));
            }
            solveButton.setEnabled(true);
            nextButton.setEnabled(false);
            moveCount.setText("0");//resets move count
            messageView.setText(" ");
        });
        solveButton.setOnClickListener(f -> {
            solveButton.setEnabled(false);
            nextButton.setEnabled(true);
            for (Button m : buttonList) {
                m.setEnabled(false);
            }
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
                for (Button m : buttonList) {
                    m.setTextColor(Color.parseColor("#FFD0FDB7"));
                }
                for (Button m : buttonList) {
                    if (m.getText().equals(newMove)) {
                        m.setTextColor(Color.parseColor("#FFFF0000"));
                    }
                }
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

    }
}