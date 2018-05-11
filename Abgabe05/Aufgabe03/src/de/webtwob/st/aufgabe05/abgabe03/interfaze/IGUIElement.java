package de.webtwob.st.aufgabe05.abgabe03.interfaze;

public interface IGUIElement {

	void setPosition(int x, int y);
	
	int getX();
	
	int getY();
	
	String getName();
	
	void setName(String name);
	
	/**
	 * Return-Type was not specified assuming void
	 * */
	default void draw() {
		System.out.println("Classname:" + getClass().getSimpleName());
		System.out.println("Name:" + getName() );
		System.out.println("X:"+getX() + " Y:"+getY());
	};
	
}
