package Composite;

import java.util.function.Function;

public class UnaryOperator extends Term {
	private final Term term;
	private final Function<Double,Double> unaryOperator;

	//Unäre Operationen
	public static final Function<Double,Double> minus = x -> - x;
	public static final Function<Double,Double> sin = x -> Math.sin(x);
	

	public UnaryOperator(Function<Double,Double> unaryOperator, Term term) {
		this.unaryOperator = unaryOperator;
		this.term = term;
	}
	
	@Override
	public double berechnen() {
		return unaryOperator.apply(term.berechnen());
	}
	
}