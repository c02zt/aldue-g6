package A10_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernungen initialisieren
	 * 
	 * @param from Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			/**
			 * Wir setzen die Distanzen auf eine große Zahl.
			 */
			dist[i] = Integer.MAX_VALUE;
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from Startknoten
	 * @param to Endknoten
	 */
	protected boolean calculatePath(int from, int to) {

		VertexHeap heap = new VertexHeap(graph.numVertices());
		
		/**
		 * Wir setzen die Distanz zum ersten Knoten auf 0.
		 */
		dist[from] = 0;
		
		/**
		 * Wir füllen die Priority-Queue mit unseren Knoten.
		 * Die Priorität ist mit der Distanz angegeben.
		 */
		for (int i = 0; i < dist.length; i++) {
			Vertex v = new Vertex(i, dist[i]);
			heap.insert(v);
		}
		
		/**
		 * Wir nehmen Knoten aus dem Priority-Queue aus,
		 * solange bis sie leer ist.
		 */
		while (!heap.isEmpty()) {
			/**
			 * Enteferne den ersten Knoten aus dem Priority-Queue.
			 */
			Vertex current = heap.remove();
			/**
			 * Finde seine Nachbaren.
			 */
			List<WeightedEdge> outgoingEdges = graph.getEdges(current.vertex);
			for (WeightedEdge outgoingEdge : outgoingEdges) {
				int distToHere = dist[current.vertex];
				int distToNext = outgoingEdge.weight;
				/**
				 * Berechne die kumulierte Distanz.
				 */
				int distCumulative = distToHere + distToNext;
				int neighbor = outgoingEdge.to_vertex;
				if (distCumulative < dist[neighbor]) {
					dist[neighbor] = distCumulative;
					heap.setCost(neighbor, distCumulative);
					pred[neighbor] = current.vertex;
				}
			}
		}
		
		/**
		 * Wenn es keinen Vorgänger vom Knoten to gibt,
		 * dann exisistiert kein Weg zwischen from und to.
		 */
		if (pred[to] == -1) {
			return false;
		}
		
		return true;
	}
}
