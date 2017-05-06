package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.util.HttpRequest;
import br.com.soapboxrace.jlauncher.util.ParseXml;
import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.RequestVO;
import br.com.soapboxrace.jlauncher.vo.ServerErrorVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class HttpRequestTest {

	@Test
	public void testAuthUrl() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		String url = httpRequest.getLoginURL(userVO);
		String expected = "http://127.0.0.1:1337/soapbox-race-core/Engine.svc/User/authenticateUser?email=joe@email&password=a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";
		Assert.assertTrue(expected.equals(url));
	}

	@Test
	public void testLauncherError() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		String url = httpRequest.getLoginURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		Assert.assertTrue(login.getResponseCode() == 500);
		ServerErrorVO parseError = parseXml.parseError();
		Assert.assertTrue(parseError.getDescription().contains("Launcher error"));
	}

	@Test
	public void testEmailFail() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		String url = httpRequest.getLoginURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		Assert.assertTrue(login.getResponseCode() == 500);
		ServerErrorVO parseError = parseXml.parseError();
		Assert.assertTrue(parseError.getDescription().contains("Login Error: Email wasn't found!"));
	}

	@Test
	public void testPasswordFail() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "testz");
		String url = httpRequest.getLoginURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		Assert.assertTrue(login.getResponseCode() == 500);
		ServerErrorVO parseError = parseXml.parseError();
		Assert.assertTrue(parseError.getDescription().contains("Login Error: Incorrect email-password combination!"));
	}

	@Test
	public void testLoginOk() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		String url = httpRequest.getLoginURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		Assert.assertTrue(login.getResponseCode() == 200);
		LoginOkVO parseOk = parseXml.parseOk();
		Assert.assertTrue("1".equals(parseOk.getUserId()));
	}

	@Test
	public void testCreateUrl() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		userVO.setInviteTicket("1234");
		String url = httpRequest.getCreateURL(userVO);
		String expected = "http://127.0.0.1:1337/soapbox-race-core/Engine.svc/User/createUser?email=joe@email&password=a94a8fe5ccb19ba61c4c0873d391e987982fbbd3&inviteTicket=1234";
		Assert.assertTrue(expected.equals(url));
	}

	@Test
	public void testCreateFail() {
		HttpRequest httpRequest = new HttpRequest("http://127.0.0.1:1337");
		UserVO userVO = new UserVO("joe@email", "test");
		userVO.setInviteTicket("1234");
		String url = httpRequest.getCreateURL(userVO);
		RequestVO login = httpRequest.doRequest(url);
		ParseXml parseXml = new ParseXml(login.getResponse());
		Assert.assertTrue(login.getResponseCode() == 500);
		ServerErrorVO parseError = parseXml.parseError();
		Assert.assertTrue(parseError.getDescription().contains("Registration Error:"));
	}
}
