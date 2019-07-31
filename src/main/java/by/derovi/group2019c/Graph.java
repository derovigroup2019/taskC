package by.derovi.group2019c;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    class Vertex {
        private int idx, color;
        private List<Vertex> neighbours = new ArrayList<>();

        Vertex(int idx, int color) {
            this.idx = idx;
            this.color = color;
        }

        public int getIdx() {
            return idx;
        }

        public List<Vertex> getNeighbours() {
            return neighbours;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getDelta() {
            int delta = 0;
            for(Vertex neighbour : neighbours)
                if(neighbour.getColor() == color)
                    ++ delta;
                else
                    -- delta;
            return delta;
        }

        public Vertex clone() {
            return new Vertex(idx, color);
        }
    }

    class Component {
        private List<Vertex> vertices = new ArrayList<>();
        int color0 = 0, color1 = 0;

        Component() {}

        public void add(Vertex vertex) {
            vertices.add(vertex);
            if(vertex.getColor() == 0)
                ++ color0;
            else
                ++ color1;
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public int getColor0() {
            return color0;
        }

        public int getColor1() {
            return color1;
        }
    }

    private List<Vertex> vertices;

    public Graph() {

    }

    public Graph(int size, int[] colors) {
        vertices = new ArrayList<>();
        for(int idx = 0; idx < size; ++ idx) {
            vertices.add(new Vertex(idx, colors[idx]));
        }
    }

    public void addEdge(int a, int b) {
        vertices.get(a).getNeighbours().add(vertices.get(b));
        vertices.get(b).getNeighbours().add(vertices.get(a));
    }

    public boolean stabilize(GraphStabilizer graphStabilizer) {
        Graph stabilized = graphStabilizer.getStabilized(this);
        if(stabilized == null)
            return false;
        this.vertices = stabilized.getVertices();
        return true;
    }

    @Override
    public String toString() {
        List<String> edges = new ArrayList<>();
        for(Vertex vertex : vertices) {
            for(Vertex neighbour : vertex.getNeighbours()) {
                if(neighbour.getIdx() > vertex.getIdx()) {
                    edges.add(Integer.toString(vertex.getIdx() + 1) + ' ' + Integer.toString(neighbour.getIdx() + 1));
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(vertices.size()).append(" vertices, ").append(edges.size()).append(" edges.");
        builder.append("Edges:\n");
        for(String edge : edges)
            builder.append(edge).append('\n');
        builder.append("Colors:\n");
        for(Vertex vertex : vertices) {
            builder.append(vertex.getIdx() + 1).append(". ").append(vertex.getColor()).append("\n");
        }
        return builder.toString();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public boolean isStabilized() {
        for(Vertex vertex : vertices)
            if(vertex.getDelta() < 0)
                return false;
        return true;
    }

    public int size() {
        return vertices.size();
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    protected Graph clone() {
        Graph graph = new Graph();
        graph.setVertices(new ArrayList<>());
        for(Vertex vertex : vertices)
            graph.getVertices().add(vertex.clone());
        for(Vertex vertex : vertices)
            for(Vertex neighbour : vertex.getNeighbours())
                if(vertex.getIdx() < neighbour.getIdx())
                    graph.addEdge(vertex.getIdx(), neighbour.getIdx());
        return graph;
    }

    public Vertex addVertex(int color) {
        Vertex vertex = new Vertex(vertices.size(), color);
        vertices.add(vertex);
        return vertex;
    }

    private void getComponent(Vertex vertex, Component component, boolean[] checked) {
        checked[vertex.getIdx()] = true;
        component.add(vertex);
        for(Vertex neighbour : vertex.getNeighbours()) {
            if(checked[neighbour.getIdx()])
                continue;
            getComponent(neighbour, component, checked);
        }
    }

    public List<Component> getComponents() {
        List<Component> components = new ArrayList<>();
        boolean[] checked = new boolean[size()];
        for(int idx = 0; idx < size(); ++ idx) {
            if(checked[idx])
                continue;
            Component component = new Component();
            getComponent(vertices.get(idx), component, checked);
            components.add(component);

        }
        return components;
    }
}
