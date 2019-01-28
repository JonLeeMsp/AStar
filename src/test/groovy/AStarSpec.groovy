import com.leex1825.algorithm.AStar
import com.leex1825.store.Edge
import com.leex1825.store.Graph
import com.leex1825.store.Node
import com.leex1825.store.NodeComparator
import spock.lang.Specification

class AStarSpec extends Specification {

    AStar astar

    NodeComparator nodeComparator

    Graph graph

    def setup() {
        NodeComparator nodeComparator = new NodeComparator()
        graph = createGraph();
        astar = new AStar(nodeComparator: nodeComparator)
    }

    def "find AStar shortest path"() {
        given:
            List<Node> nodes = graph.getNodes()
            Node startNode = nodes.get(0)
            Node endNode = nodes.get(nodes.size() - 1)

        when:
            List<Node> shortestPath = astar.search(startNode, endNode)

        then:
            shortestPath != null
            shortestPath.size() == 2
            shortestPath.get(0).getId() == 0
            shortestPath.get(1).getId() == 4
    }


    def createGraph() {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            nodes.add(new Node(i, i * 5));
        }

        for (int j = 0; j < nodes.size(); ++j) {

            Node node = nodes.get(j);

            for (int k = j; k < nodes.size(); ++k) {
                Node node2 = nodes.get(k);

                Edge edge = new Edge();
                edge.setCost(j * k);
                edge.setTargetNode(node2);

                node.getNeighboringEdges().add(edge);
            }
        }

        Graph graph = new Graph(nodes);
        return graph;
    }
}