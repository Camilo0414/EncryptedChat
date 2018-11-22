package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFramePanel extends JPanel implements ActionListener {

	private JButton btnSend;
	private JButton btnValidate;
	private JTextField tfMessage;
	private JTextArea taChat;
	private JTextField tfIp;
	private JLabel jlNick;
	private Client client;
	private DiffieHellman dh;

	public ClientFramePanel() {
		// TODO Auto-generated constructor stub
		String nick = JOptionPane.showInputDialog("Nick: ");
		tfMessage = new JTextField(20);

		dh = new DiffieHellman();

		tfIp = new JTextField(8);

		JLabel jlIp = new JLabel("ip: ");

		jlNick = new JLabel(nick);

		client = new Client();

		btnSend = new JButton("Send");

		btnValidate = new JButton("Validate");

		taChat = new JTextArea(12, 20);

		taChat.setEditable(false);

		btnValidate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tfIp.getText().equals("") || jlNick.getText().equals("")) {
					JOptionPane.showMessageDialog(btnValidate, "I need the IP and your Nick :)");
				} else {

					String value = JOptionPane.showInputDialog("Type your number: ");
					dh.setX(new BigInteger(value));

					String value_converted = dh.convert(value);
					client.sendValidation(value_converted, tfIp.getText(), jlNick.getText());
				}
			}
		});

		btnSend.addActionListener(this);

		add(jlNick);
		add(new JLabel(" - CHAT - "));
		add(jlIp);
		add(tfIp);
		add(taChat);
		add(tfMessage);
		// add(btnValidate);

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

	public DiffieHellman getDh() {
		return dh;
	}

	public void setDh(DiffieHellman dh) {
		this.dh = dh;
	}

}
