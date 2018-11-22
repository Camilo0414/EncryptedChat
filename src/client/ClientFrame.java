package client;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientFrame extends JFrame {

	private Client client;
	private ClientFramePanel panel ;

	public ClientFrame() {

		client = new Client();

		setBounds(600, 300, 280, 350);

	    panel = new ClientFramePanel();

		add(panel);

		setVisible(true);

		Thread thread = new Thread(client);

		thread.start();
	}

	public ClientFramePanel getPanel() {
		return panel;
	}

	public void setPanel(ClientFramePanel panel) {
		this.panel = panel;
	}
	
	
	

}
