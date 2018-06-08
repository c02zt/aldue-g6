package A04_TraverseTree;

import java.util.Set;


public class Woerterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Z�hlt alle W�rter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der W�rter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {
		
		return 0;
	}

	/**
	 * Liefert die Menge aller W�rter retour, die ein spezifisches Pr�fix haben.
	 * @param prefix W�rter m�ssen diesen Pr�fix haben
	 * @return Menge aller zutreffenden W�rter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {
		
		return null;
	}
	

	/**
	 * Neues Wort hinzuf�gen
	 * @param wort Hinzuzuf�gendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch gr��er
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
