package de.webtwob.st.aufgabe05.abgabe03.clazz;

import de.webtwob.st.aufgabe05.abgabe03.interfaze.IClickableElement;

public class ClickablePicture extends PictureElement implements IClickableElement {

	private IClickableElement delegateClickableElement = new ClickableElement();
	
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		delegateClickableElement.setPosition(x, y);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		delegateClickableElement.setName(name);
	}

	
	@Override
	public String getMessage() {
		return delegateClickableElement.getMessage();
	}

	@Override
	public void setMessage(String message) {
		delegateClickableElement.setMessage(message);

	}
	
	public void draw() {
		super.draw();
		System.out.println("DelegateClickableElement:");
		delegateClickableElement.draw();
	}

}
