package by.derovi.group2019c.dialog;

import by.derovi.group2019c.*;

public class CommandGenerate extends Command {
    public CommandGenerate() {
        super("generate", "generate <vertex count> <density (0-100)> <stabilization method (0/1/2)>  - generate new stabilized graph", 3, "\n========= Graph generation =========");
    }

    @Override
    public void execute(String[] args) {
        System.out.println(getHeader());
        int size = Integer.parseInt(args[0]);
        int density = Integer.parseInt(args[1]);
        int stabilizationType = Integer.parseInt(args[2]);
        while(true) {
            Graph graph = GraphGenerator.generate(size, density);
            boolean bl = false;
            if (stabilizationType == 0)
                bl = graph.stabilize(new GraphColorStabilizer());
            else
                if(stabilizationType == 1)
                    bl = graph.stabilize(new GraphStructureStabilizer());
                else bl = graph.stabilize(new GraphComponentStabilizer());
            if(bl && graph.isStabilized()) {
                System.out.println(graph);
                break;
            }
        }
        System.out.println();
    }
}
