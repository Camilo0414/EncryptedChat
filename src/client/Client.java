package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client implements Runnable {

	private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
	private InetAddress direccion;
	private static ClientFramePanel panel;
//	private SecretKey secretKey;
//	private Cipher aesCipher;

	public static void main(String[] args) {

		ClientFrame client_frame = new ClientFrame();
		panel = client_frame.getPanel();

		client_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void sendMessage(String msg, String ip, String nick) {
		// TODO Auto-generated method stub
		try {

			direccion = InetAddress.getByName("192.168.161.27");

			Socket client = new Socket(direccion, 5555);

			ObjectOutputStream data = new ObjectOutputStream(client.getOutputStream());

			Message message = new Message();

			// KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			//
			// keyGen.init(128);
			//
			// secretKey = keyGen.generateKey();
			//
			// aesCipher = Cipher.getInstance("AES");
			//
			// aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			//
			// byte[] byteDataToEncrypt = msg.getBytes();
			//
			// byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
			// String strCipherText = Base64.getEncoder().encodeToString(byteCipherText);
			// bufferedWriter.write("Cipher text generated using AES is " + strCipherText);
			// bufferedWriter.flush();

			// message.setMsg(strCipherText);
			message.setMsg(msg);
			message.setIp(ip);
			message.setNick(nick);
			message.setDiffie(false);
//			message.setByteCypherText(byteCipherText);

			data.writeObject(message);
		} catch (IOException /*
								 * | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
								 * IllegalBlockSizeException | BadPaddingException
								 */ e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendValidation(String msg, String ip, String nick) {
		try {
			direccion = InetAddress.getByName("192.168.161.27");

			Message message = new Message();

			message.setMsg(msg);
			message.setIp(ip);
			message.setNick(nick);
			message.setDiffie(true);
			Socket client = new Socket(direccion, 5555);

			ObjectOutputStream data = new ObjectOutputStream(client.getOutputStream());

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

			// DiffieHellman df = panel.getDh();

			ServerSocket server_client = new ServerSocket(5555);

			Socket client;

			while (true) {

				client = server_client.accept();

				ObjectInputStream in = new ObjectInputStream(client.getInputStream());

				Message msg = (Message) in.readObject();

				// if (msg.isDiffie()) {
				//
				// bufferedWriter.write(msg.getMsg() + " " + df.getKey());
				// bufferedWriter.flush();
				//
				// df.setKey(new BigInteger(msg.getMsg()).modPow(df.getX(), df.getPrime_1()));
				// df.isSafe(df.getA(), df.getX(), df.getPrime_1());
				//
				// } else {

				// KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				//
				// keyGen.init(128);
				//
				// secretKey = keyGen.generateKey();
				// aesCipher = Cipher.getInstance("AES");
				//
				// aesCipher.init(Cipher.DECRYPT_MODE, secretKey, aesCipher.getParameters());
				// byte[] byteDecryptedText = aesCipher.doFinal(msg.getByteCypherText());
				// String strDecryptedText = new String(byteDecryptedText);
				// bufferedWriter.write("Decrypted text message is " + strDecryptedText);
				// bufferedWriter.flush();
				// msg.setMsg(strDecryptedText);
				panel.updateChat(msg);

				//bufferedWriter.write("Mensaje de " + msg.getNick() + " " + msg.getMsg() + "\n");
				//bufferedWriter.flush();
				// }

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
