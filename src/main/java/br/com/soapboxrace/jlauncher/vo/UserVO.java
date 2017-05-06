package br.com.soapboxrace.jlauncher.vo;

import org.apache.commons.codec.digest.DigestUtils;

public class UserVO {

	private String email;
	private String password;
	private String shaPassword;
	private String inviteTicket;

	public UserVO(String email, String password) {
		this.email = email;
		this.password = password;
		this.shaPassword = DigestUtils.sha1Hex(password);
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getShaPassword() {
		return shaPassword;
	}

	public String getInviteTicket() {
		return inviteTicket;
	}

	public void setInviteTicket(String inviteTicket) {
		this.inviteTicket = inviteTicket;
	}

	public void setShaPassword(String shaPassword) {
		this.shaPassword = shaPassword;
	}

}
