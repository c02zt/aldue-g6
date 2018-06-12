package A13_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 * @param personen Zu sortierendes Array
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length - 1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		/**
		 * Wir teilen unseres Feld `personen` so lange auf zwei Hälften auf,
		 * bis wir einen Abschnitt des Feldes mit höchstens einem Element haben.
		 * 
		 * Somit haben wir zwei Möglichkeiten:
		 *   - wir haben keine Elemente im Abschnitt,
		 *     das heißt der Endpunkt `end` liegt vor dem Startpunkt `start`
		 *       `end < start`
		 *   - wir haben genau einen Element im Abschnitt,
		 *     das heißt der Endpunkt `end` liegt im Startpunkt `start`
		 *       `end = start`
		 * 
		 * Sobald diese Abbruchbedingung erreicht wird,
		 * kann unser Feld nicht mehr aufgeteilt werden.
		 * Also gibt es nichts mehr zu machen.
		 * Wir dürfen mit einem `return` abbrechen.
		 */
		if (end <= start) return;
		
		/**
		 * Die Variable `mitte` ist unser „Mittelpunkt“,
		 * der zwischen dem Start- und Endpunkt liegt.
		 *   `start <= mitte < end`
		 * 
		 * Das ist der Punkt *hinter* dem wir unser Feld `personen` „schneiden“,
		 * um es auf zwei Hälften `teil1` und `teil2` aufzuteilen.
		 * 
		 * Unser Feld `personen`
		 * 
		 * `personen` := [ ][ ]...[ ][ ][ ]...[ ]
		 *                ^        ^           ^
		 *             `start`  `mitte`      `end`
		 *  
		 * wird auf zwei Teile `teil1` und `teil2` aufgeteilt
		 * 
		 * `teil1` := [ ][ ]...[ ]
		 *             ^        ^
		 *          `start`  `mitte`
		 *         
		 * `teil2` := [ ][ ]...[ ]
		 *             ^        ^
		 *        `mitte + 1` `end`
		 * 
		 * Also ist der Wert der Variable `mitte` das arithmetische Mittel
		 * (Durchschnitt) der Werte der Variablen `start` und `end`.
		 */
		int mitte = (start + end) / 2;

		/**
		 * Jetzt haben wir alles, was wir für eine Aufteilung brauchen.
		 * 
		 * Wir rufen unsere Methode rekursiv auf.
		 * In diesem Schritt wird unser Feld noch *nicht* in zwei Felder
		 * aufgeteilt. Das kommt erst später im Merge-Teil.
		 * 
		 * Jedoch werden die Grenzpunkte
		 *   `start`, `mitte`, `mitte + 1` und `end`
		 * für den rekursiven Aufruf auf dem *ursprünglichem* Feld `personen`
		 * gebraucht.
		 *                __sort()__         __sort()__
		 *               /          \       /          \
		 * `personen` := [ ][ ]...[ ]       [ ][ ]...[ ]
		 *                ^        ^         ^        ^
		 *             `start`  `mitte` `mitte + 1` `end`
		 * 
		 * Als Resultat werden die beiden Hälften nach den rekursiven Aufrufen von
		 * `sort()` sortiert. 
		 */
		sort(personen, start, mitte);
		sort(personen, mitte + 1, end);

		/**
		 * Die sortierten Hälften müssen jetzt zusammengefügt werden.
		 * 
		 * Wir erstellen die Hälften für das Zusammenfügen mithilfe
		 * der Methode `Arrays.copyOfRange()` als Kopien des ursprünglichen Feldes;
		 *   `teil1` ... `result` vom Anfangspunkt bis zum Mittelpunkt und
		 *   `teil2` ... `result` vom Mittelpunkt bis zum Endpunkt.
		 * 
		 * Hinweis: Bei der Methode `copyOfRange()` ist die Obergrenze exklusiv,
		 * deshalb `<letztes Element> + 1`. (vergleiche mit der Rekursion oben)
		 */
		Person[] teil1 = Arrays.copyOfRange(personen, start, mitte + 1);
		Person[] teil2 = Arrays.copyOfRange(personen, mitte + 1, end + 1);
		/**
		 * Merge-Methode aufrufen und beide Teile ins Feld `personen` zusammenfügen;
		 * ab dem Startpunkt.
		 */
		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		/**
		 * Für das Zusammenfügen zweier Felder in das ursprüngliche Feld
		 * brauchen wir zwei Variablen, die als ein Zeiger auf die Indizes
		 * der Elemente in den beiden Feldern dienen.
		 * 
		 * So bekommt unser Feld `pers1` den Zeiger `i`
		 * 
		 * `pers1` := [ ][ ]...[ ]
		 *             ^
		 *            `i`
		 * 
		 * und unser Feld `pers2` den Zeiger `j`.
		 * 
		 * `pers2` := [ ][ ]...[ ]
		 *             ^
		 *            `j`
		 * 
		 * Unser ursprüngliches Feld `result` hat schon einen Zeiger
		 * in der Variable `start`, die auf das Erste der Elemente des
		 * Feldes zeigt, die wir mit den Elementen aus `pers1` bzw. `pers2`
		 * überschreiben werden.
		 * 
		 * `result` := [ ][ ]...[ ][ ]...[ ]
		 *                       ^
		 *                    `start`
		 *                       ^
		 *                      `k` (als Alternative; siehe Anmerkung)
		 * 
		 * Mithilfe dieser Zeiger, können wir die Felder traversieren, um Operationen
		 * an denen durchzuführen.
		 * 
		 * Anmerkung: Statt `start` dürften wir auch eine neue Variable `k` verwenden,
		 *   `int k = start;`
		 * weil wir aber keine Information über dem ursprünglichen „Anfang“ im Feld
		 * `result` brauchen, verwenden wir das, was schon vorhanden ist, um sparsamer
		 * mit dem Speicherplatz umzugehen.
		 */
		int i = 0;
		int j = 0;
		
		/**
		 * Als nächstes traversieren wir die beiden Felder `pers1` und `pers2`.
		 * Im jeden Schritt wird das Element an der `i`-ten Stelle im Feld `pers1`
		 * mit dem Element an der `j`-ten Stelle im Feld `pers2` verglichen.
		 * 
		 * Es gibt zwei mögliche Situationen:
		 *   1) `pers1[i] < pers2[j]` und
		 *   2) `pers1[i] >= pers2[j]`.
		 * 
		 * Im ersten Fall, wird das Element `result[start]` mit dem `pers1[i]`
		 * überschrieben. Anschließend werden noch die Zeiger `i` und `start`
		 * um eine Stelle gegen dem Ende der Felder verschoben.
		 * 
		 * Im zweiten Fall, wird das Element `result[start]` mit dem `pers2[j]`
		 * überschrieben. Anschließend werden noch die Zeiger `j` und `start`
		 * um eine Stelle gegen dem Ende der Felder verschoben.
		 * 
		 * Zum Beispiel:
		 * 
		 * Schritt 1:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *             ^                     ^
		 *            `i`                   `j`
		 *             
		 * `result` := [ ]...[8][3][7][0][2][5]...[ ]
		 *                    ^
		 *                 `start`
		 *                 
		 *  `pers1[i] < pers2[j]`? Ja.
		 *  
		 *  `result[start] := pers1[i]; i++; start++` 
		 * 
		 * Schritt 2:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                ^                  ^
		 *               `i`                `j`
		 *             
		 * `result` := [ ]...[0][3][7][0][2][5]...[ ]
		 *                       ^
		 *                    `start`
		 * 
		 *  `pers1[i] < pers2[j]`? Nein.
		 *  
		 *  `result[start] := pers2[j]; j++; start++` 
		 * 
		 * Schritt 3:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                ^                     ^
		 *               `i`                   `j`
		 *             
		 * `result` := [ ]...[0][2][7][0][2][5]...[ ]
		 *                          ^
		 *                       `start`
		 * 
		 *  `pers1[i] < pers2[j]`? Nein.
		 *  
		 *  `result[start] := pers2[j]; j++; start++` 
		 * 
		 * Schritt 4:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                ^                        ^
		 *               `i`                      `j`
		 *             
		 * `result` := [ ]...[0][2][3][0][2][5]...[ ]
		 *                             ^
		 *                          `start`
		 * 
		 *  `pers1[i] < pers2[j]`? Nein.
		 *  
		 *  `result[start] := pers2[j]; j++; start++` 
		 * 
		 * Schritt 5:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                ^                          ^
		 *               `i`                        `j`
		 *             
		 * `result` := [ ]...[0][2][3][5][2][5]...[ ]
		 *                                ^
		 *                             `start`
		 * 
		 *  Unser Index `j` ist außerhalb des Feldes `pers2`,
		 *  also wird die Schleife nicht mehr ausgeführt.
		 */
		while (i < pers1.length && j < pers2.length) {
			if (pers1[i].compareTo(pers2[j]) < 0) {
				result[start] = pers1[i];
				i++;
			} else {
				result[start] = pers2[j];
				j++;
			}
			start++;
		}
		
		/**
		 * Am Ende müssen wir noch die verbleibenden Elemente in
		 * entweder `pers1` oder `pers2` in das ursprüngliche Feld
		 * speichern.
		 * 
		 * Nur eine der nachfolgenden Schleifen wird ausgeführt.
		 * 
		 * Zum Beispiel:
		 * 
		 * Schritt 1:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                ^                          ^
		 *               `i`                        `j`
		 *             
		 * `result` := [ ]...[0][2][3][5][2][5]...[ ]
		 *                                ^
		 *                             `start`
		 * 
		 * `result[start] := pers1[i]; i++; start++`
		 * 
		 * Schritt 2:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                   ^                       ^
		 *                  `i`                     `j`
		 *             
		 * `result` := [ ]...[0][2][3][5][7][5]...[ ]
		 *                                   ^
		 *                                `start`
		 * 
		 * `result[start] := pers1[i]; i++; start++`
		 * 
		 * Schritt 3:
		 * 
		 * `pers1` := [0][7][8]  `pers2` := [2][3][5]
		 *                     ^                     ^
		 *                    `i`                   `j`
		 *             
		 * `result` := [ ]...[0][2][3][5][7][8]...[ ]
		 *                                     ^
		 *                                  `start`
		 * 
		 * Unser Index `i` ist außerhalb des Feldes `pers1`,
		 * also wird die Schleife nicht mehr ausgeführt.
		 */
		while (i < pers1.length) {
			result[start] = pers1[i];
			i++;
			start++;
		}
		while (j < pers2.length) {
			result[start] = pers2[j];
			j++;
			start++;
		}

	}

}
