package Composite;

public class Main {

	public static void main(String[] args) {

		Term z20 = new Zahl(20);
		Term z13 = new Zahl(13);
		Term zStandard = new Zahl(0);
		Term term = new BinaryOperator(BinaryOperator.plus, z20, z13);
		Term sin = new UnaryOperator(UnaryOperator.sin, z20);
		Term minus = new UnaryOperator(UnaryOperator.minus, z13);

		Term term2 = Zahl.stuNummer;
		if (term2.berechnen() != 204017.0) {
			System.out.println("Fehler : stuNummer entspricht nicht der stuNummer ");
			return;
		}
		;

		if (term.berechnen() != 33.0) {
			System.out.println("Addieren kannst du wohl nicht");
			return;
		}
		;

		if (!(sin.berechnen() >= 0.91 && sin.berechnen() <= 0.915)) {
			System.out.println("Das üben wir aber nochmal");
			return;
		}
		;

		if (minus.berechnen() != -13.0) {
			System.out.println("Negativ *hehe");
			return;
		}
		;

		if (zStandard.berechnen() != 0) {
			System.out.println("Du Null!");
			return;
		}
		;
		
		System.out.println("Keine Fehler gefunden");

	}
}
