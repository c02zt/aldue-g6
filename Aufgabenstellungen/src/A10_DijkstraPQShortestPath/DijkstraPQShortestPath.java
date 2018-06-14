package A10_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernungen initialisieren
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			/**
			 * Wir setzen die Entfernungen auf eine große Zahl.
			 */
			dist[i] = Integer.MAX_VALUE;
		}
	}

	/**
	 * Berechnet den kürzesten Weg ausgehend vom Startknoten zum Endknoten
	 * 
	 * @param from Startknoten
	 * @param to Endknoten
	 */
	protected boolean calculatePath(int from, int to) {

		VertexHeap heap = new VertexHeap(graph.numVertices());
		
		/**
		 * Wir setzen die Entfernung vom ersten Knoten zum ersten Knoten auf 0.
		 */
		dist[from] = 0;
		
		/**
		 * Wir füllen die Priority-Queue mit unseren Knoten.
		 * Die Priorität ist mit der Entfernung in der Entfernungstabelle
		 * `dist` angegeben.
		 */
		for (int i = 0; i < dist.length; i++) {
			Vertex v = new Vertex(i, dist[i]);
			heap.insert(v);
		}
		
		/**
		 * Wir nehmen Knoten aus dem Priority-Queue raus,
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
				 * Berechne die kumulierte Entfernung vom Knoten `from`
				 * zu dem Nachbar.
				 */
				int distCumulative = distToHere + distToNext;
				/**
				 * Der Nachbar.
				 */
				int neighbor = outgoingEdge.to_vertex;
				/**
				 * Falls die kumulierte Entfernung kürzer als die bis jetzt bekannte
				 * distanz zu diesem Knoten (Nachbar), dann speichern wir den neuen
				 * Wert in die Entfernungstabelle `dist˙ und verringern den Wert des
				 * Knotens im Heap, um es nach oben zu ziehen (swim).
				 * 
				 * Wir setzen auch unseren Knoten `current` als seinen Vorgänger.
				 */
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
		
		/**
		 * Es gibt einen Weg. :)
		 */
		return true;
	}
}
