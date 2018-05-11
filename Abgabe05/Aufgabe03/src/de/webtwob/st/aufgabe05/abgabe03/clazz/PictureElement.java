package de.webtwob.st.aufgabe05.abgabe03.clazz;

import de.webtwob.st.aufgabe05.abgabe03.interfaze.IPictureElement;

public class PictureElement extends GUIElement implements IPictureElement{
	
	private String pictureFileName;

	@Override
	public String getPictureFileName() {
		return pictureFileName;
	}

	@Override
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

}
