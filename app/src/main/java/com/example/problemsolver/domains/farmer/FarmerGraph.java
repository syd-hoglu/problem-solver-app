package com.example.problemsolver.domains.farmer;

import com.example.problemsolver.framework.graph.Graph;
import com.example.problemsolver.framework.graph.Vertex;

/**
 * This class represents the graph of Farmer solutions
 * @author Sydney Hoglund Section 001
 */
public class FarmerGraph extends Graph {

    public static final Vertex v1 = new Vertex(new FarmerState("West",
            "West",
            "West",
            "West"));
    public static final Vertex v2 = new Vertex(new FarmerState("West",
            "East",
            "West",
            "East"));
    public static final Vertex v3 = new Vertex(new FarmerState("West",
            "East",
            "West",
            "West"));
    public static final Vertex v4 = new Vertex(new FarmerState("West",
            "West",
            "East",
            "West"));
    public static final Vertex v5 = new Vertex(new FarmerState("West",
            "West",
            "West",
            "East"));
    public static final Vertex v6 = new Vertex(new FarmerState("East",
            "East",
            "East",
            "East"));
    public static final Vertex v7 = new Vertex(new FarmerState("East",
            "West",
            "East",
            "West"));
    public static final Vertex v8 = new Vertex(new FarmerState("East",
            "West",
            "East",
            "East"));
    public static final Vertex v9 = new Vertex(new FarmerState("East",
            "East",
            "West",
            "East"));
    public static final Vertex v10 = new Vertex(new FarmerState("East",
            "East",
            "East",
            "West"));

    public FarmerGraph() {
        add(v1, v7);
        add(v7, v4);
        add(v4, v8);
        add(v8, v5);
        add(v5, v9);
        add(v4, v10);
        add(v10, v3);
        add(v3, v9);
        add(v9, v2);
        add(v2, v6);
    }

    private void add(Vertex x, Vertex y) {
        addEdge(x, y);
        addEdge(y, x);
    }

}
