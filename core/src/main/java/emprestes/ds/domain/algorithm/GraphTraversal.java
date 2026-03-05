package emprestes.ds.domain.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generic graph traversal helpers over adjacency-list maps.
 */
public class GraphTraversal {

    /**
     * Performs BFS traversal from a start vertex.
     *
     * @param graph adjacency list representation
     * @param start start vertex
     * @param <T> vertex value type
     * @return traversal order, or empty list when input is invalid
     */
    public <T> List<T> bfs(Map<T, List<T>> graph, T start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return List.of();
        }

        var visited = new LinkedHashSet<T>();
        var queue = new ArrayDeque<T>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            var node = queue.removeFirst();
            for (var neighbor : neighborsOf(graph, node)) {
                if (visited.add(neighbor)) {
                    queue.addLast(neighbor);
                }
            }
        }

        return new ArrayList<>(visited);
    }

    /**
     * Performs DFS traversal from a start vertex.
     *
     * @param graph adjacency list representation
     * @param start start vertex
     * @param <T> vertex value type
     * @return traversal order, or empty list when input is invalid
     */
    public <T> List<T> dfs(Map<T, List<T>> graph, T start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return List.of();
        }

        var visited = new LinkedHashSet<T>();
        var stack = new ArrayDeque<T>();
        stack.push(start);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (visited.add(node)) {
                var neighbors = neighborsOf(graph, node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    var neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(visited);
    }

    /**
     * Returns valid neighbor vertices for a node.
     *
     * @param graph adjacency list representation
     * @param node node whose neighbors will be resolved
     * @param <T> vertex value type
     * @return neighbors that also exist as graph keys
     */
    private <T> List<T> neighborsOf(Map<T, List<T>> graph, T node) {
        return graph.getOrDefault(node, List.of())
                .stream()
                .filter(graph::containsKey)
                .toList();
    }
}
