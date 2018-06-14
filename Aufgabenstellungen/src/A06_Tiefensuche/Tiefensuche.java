package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {
		
		return Double.compare(a.getLaenge(), b.getLaenge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {
		/**
		 * Eine neue Liste für das Speichern der Resultate wird erstellt.
		 */
		List<String> result = new ArrayList<>();
		
		/**
		 * Wenn unser Knoten nicht existiert machen wir nichts.
		 * Es wird am Ende eine leere Liste der Resultate ausgegeben.
		 */
		if (node != null) {
			/**
			 * Erstens rufen wir dieselbe Methode auf dem linken Kind.
			 */
			List<String> resultOfLeftChild = getNodesInOrder(node.getLeft());
			/**
			 * Zweitens lesen wir den Titel des Films im Knoten aus und speichern
			 * ihn in der variable `title`.
			 */
			Film nodeValue = node.getValue();
			String title = nodeValue.getTitel();
			/**
			 * Letztens rufen wir dieselbe Methode auf dem rechten Kind.
			 */
			List<String> resultOfRightChild = getNodesInOrder(node.getRight());
			
			/**
			 * Die resultierende Liste des linken Kindes wird zuerst der leeren
			 * Liste der Resultate hinzugefügt.
			 * Anschließend wird die Titel des Films, der im Knoten liegt, hinzugefügt.
			 * Abschließend wird noch die resultierende Liste des rechten Kindes hinzugefügt.
			 */
			result.addAll(resultOfLeftChild);
			result.add(title);
			result.addAll(resultOfRightChild);
		}
		
		/**
		 * Die Liste der Resultate wird ausgegeben.
		 */
		return result;
	}
	
	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in Hauptreihenfolge
	 */
	public List<String> getNodesPreOrder(Node<Film> node, double min, double max) {
		/**
		 * Eine neue Liste für das Speichern der Resultate wird erstellt.
		 */
		List<String> result = new ArrayList<>();
		
		/**
		 * Wenn unser Knoten nicht existiert machen wir nichts.
		 * Es wird am Ende eine leere Liste der Resultate ausgegeben.
		 */
		if (node != null) {
			/**
			 * Erstens lesen wir den Titel des Films im Knoten aus und speichern
			 * ihn in der variable `title`.
			 */
			Film nodeValue = node.getValue();
			String title = nodeValue.getTitel();
			
			/**
			 * Wir fügen den Titel der Liste der Resultate hinzu, falls
			 * die Filmlänge zwischen den gegebenen Werten liegt.
			 */
			double nodeLength = nodeValue.getLaenge();
			if (nodeLength > min && nodeLength < max) {
				result.add(title);
			}
			
			/**
			 * Zweitens rufen wir dieselbe Methode auf dem linken Kind
			 * und fügen die resultierende Liste der Liste `result` hinzu.
			 */
			List<String> left = getNodesPreOrder(node.getLeft(), min, max);
			result.addAll(left);	
			
			/**
			 * Letztens rufen wir dieselbe Methode auf dem rechten Kind
			 * und fügen die resultierende Liste der Liste `result` hinzu.
			 */
			List<String> right = getNodesPreOrder(node.getRight(), min, max);
			result.addAll(right);
		}
		
		/**
		 * Die Liste der Resultate wird ausgegeben.
		 */
		return result;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		/**
		 * Aufruf der rekursiven Methode.
		 */
		return getNodesPreOrder(root, min, max);
	}

}
