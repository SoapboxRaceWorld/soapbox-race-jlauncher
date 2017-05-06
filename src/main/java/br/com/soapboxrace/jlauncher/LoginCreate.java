package br.com.soapboxrace.jlauncher;

import br.com.soapboxrace.jlauncher.util.HttpRequest;
import br.com.soapboxrace.jlauncher.util.ParseXml;
import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.RequestVO;
import br.com.soapboxrace.jlauncher.vo.ServerErrorVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class LoginCreate {

	private String url;
	private String message;
	private LoginOkVO loginOkVO;
	private UserVO userVO;

	public LoginCreate(String url, UserVO userVO) {
		this.url = url;
		this.userVO = userVO;
	}

	public boolean doLogin() {
		HttpRequest httpRequest = new HttpRequest(url);
		String url = httpRequest.getLoginURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		if (login.getResponseCode() == 200) {
			loginOkVO = parseXml.parseOk();
			message = "Login OK!";
			return true;
		}
		ServerErrorVO parseError = parseXml.parseError();
		message = parseError.getDescription();
		return false;
	}

	public boolean doCreate(String password2, String ticket) {
		if (!password2.equals(userVO.getPassword())) {
			message = "Different password confirmation!";
			return false;
		}
		HttpRequest httpRequest = new HttpRequest(url);
		userVO.setInviteTicket(ticket);
		String url = httpRequest.getCreateURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		if (login.getResponseCode() == 200) {
			loginOkVO = parseXml.parseOk();
			message = "Account created!";
			return true;
		}
		ServerErrorVO parseError = parseXml.parseError();
		message = parseError.getDescription();
		return false;
	}

	public String getMessage() {
		return message;
	}

	public LoginOkVO getLoginOkVO() {
		return loginOkVO;
	}

}
