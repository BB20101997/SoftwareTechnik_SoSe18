package de.webtwob.st.aufgabe05.abgabe03.interfaze;

public interface IClickableElement extends IGUIElement{

	String getMessage();
	
	void setMessage(String message);
	
	default void draw() {
		IGUIElement.super.draw();
		System.out.println("Message:" + getMessage());
	}
	
}
