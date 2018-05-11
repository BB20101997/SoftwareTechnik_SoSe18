package de.webtwob.st.aufgabe05.abgabe03.clazz;

import de.webtwob.st.aufgabe05.abgabe03.interfaze.IClickableElement;

public class ClickableElement extends GUIElement implements IClickableElement {
	
	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
