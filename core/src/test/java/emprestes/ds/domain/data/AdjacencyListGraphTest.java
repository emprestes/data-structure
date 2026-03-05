package emprestes.ds.domain.data;

import emprestes.ds.domain.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyListGraphTest {

    private Graph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>();
        graph.addEdge("A", "B")
                .addEdge("A", "C")
                .addEdge("B", "D")
                .addEdge("C", "E")
                .addEdge("D", "F");
    }

    @Test
    void shouldTraverseWithBfs() {
        assertEquals(java.util.List.of("A", "B", "C", "D", "E", "F"), graph.bfs("A"));
    }

    @Test
    void shouldTraverseWithDfs() {
        assertEquals(java.util.List.of("A", "B", "D", "F", "C", "E"), graph.dfs("A"));
    }

    @Test
    void shouldFindShortestPath() {
        graph.addEdge("B", "E");

        assertEquals(java.util.List.of("A", "B", "E"), graph.shortestPath("A", "E"));
        assertEquals(java.util.List.of(), graph.shortestPath("E", "A"));
    }
}
