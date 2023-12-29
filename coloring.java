import java.util.*;

public class algorithm_practice {
    public static int edges;

    public static enum state {
        UNDISCOVERED, DISCOVERED
    };

    public static enum colors {
        BLACK, RED, BLUE
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        edges = input.nextInt();
        input.nextLine();

        int[][] adjacencyMatrix = new int[edges][edges];

        for (int i = 0; i < edges; i++) {
            for (int j = 0; j < edges; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < edges; i++) {
            String edge = input.nextLine();
            String[] vertices = edge.split(" ");
            int vertex1 = Integer.parseInt(vertices[0]);
            int vertex2 = Integer.parseInt(vertices[1]);
            adjacencyMatrix[vertex1][vertex2] = 1;
            adjacencyMatrix[vertex2][vertex1] = 1;
        }

        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < edges; i++) {
            for (int j = 0; j < edges; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

        input.close();
        BFS(adjacencyMatrix, 0);

    }

    public static void BFS(int[][] graph, int source) {
        state[] explored = new state[graph.length];
        int[] predecessor = new int[graph.length];
        Queue<Integer> frontier = new LinkedList<>();
        colors[] coloring = new colors[graph.length];
        coloring[source] = colors.RED;
        for (int i = source + 1; i < graph.length; i++) {
            explored[i] = state.UNDISCOVERED;
            predecessor[i] = -1;
        }
        explored[source] = state.DISCOVERED;
        predecessor[source] = -1;
        frontier.add(source);

        while (!frontier.isEmpty()) {
            int CurrentElement = frontier.remove();
            for (int i = 0; i < graph.length; i++) {
                if (graph[CurrentElement][i] != 0 && coloring[CurrentElement] != coloring[i]
                        && explored[i] != state.DISCOVERED) {
                    predecessor[i] = CurrentElement;
                    explored[i] = state.DISCOVERED;
                    frontier.add(i);
                    if (coloring[CurrentElement] == colors.RED) {
                        coloring[i] = colors.BLUE;
                    } else {
                        coloring[i] = colors.RED;
                    }

                }
            }
        }
        System.out.println("\nMatches and Chosen Colors:");
        for (int i = 0; i < edges; i++) {
            System.out.println("Vertex " + i + " Color: " + coloring[i]);
        }
    }
}
