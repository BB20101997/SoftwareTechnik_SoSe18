package de.webtwob.st.aufgabe05.abgabe03.interfaze;

public interface IPictureElement extends IGUIElement{
	
	String getPictureFileName();
	
	void setPictureFileName(String pictureFileName);
	
	default void draw() {
		IGUIElement.super.draw();
		System.out.println("PictureFileName:"+getPictureFileName());
		
	}

}
