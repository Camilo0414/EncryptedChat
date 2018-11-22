package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFramePanel extends JPanel implements ActionListener {

	private JButton btnSend;
	private JTextField tfMessage;
	private JTextArea taChat;
	private JTextField tfIp;
	private JLabel jlNick;
	private Client client;

	public ClientFramePanel() {
		// TODO Auto-generated constructor stub
		String nick = JOptionPane.showInputDialog("Nick: ");
		tfMessage = new JTextField(20);

		tfIp = new JTextField(8);

		JLabel jlIp = new JLabel("ip: ");

		JLabel jlNick = new JLabel(nick);

		client = new Client();

		btnSend = new JButton("Send");

		taChat = new JTextArea(12, 20);

		taChat.setEditable(false);

		btnSend.addActionListener(this);

		add(jlNick);
		add(new JLabel("-- CHAT --"));
		add(jlIp);
		add(tfIp);
		add(taChat);
		add(tfMessage);
		add(btnSend);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		client.sendMessage(tfMessage.getText(), tfIp.getText(), jlNick.getText());
		taChat.append("From you: " + tfMessage.getText() + "\n");
		tfMessage.setText("");

	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}

	public JTextField getTfMessage() {
		return tfMessage;
	}

	public void setTfMessage(JTextField tfMessage) {
		this.tfMessage = tfMessage;
	}

	public JTextArea getTaChat() {
		return taChat;
	}

	public void setTaChat(JTextArea taChat) {
		this.taChat = taChat;
	}

	public JTextField getTfIp() {
		return tfIp;
	}

	public void setTfIp(JTextField tfIp) {
		this.tfIp = tfIp;
	}

	public void updateChat(Message msg) {
		// TODO Auto-generated method stub
		this.taChat.append("From " + msg.getNick() + ": " + msg.getMsg().toString() + "\n");

	}

}
