package com.leex1825.algorithm;

import com.leex1825.store.Edge;
import com.leex1825.store.Node;
import com.leex1825.store.NodeComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AStar {

    @Autowired
    private NodeComparator nodeComparator;

    public List<Node> search(Node startNode, Node endNode) {

        startNode.setFTotalCost(0);
        startNode.setHeuristicValue(0);
        startNode.setGDistance(0);

        endNode.setFTotalCost(0);
        endNode.setHeuristicValue(0);
        endNode.setGDistance(0);

        Queue<Node> openPriorityQueue = new PriorityQueue<>(nodeComparator);
        Set<Node> visitedNodes = new HashSet<>();

        openPriorityQueue.add(startNode);

        while (!openPriorityQueue.isEmpty()) {

            // need to find the node with the least f value in PQ
            Node currentNode = openPriorityQueue.poll();

            visitedNodes.add(currentNode);

            if (currentNode.getId() == endNode.getId()) {
                // found it
                log.info("Found the path.  Need to backtrack now");
                break;
            }

            for (Edge neighboringEdge : currentNode.getNeighboringEdges()) {
                Node destinationNode = neighboringEdge.getTargetNode();

                int edgeCost = neighboringEdge.getCost();
                int calcGScore = currentNode.getGDistance() + edgeCost;
                int calcfScore = calcGScore + destinationNode.getHeuristicValue();

                if (visitedNodes.contains(destinationNode) &&
                    calcfScore > destinationNode.getFTotalCost()) {

                    continue;
                } else if((!openPriorityQueue.contains(destinationNode)) ||
                        (calcfScore < destinationNode.getFTotalCost())){

                    destinationNode.setParentNode(currentNode);
                    destinationNode.setGDistance(calcGScore);
                    destinationNode.setFTotalCost(calcfScore);

                    openPriorityQueue.add(destinationNode);
                }
            }

        }
        List<Node> shortestPath = backTrackPath(startNode, endNode);
        return shortestPath;
    }

    public List<Node> backTrackPath(Node startNode, Node endNode){
        List<Node> shortestPath = new ArrayList<Node>();

        for(Node node = endNode; node!=null; node = node.getParentNode()){

            shortestPath.add(node);

            if (node.getId() == startNode.getId()) {
                break;
            }
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
