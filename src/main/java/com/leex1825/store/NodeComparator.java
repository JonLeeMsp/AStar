package com.leex1825.store;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getFTotalCost() > o2.getFTotalCost()) {
            return 1;
        } else if (o1.getFTotalCost() == o2.getFTotalCost()) {
            return 0;
        }
        else return -1;
    }
}
