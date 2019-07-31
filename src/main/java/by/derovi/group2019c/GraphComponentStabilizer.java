package by.derovi.group2019c;

import java.util.List;

public class GraphComponentStabilizer implements GraphStabilizer {


    @Override
    public Graph getStabilized(Graph graph) {
        Graph stabilized = graph.clone();
        List<Graph.Component> components = stabilized.getComponents();
        for(Graph.Component component : components) {
            int color = 0;
            if(component.getColor1() > component.getColor0())
                color = 1;
            for(Graph.Vertex vertex : component.getVertices()) {
                if(vertex.getColor() != color)
                    vertex.setColor(color);
            }
        }
        return stabilized;
    }
}
