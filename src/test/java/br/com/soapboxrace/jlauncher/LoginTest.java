package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class LoginTest {

	@Test
	public void testLoginOk() {
		UserVO userVO = new UserVO("joe@email", "test");
		LoginCreate login = new LoginCreate("http://127.0.0.1:1337", userVO);
		boolean doLogin = login.doLogin();
		Assert.assertTrue(doLogin);
		LoginOkVO loginOkVO = login.getLoginOkVO();
		Assert.assertTrue(loginOkVO != null);
		Assert.assertTrue("1".equals(loginOkVO.getUserId()));
	}

	@Test
	public void testLoginFail() {
		UserVO userVO = new UserVO("joe@email", "testz");
		LoginCreate login = new LoginCreate("http://127.0.0.1:1337", userVO);
		boolean doLogin = login.doLogin();
		Assert.assertTrue(!doLogin);
		LoginOkVO loginOkVO = login.getLoginOkVO();
		Assert.assertTrue(loginOkVO == null);
		Assert.assertTrue(login.getMessage().contains("Login Error:"));
	}

}
