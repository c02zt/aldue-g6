package A10_DijkstraPQShortestPath;
import java.util.ArrayList;
import java.util.List;


public class ArrayGraph implements Graph {

	private int[][] graph;
	private int numVertices;
	private boolean directed;
	
	public ArrayGraph(int numVertices, boolean directed) {
		graph = new int[numVertices][numVertices];
		this.numVertices = numVertices;
		this.directed = directed;
	}
	
	public int numVertices() {
		return numVertices;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean hasEdge(int u, int v) {
		return (graph[u][v] > 0);
	}

	public int getEdgeWeight(int u, int v) {
		return graph[u][v];
	}

	public void addEdge(int u, int v) {
		addEdge(u, v, 1);
	}
	
	public void addEdge(int u, int v, int weight) {
		if (u < numVertices && v < numVertices) {
			graph[u][v] = weight;
			if (!directed) {
				graph[v][u] = weight;
			}
		}
	}

	public void removeEdge(int u, int v) {
		if (u < numVertices && v < numVertices) {
			graph[u][v] = 0;
			if (!directed) {
				graph[v][u] = 0;
			}
		}
	}

	public List<WeightedEdge> getEdges(int v) {
		List<WeightedEdge> edges = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			int weight = graph[v][i];
			if (weight > 0) {
				edges.add(new WeightedEdge(v, i, weight));
			}
		}
		return edges;
	}
}
