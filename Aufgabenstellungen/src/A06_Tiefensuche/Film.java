package A06_Tiefensuche;

public class Film {

	private final String titel;
	
	private final double laenge;

	public Film(String titel, double laenge) {
		this.titel = titel;
		this.laenge = laenge;
	}

	public String getTitel() {
		return titel;
	}

	public double getLaenge() {
		return laenge;
	}

	@Override
	public String toString() {
		return titel + " (Laenge: " + laenge + ")";
	}

}
