package emprestes.ds.domain.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTraversalTest {

    private final GraphTraversal traversal = new GraphTraversal();
    private Map<String, List<String>> graph;

    @BeforeEach
    void setUp() {
        graph = Map.of(
                "A", List.of("B", "C"),
                "B", List.of("D"),
                "C", List.of("E"),
                "D", List.of("F"),
                "E", List.of(),
                "F", List.of()
        );
    }

    @Test
    void shouldRunBfs() {
        assertEquals(List.of("A", "B", "C", "D", "E", "F"), traversal.bfs(graph, "A"));
    }

    @Test
    void shouldRunDfs() {
        assertEquals(List.of("A", "B", "D", "F", "C", "E"), traversal.dfs(graph, "A"));
    }

    @Test
    void shouldReturnEmptyForInvalidInput() {
        assertEquals(List.of(), traversal.bfs(null, "A"));
        assertEquals(List.of(), traversal.dfs(graph, "Z"));
    }
}
