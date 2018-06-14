package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		/**
		 * Initialisierung der Entfernungstabelle.
		 */
		initPathSearch();
		/**
		 * Falls kein Weg gefunden wurde, geben wir null aus.
		 */
		if (!calculatePath(from, to)) {
			return null;
		}
		/**
		 * Sonst kann ein Weg erstellt werden.
		 */
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {
		ArrayList<Integer> way = new ArrayList<Integer>();
		int i = to;
		while (i != from) {
			way.add(0, i);
			i = pred[i];
		}
		way.add(0, from);
		return way;
	}
}
