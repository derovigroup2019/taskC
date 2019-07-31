package by.derovi.group2019c;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GraphStructureStabilizer implements GraphStabilizer {
    private Graph graph;

    @Override
    public Graph getStabilized(Graph graph) {
        this.graph = graph.clone();
        stabilizeForColor(0);
        stabilizeForColor(1);
        return this.graph;
    }

    private void stabilizeForColor(int color) {
        Set<Graph.Vertex> negative = new HashSet<>(), positive = new HashSet<>();
        for(Graph.Vertex vertex : graph.getVertices()) {
            if(vertex.getColor() != color)
                continue;
            if(vertex.getDelta() < 0)
                negative.add(vertex);
            else
                positive.add(vertex);
        }

        while(negative.size() > 0) {
            boolean found = false;
            Iterator<Graph.Vertex> iterator = negative.iterator();
            Graph.Vertex vertex = iterator.next();
            Set<Graph.Vertex> neighbours = new HashSet<>(vertex.getNeighbours());
            while(iterator.hasNext()) {
                Graph.Vertex other = iterator.next();
                if(neighbours.contains(other))
                    continue;
                graph.addEdge(vertex.getIdx(), other.getIdx());
                found = true;
                break;
            }
            if(!found) {
                iterator = positive.iterator();
                while(iterator.hasNext()) {
                    Graph.Vertex other = iterator.next();
                    if(neighbours.contains(other))
                        continue;
                    graph.addEdge(vertex.getIdx(), other.getIdx());
                    found = true;
                    break;
                }
                if(!found) {
                    Graph.Vertex other = graph.addVertex(color);
                    graph.addEdge(vertex.getIdx(), other.getIdx());
                }
            }
            if(vertex.getDelta() >= 0) {
                negative.remove(vertex);
                positive.add(vertex);
            }
        }
    }
}
