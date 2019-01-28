package com.leex1825.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    public Node(int id, int heuristicValue){
        this.id = id;
        this.heuristicValue = heuristicValue;
    }

    private int id;

    private int gDistance;

    private int fTotalCost;

    private int heuristicValue;

    @ToString.Exclude
    private Node parentNode;

    @ToString.Exclude
    private List<Edge> neighboringEdges = new ArrayList<>();

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Node)) {
            return false;
        }

        Node node = (Node) o;

        return this.id == node.id;
    }

}
