package by.derovi.group2019c;

public class GraphColorStabilizer implements GraphStabilizer {
    @Override
    public Graph getStabilized(Graph graph) {
        if(graph.size() > 31) return null;
        int best = graph.size();
        Graph answer = null;
        for(int msk = 0; msk < 1 << graph.size(); ++ msk) {
            int changed = 0;
            Graph stabilized = graph.clone();
            int zeroCnt = 0;
            for(Graph.Vertex vertex : stabilized.getVertices()) {
                int color = 1 & (msk >> vertex.getIdx());
                if(color == 0) zeroCnt ++;
                if(vertex.getColor() != color) {
                    vertex.setColor(color);
                    ++ changed;
                }
            }
            if(zeroCnt == graph.size() || zeroCnt == 0)
                continue;
            if(changed < best && stabilized.isStabilized()) {
                best = changed;
                answer = stabilized;
            }
        }
        return answer;
    }
}
