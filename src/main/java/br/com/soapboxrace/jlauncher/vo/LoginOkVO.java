package br.com.soapboxrace.jlauncher.vo;

public class LoginOkVO {
	private String userId;
	private String loginToken;

	public LoginOkVO(String userId, String loginToken) {
		this.userId = userId;
		this.loginToken = loginToken;

	}

	public String getUserId() {
		return userId;
	}

	public String getLoginToken() {
		return loginToken;
	}

}
