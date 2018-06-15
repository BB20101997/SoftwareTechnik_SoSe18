package mvc;

import javax.swing.JFrame;
import javax.swing.JLabel;

public final class SecondView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel label;

	public SecondView() {
		super("Second View");

		this.label = new JLabel("N/A");
		super.getContentPane().add(this.label);
		super.pack();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setText(String labelText) {
		//this implements a method that can be used for the Consumer<String> Strategy
		label.setText(labelText);
	}

}
