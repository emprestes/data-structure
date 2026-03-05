package emprestes.ds.domain.data;

import emprestes.ds.domain.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Directed graph implementation using adjacency lists.
 *
 * @param <T> vertex value type
 */
public class AdjacencyListGraph<T> implements Graph<T> {

    private final Map<T, Set<T>> adjacency = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Graph<T> addVertex(T value) {
        if (value != null) {
            adjacency.computeIfAbsent(value, k -> new LinkedHashSet<>());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Graph<T> addEdge(T source, T destination) {
        if (source == null || destination == null) {
            return this;
        }

        addVertex(source);
        addVertex(destination);
        adjacency.get(source).add(destination);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> bfs(T start) {
        if (!adjacency.containsKey(start)) {
            return List.of();
        }

        var visited = new LinkedHashSet<T>();
        var queue = new ArrayDeque<T>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            var node = queue.removeFirst();
            for (var neighbor : adjacency.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.addLast(neighbor);
                }
            }
        }

        return new ArrayList<>(visited);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> dfs(T start) {
        if (!adjacency.containsKey(start)) {
            return List.of();
        }

        var visited = new LinkedHashSet<T>();
        var stack = new ArrayDeque<T>();
        stack.push(start);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (visited.add(node)) {
                var neighbors = new ArrayList<>(adjacency.get(node));
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
     * {@inheritDoc}
     */
    @Override
    public List<T> shortestPath(T source, T destination) {
        if (!adjacency.containsKey(source) || !adjacency.containsKey(destination)) {
            return List.of();
        }

        var queue = new ArrayDeque<T>();
        var visited = new LinkedHashSet<T>();
        var parent = new HashMap<T, T>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            if (current.equals(destination)) {
                return buildPath(parent, destination);
            }

            for (var neighbor : adjacency.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.addLast(neighbor);
                }
            }
        }

        return List.of();
    }

    /**
     * Reconstructs a path from parent links generated during BFS.
     *
     * @param parent map of child to parent vertex
     * @param destination final vertex
     * @return path from source to destination
     */
    private List<T> buildPath(Map<T, T> parent, T destination) {
        var path = new ArrayList<T>();
        T current = destination;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        var result = new ArrayList<T>(path.size());
        for (int i = path.size() - 1; i >= 0; i--) {
            result.add(path.get(i));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<T> vertices() {
        return Set.copyOf(adjacency.keySet());
    }
}
