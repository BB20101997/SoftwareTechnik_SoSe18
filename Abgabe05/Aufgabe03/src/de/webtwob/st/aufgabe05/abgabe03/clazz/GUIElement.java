package de.webtwob.st.aufgabe05.abgabe03.clazz;

import de.webtwob.st.aufgabe05.abgabe03.interfaze.IGUIElement;

public class GUIElement implements IGUIElement {
	
	private int x;
	private int y;
	private String name;

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
