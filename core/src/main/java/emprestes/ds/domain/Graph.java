package emprestes.ds.domain;

import java.util.List;
import java.util.Set;

/**
 * Defines operations for a directed graph abstraction.
 *
 * @param <T> vertex value type
 */
public interface Graph<T> {

    /**
     * Adds a vertex when it does not exist.
     *
     * @param value vertex value
     * @return current graph instance
     */
    Graph<T> addVertex(T value);

    /**
     * Adds a directed edge from source to destination.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @return current graph instance
     */
    Graph<T> addEdge(T source, T destination);

    /**
     * Performs breadth-first traversal from the given start vertex.
     *
     * @param start start vertex
     * @return traversal order
     */
    List<T> bfs(T start);

    /**
     * Performs depth-first traversal from the given start vertex.
     *
     * @param start start vertex
     * @return traversal order
     */
    List<T> dfs(T start);

    /**
     * Finds the shortest path in number of edges between two vertices.
     *
     * @param source source vertex
     * @param destination destination vertex
     * @return path from source to destination, or empty list when unreachable
     */
    List<T> shortestPath(T source, T destination);

    /**
     * Returns all vertices currently stored in the graph.
     *
     * @return immutable view of vertices
     */
    Set<T> vertices();
}
