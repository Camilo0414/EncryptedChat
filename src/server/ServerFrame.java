package server;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame {

	private JTextArea taChat;
	private Server server;

	public ServerFrame() {
		this.server = new Server();
		setBounds(1200, 300, 280, 350);

		JPanel panelChat = new JPanel();

		panelChat.setLayout(new BorderLayout());

		taChat = new JTextArea();

		panelChat.add(taChat, BorderLayout.CENTER);

		add(panelChat);

		setVisible(true);

		Thread thread = new Thread(server);

		thread.start();

	}

}
