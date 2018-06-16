package kommando;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Supplier;

public final class BooleanVector {

	private static final int MAX_UNDO_STEPS = 10;

	private final boolean[] vector;

	Deque<ICommand> undoStack = new LinkedList<>();
	Deque<ICommand> redoStack = new LinkedList<>();

	public BooleanVector(boolean[] inputValues) {
		this.vector = inputValues;
	}

	/**
	 * (TRUE,FALSE,FALSE) invert returns: (FALSE,TRUE,TRUE) <br/>
	 * (TRUE,TRUE,TRUE) invert returns: (FALSE,FALSE,FALSE)
	 */
	public void invert() {

		ICommand invert = new Invert(this);
		invert.run();

		undoStack.push(invert);
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
	}

	/**
	 * (TRUE,FALSE,FALSE) shift left returns: (FALSE,FALSE,TRUE) <br/>
	 * (FALSE,TRUE,FALSE) shift left returns: (TRUE,FALSE,FALSE)
	 */
	public void shiftLeft() {
		ICommand sL = new ShiftLeft(this);
		sL.run();

		undoStack.push(sL);
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
	}

	/**
	 * (TRUE,FALSE,FALSE) shift right returns: (FALSE,TRUE,FALSE) <br/>
	 * (FALSE,FALSE,TRUE) shift right returns: (TRUE,FALSE,FALSE)
	 */
	public void shiftRight() {

		ICommand sR = new ShiftRight(this);
		sR.run();

		undoStack.push(sR);
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
	}

	@Override
	// do not change
	public String toString() {
		return Arrays.toString(this.vector);
	}

	@Override
	// do not change
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof BooleanVector))
			return false;
		BooleanVector otherVector = (BooleanVector) obj;
		return Arrays.equals(this.vector, otherVector.vector);
	}

	public void undo() {
		ICommand com = undoStack.pop();
		ICommand undo = com.get();
		
		undo.run();
		redoStack.push(undo);
		if (redoStack.size() > MAX_UNDO_STEPS) {
			redoStack.removeLast();
		}
	}

	public void redo() {
		ICommand com = redoStack.pop();
		ICommand undo = com.get();
		
		undo.run();
		undoStack.push(undo);
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
	}

	private interface ICommand extends Runnable, Supplier<ICommand> {
	}

	private static class ShiftLeft implements ICommand {

		BooleanVector vector;

		public ShiftLeft(BooleanVector vect) {
			vector = vect;
		}

		@Override
		public void run() {

			final boolean firstValue = vector.vector[0];
			for (int i = 1; i < vector.vector.length; i++) {
				vector.vector[i - 1] = vector.vector[i];
			}
			vector.vector[vector.vector.length - 1] = firstValue;
		}

		@Override
		public ICommand get() {
			return new ShiftRight(vector);
		}

	}

	private static class ShiftRight implements ICommand {

		BooleanVector vector;

		public ShiftRight(BooleanVector vect) {
			vector = vect;
		}

		@Override
		public void run() {

			final boolean lastValue = vector.vector[vector.vector.length - 1];
			for (int i = vector.vector.length - 1; i > 0; i--) {
				vector.vector[i] = vector.vector[i - 1];
			}
			vector.vector[0] = lastValue;
		}

		@Override
		public ICommand get() {
			return new ShiftLeft(vector);
		};

	}

	private static class Invert implements ICommand {
		BooleanVector vector;

		public Invert(BooleanVector vect) {
			vector = vect;
		}

		@Override
		public void run() {
			for (int i = 0; i < vector.vector.length; i++) {
				vector.vector[i] = !vector.vector[i];
			}

		}

		@Override
		public ICommand get() {
			return new Invert(vector);
		}

	}
}
