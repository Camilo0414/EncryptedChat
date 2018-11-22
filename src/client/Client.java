package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client implements Runnable {

	private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
	private InetAddress direccion;
	private Message objectMessage;
	private static ClientFramePanel panel;

	public static void main(String[] args) {

		ClientFrame client_frame = new ClientFrame();
		panel = client_frame.getPanel();

		client_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void sendMessage(String msg, String ip, String nick) {
		// TODO Auto-generated method stub
		try {

			direccion = InetAddress.getByName("192.168.161.27");
			objectMessage = new Message();

			Socket client = new Socket(direccion, 5555);

			ObjectOutputStream data = new ObjectOutputStream(client.getOutputStream());

			Message message = new Message();

			message.setMsg(msg);
			message.setIp(ip);
			message.setNick(nick);

			data.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			/* panel = new ClientFramePanel(); */

			ServerSocket server_client = new ServerSocket(5555);

			Socket client;

			while (true) {

				client = server_client.accept();

				ObjectInputStream in = new ObjectInputStream(client.getInputStream());

				Message msg = (Message) in.readObject();

				panel.updateChat(msg);

				bufferedWriter.write("Mensaje de " + msg.getNick() + " " + msg.getMsg() + "\n");
				bufferedWriter.flush();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Message getObjectMessage() {
		return objectMessage;
	}

	public void setObjectMessage(Message objectMessage) {
		this.objectMessage = objectMessage;
	}

}
