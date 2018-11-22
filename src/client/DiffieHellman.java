package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JOptionPane;

public class DiffieHellman {

	private BigInteger prime_1, prime_2, X, A, key;
	private boolean safe;
	private Client client;

	public DiffieHellman() {
		this.prime_1 = BigInteger.probablePrime(1048, new Random());
		this.prime_2 = new BigInteger("2");
	}

	

	public String convert(String value) {
		String number = "";

		A = prime_2.modPow(new BigInteger(value), prime_1);
		number = A + "";

		return number;
	}

	public BigInteger getKey() {
		return key;
	}

	public void setKey(BigInteger key) {
		this.key = key;
	}

	public BigInteger getX() {
		return X;
	}

	public void setX(BigInteger x) {
		X = x;
	}

	public BigInteger getA() {
		return A;
	}

	public void setB(BigInteger a) {
		A = a;
	}

	public BigInteger getPrime_1() {
		return prime_1;
	}

	public void setPrime_1(BigInteger prime_1) {
		this.prime_1 = prime_1;
	}

	public void isSafe(BigInteger A, BigInteger y, BigInteger n) {
		BigInteger big = A.modPow(y, n);
		System.out.println("A: " + A);
		System.out.println("y: " + y);
		System.out.println("n: " + n);

		if (big == key) {
			safe = true;
		} else {
			safe = false;
		}

		System.out.println(safe);
		System.out.println(big);
		System.out.println(key);

	}

}
