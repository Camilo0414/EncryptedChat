package client;

import java.io.Serializable;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 288451282991981084L;
	private String ip, msg, nick; 
	private boolean isDiffie;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public boolean isDiffie() {
		return isDiffie;
	}

	public void setDiffie(boolean isDiffie) {
		this.isDiffie = isDiffie;
	}
	
}
