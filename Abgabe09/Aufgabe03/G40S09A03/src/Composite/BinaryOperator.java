package Composite;


import java.util.function.Function;

public class BinaryOperator extends Term {
	private final Function<Double,Function<Double,Double>> binaryOperator;
	private final Term links;
	private final Term rechts;

	//Berechnungsfunktionen
	public static final Function<Double,Function<Double,Double>> plus = x -> y -> x + y;
	public static final Function<Double,Function<Double,Double>> minus = x -> y -> x - y;
	public static final Function<Double,Function<Double,Double>> mult = x -> y -> x * y;
	public static final Function<Double,Function<Double,Double>> div = x -> y -> x / y;
	
	@Override
	public double berechnen() {
		return binaryOperator.apply(links.berechnen()).apply(rechts.berechnen());
	}
	
	public BinaryOperator(Function<Double,Function<Double,Double>> binaryOperator, Term links, Term rechts) {
		this.binaryOperator = binaryOperator;
		this.links = links;
		this.rechts = rechts;
	}
}
