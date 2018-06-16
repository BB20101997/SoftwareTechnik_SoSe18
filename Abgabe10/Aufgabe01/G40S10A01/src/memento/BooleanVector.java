package memento;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public final class BooleanVector {

	private class Memento {
		private final boolean[] state;

		public Memento(boolean[] toBeCopied) {
			state = Arrays.copyOf(toBeCopied, toBeCopied.length);
		}

		public boolean[] getState() {
			return Arrays.copyOf(state, state.length);
		}
	}

	private static final int MAX_UNDO_STEPS = 10;

	Deque<Memento> undoStack = new LinkedList<>();
	Deque<Memento> redoStack = new LinkedList<>();

	private final boolean[] vector;

	public BooleanVector(boolean[] inputValues) {
		this.vector = inputValues;
	}

	/**
	 * (TRUE,FALSE,FALSE) invert returns: (FALSE,TRUE,TRUE) <br/>
	 * (TRUE,TRUE,TRUE) invert returns: (FALSE,FALSE,FALSE)
	 */
	public void invert() {
		undoStack.push(new Memento(vector));
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
		for (int i = 0; i < this.vector.length; i++) {
			this.vector[i] = !this.vector[i];
		}
	}

	/**
	 * (TRUE,FALSE,FALSE) shift left returns: (FALSE,FALSE,TRUE) <br/>
	 * (FALSE,TRUE,FALSE) shift left returns: (TRUE,FALSE,FALSE)
	 */
	public void shiftLeft() {
		undoStack.push(new Memento(vector));
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
		final boolean firstValue = this.vector[0];
		for (int i = 1; i < this.vector.length; i++) {
			this.vector[i - 1] = this.vector[i];
		}
		this.vector[vector.length - 1] = firstValue;
	}

	/**
	 * (TRUE,FALSE,FALSE) shift right returns: (FALSE,TRUE,FALSE) <br/>
	 * (FALSE,FALSE,TRUE) shift right returns: (TRUE,FALSE,FALSE)
	 */
	public void shiftRight() {
		undoStack.push(new Memento(vector));
		if (undoStack.size() > MAX_UNDO_STEPS) {
			undoStack.removeLast();
		}
		final boolean lastValue = this.vector[vector.length - 1];
		for (int i = this.vector.length - 1; i > 0; i--) {
			this.vector[i] = this.vector[i - 1];
		}
		this.vector[0] = lastValue;
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
		redoStack.push(new Memento(vector));
		boolean[] old =  undoStack.pop().getState();
		System.arraycopy(old, 0, vector, 0, old.length);
	}

	public void redo() {
		undoStack.push(new Memento(vector));
		boolean[] old =  redoStack.pop().getState();
		System.arraycopy(old, 0, vector, 0, old.length);
	}
}
