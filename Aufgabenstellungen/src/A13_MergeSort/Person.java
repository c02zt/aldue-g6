package A13_MergeSort;

public class Person implements Comparable<Person> {
	
	private final String nachname;
	
	private final String vorname;

	public Person(String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Vergleicht zwei Personen miteinander
	 * @return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
	 */
	public int compareTo(Person p) {
		/**
		 * Vergleiche Nachnamen.
		 */
		int compare = this.getNachname().compareTo(p.getNachname()); 
		
		/**
		 * Vergleiche Vornamen, wenn Nachnamen gleich sind.
		 */
		if (compare == 0) {
			return this.getVorname().compareTo(p.getVorname());
		}
		
		return compare;
	}
}
