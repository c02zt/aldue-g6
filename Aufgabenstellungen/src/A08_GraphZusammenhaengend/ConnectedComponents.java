package A08_GraphZusammenhaengend;

import java.util.List;
import A01_Stack.Stack;
import A01_Stack.StackEmptyException;
import basisAlgorithmen.Graph;
import basisAlgorithmen.WeightedEdge;

public class ConnectedComponents {
	
	/**
	 * Retourniert die Anzahl der zusammenhängenden Komponenten eines Graphen
	 * @param g zu prüfender Graph G = (V, E)
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) {
		
		/**
		 * Schritt 1: Wir brauchen einen Zähler aller Komponenten.
		 * 
		 * Wir fangen mit einem Wert von 0 an, weil wir damit auch
		 * die Möglichkeit eines leeren Graphen mitberücksichtigen.
		 */
		int componentCount = 0;
		
		// Anzahl aller Knoten: nV = |V|.
		int nV = g.numVertices();
		
		/**
		 * Schritt 2: Wir brauchen eine Datenstruktur in der wir führen können,
		 * welche Knoten wir schon besucht haben.
		 * 
		 * Dafür bietet sich ein Feld (Array) der Wahrheitswerten (Boolean) bestens an.
		 *   true .... der Knoten i wurde (schon) besucht
		 *   false ... der Knoten i wurde (noch) nicht besucht
		 */
		boolean[] wasVisited = new boolean[nV];
		
		/**
		 * Schritt 3: Wir brauchen eine Schleife, die alle Knoten durchgeht.
		 * 
		 * Wir fangen im Knoten 0 an und gehen alle Knoten bis zum (nV - 1)
		 * durch.
		 * 
		 * Wenn ein Knoten (noch) nicht besucht wurde, fangen wir mit einer
		 * Tiefensuche in dem Knoten an.
		 * Sonst machen wir nichts.
		 * 
		 * Dafür eignet sich eine for-Schleife am besten.
		 */
		for (int i = 0; i < nV; i++) {
			// Wenn i (noch) nicht besucht wurde (äquivalent wasVisited[i] == false).
			if (!wasVisited[i]) {
				// Wir haben gerade den Knoten i besucht.
				wasVisited[i] = true;
				/**
				 * Schritt 4: Wir fangen mit einer Tiefensuche vom Knoten i aus an.
				 * 
				 * Es gibt zwei Möglichkeiten eine Tiefensuche zu implementieren:
				 *   1. rekursiv
				 *   2. mit einem Stack
				 *   
				 * Wir entscheiden uns für die zweite Möglichkeit.
				 * 
				 * Dafür brauchen wir einen neuen Stack für jede Tiefensuche.
				 * 
				 * Uns stehen zwei Methoden am Stack zur Verfügung:
				 *   void push(int i) ... Knoten i hinzufügen
				 *   int pop() .......... den obersten (ersten) Knoten vom Stack nehmen
				 *                        und ausgeben
				 *   
				 * Wir fangen an mit einem Stack, der nur den Knoten i enthält.
				 */
				Stack<Integer> stack = new Stack<>();
				stack.push(i);
				/**
				 * Schritt 5: Wir brauchen eine Schleife. Weil wir nicht genau wissen,
				 * in wie vielen Schritten wir fertig werden, nehmen wir eine while-Schleife.
				 * 
				 * In der Schleife entfernen wir den obersten Knoten aus dem Stack,
				 * finden alle seine Nachbaren (Knoten die vom obersten Knoten erreichbar sind;
				 * es existiert eine Kante zwischen den beiden Knoten) und fügen die unbesuchten
				 * Nachbaren in den Stack ein.
				 * 
				 * Wir machen das solange unser Stack nicht leer ist, was uns mittels einer
				 * StackEmptyException mitgeteilt wird, das bedeutet, dass wir sie in einem
				 * try-catch-Block fangen sollen.
				 * 
				 * Wenn unser Stack leer ist, haben wir alle Knoten in dieser zusammenhängenden
				 * Komponente besucht und können die Schleife verlassen, aber nicht bevor wir
				 * die Anzahl der zusammenhängenden Komponenten um eins vergrößern. 
				 */
				while(true) {
					try {
						// Wir entfernen den obersten Knoten im Stack und merken ihn uns.
						int currentVertex = stack.pop();
						/**
						 *  Wir gehen alle seine Nachbaren durch und fügen diejenigen ein,
						 *  die (noch) unbesucht sind.
						 *  Nach dem Einfügen markieren wir den Nachbaren als besucht.
						 */
						List<WeightedEdge> edgesToNeighbors = g.getEdges(currentVertex);
						for (WeightedEdge e : edgesToNeighbors) {
							int j = e.to_vertex;
							if (!wasVisited[j]) {
								stack.push(j);
								wasVisited[j] = true;
							}
						}
					} catch (StackEmptyException e) {
						/**
						 * Schritt 6: Wir haben alle Knoten in dieser zusammenhängenden Komponente
						 * besucht, darum können wir die Anzahl aller zusammenhängenden Komponenten
						 * um eins vergrößern und die while-Schleife verlassen.
						 */
						componentCount++;
						break;
					}
				}	
			}
		}
		/**
		 * Schritt 7: Wir geben die Anzahl aller zusammenhängenden Komponenten aus.
		 */
		return componentCount;
	}

}
