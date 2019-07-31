package by.derovi.group2019c;

import java.util.Random;

public class GraphGenerator {
    public static Graph generate(int size, int density) {
        Random random = new Random(System.currentTimeMillis());
        int[] colors = new int[size];
        for(int idx = 0; idx < colors.length; ++ idx)
            colors[idx] = random.nextInt(2);
        Graph graph = new Graph(size, colors);
        for(int a = 0; a < size; ++ a)
            for(int b = a + 1; b < size; ++ b)
                if(random.nextInt(100) < density)
                    graph.addEdge(a, b);
        return graph;
    }
}
