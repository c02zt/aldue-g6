package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: L�nge des Films
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
		
		List<String> result = new ArrayList<>();
		
		if (node != null) {
			List<String> left = getNodesInOrder(node.getLeft());
			Film nodeValue = node.getValue();
			String title = nodeValue.getTitel();
			List<String> right = getNodesInOrder(node.getRight());
			
			result.addAll(left);
			result.add(title);
			result.addAll(right);
		}
		
		return result;
	}
	
	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in Hauptreihenfolge
	 */
	public List<String> getNodesPreOrder(Node<Film> node, double min, double max) {
		
		List<String> result = new ArrayList<>();
		
		if (node != null) {
			Film nodeValue = node.getValue();
			String title = nodeValue.getTitel();
			
			System.out.println(title);
			
			double nodeLength = nodeValue.getLaenge();
			if (nodeLength > min && nodeLength < max) {
				result.add(title);
			}
			
			List<String> left = getNodesPreOrder(node.getLeft(), min, max);
			result.addAll(left);	
			
			List<String> right = getNodesPreOrder(node.getRight(), min, max);
			result.addAll(right);
		}
		
		return result;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren L�nge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale L�nge des Spielfilms
	 * @param max Maximale L�nge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		return getNodesPreOrder(root, min, max);
	}

}
