package mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public final class Model {

	private String content;

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;

		/**
		 * Run all added Consumer<String> Strategy implementations to inform all observers about the content having changed
		 * */
		inputReadyListeners.forEach(run->run.accept(content));
	}
	
	/**
	 * Strategy Pattern combined with observer Pattern Consumer<T> is the Strategies interface
	 * and the addInputReadyListener is part of the Observer Pattern
	 * */
	public void addInputReadyListener(Consumer<String> run) {
		inputReadyListeners.add(run);
	}
	

	private List<Consumer<String>> inputReadyListeners = new LinkedList<>();

}
