package mvc;

public final class Controller {
	
	private Model model = new Model();
	private FirstView firstView = new FirstView(this);
	private SecondView secondView = new SecondView();
	
	public Controller() {
		//setup both views with the model
		
		/*
		 * add both views setText method as implementations of the Consumer<String> Strategy to wait for content changes
		 * */
		model.addInputReadyListener(secondView::setText);
		model.addInputReadyListener(firstView::setText);
		
		/*
		 * make the controller update the models state when a property change is received
		 * */
		firstView.addPropertyChangeListener(FirstView.INPUT_READY, event -> model.setContent(firstView.getInput()));
	}

	public void startApplication() {
		/*
		 * make everything visible on application start
		 * */
		firstView.setVisible(true);
		secondView.setVisible(true);
	}
}
