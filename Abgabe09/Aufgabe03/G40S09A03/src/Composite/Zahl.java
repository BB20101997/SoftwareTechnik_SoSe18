package Composite;

public class Zahl extends Term {

	private final double wert;

	// Für den Test später
	public static final Zahl stuNummer = new Zahl(204017);

	public Zahl(double wert) {
		this.wert = wert;
	}

	@Override
	public double berechnen() {
		return wert;
	}

}
