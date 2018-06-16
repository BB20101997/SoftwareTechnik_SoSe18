package kommando;

import kommando.BooleanVector;

public class Main {

	public static void main(String[] args) {

		System.out.println("Kommando Tests");
		
		//test shift left
		boolean[] inputValues = { false, true, false };
		BooleanVector vector = new BooleanVector(inputValues);

		vector.shiftLeft();

		boolean[] expectedValues = new boolean[] { true, false, false };
		BooleanVector expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("shiftleft läuft");
		}

		vector.undo();

		expectedValues = new boolean[] { false, true, false };
		expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("undo von shiftleft läuft");
		}

		vector.redo();
		
		expectedValues = new boolean[] { true, false, false };
		expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("redo von shiftleft läuft");
		}
		
		//test shift right
		
		inputValues = new boolean[]{ true, false, false };
		vector      = new BooleanVector(inputValues);

		vector.shiftRight();

		expectedValues = new boolean[] { false, true, false };
	    expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("shiftright läuft");
		}

		vector.undo();

		expectedValues = new boolean[]{ true, false, false };  
		expectedVector = new BooleanVector(inputValues);       

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("undo von shiftright läuft");
		}

		vector.redo();
		
		expectedValues = new boolean[] { false, true, false }; 
		expectedVector = new BooleanVector(expectedValues);    

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("redo von shiftright läuft");
		}
		
		//test invert
		
		inputValues = new boolean[] { true, false, false };
		vector = new BooleanVector(inputValues);

		vector.invert();

		expectedValues = new boolean[] { false, true, true };
		expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("invert läuft");
		}

		vector.undo();

		expectedValues = new boolean[] { true, false, false };
		expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("undo von invert läuft");
		}

		vector.redo();
		
		expectedValues = new boolean[]{ false, true, true };
		expectedVector = new BooleanVector(expectedValues);

		if (!vector.equals(expectedVector)) {
			throw new IllegalStateException(vector.toString());
		} else {
			System.out.println("redo von invert läuft");
		}

	}

}
