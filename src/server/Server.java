package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import client.Message;

public class Server implements Runnable {

	private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
	private Message objectMessage;

	public static void main(String[] args) {
		ServerFrame frame = new ServerFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void run() {
		objectMessage = new Message();
		// TODO Auto-generated method stub
		// for (int i = 0; i < 100; i++) {
		// try {
		// bufferedWriter.write("Este hilo va en: " + i + "\n");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		try {
			bufferedWriter.write("Testing chat");
			bufferedWriter.flush();

			ServerSocket server = new ServerSocket(5555);
			while (true) {
				Socket client_server = server.accept();
				ObjectInputStream data = new ObjectInputStream(client_server.getInputStream());
				objectMessage = (Message) data.readObject();
				bufferedWriter.write("\n" + objectMessage.getMsg() + "");
				bufferedWriter.flush();

				Socket send_destiny = new Socket(objectMessage.getIp(), 5555);
				ObjectOutputStream send_package = new ObjectOutputStream(send_destiny.getOutputStream());
				send_package.writeObject(objectMessage);

				send_package.close();
				send_destiny.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
