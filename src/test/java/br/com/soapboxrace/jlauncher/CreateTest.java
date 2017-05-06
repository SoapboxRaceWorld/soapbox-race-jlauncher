package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class CreateTest {

	@Test
	public void testCreatePasswordFail() {
		UserVO userVO = new UserVO("joe@email", "testz");
		LoginCreate login = new LoginCreate("http://127.0.0.1:1337", userVO);
		boolean doCreate = login.doCreate("test", "1234");
		Assert.assertTrue(!doCreate);
		LoginOkVO loginOkVO = login.getLoginOkVO();
		Assert.assertTrue(loginOkVO == null);
		Assert.assertTrue(login.getMessage().contains("Different password confirmation!"));
	}

	@Test
	public void testCreateFail() {
		UserVO userVO = new UserVO("joe@email", "testz");
		LoginCreate login = new LoginCreate("http://127.0.0.1:1337", userVO);
		boolean doCreate = login.doCreate("testz", "1234");
		Assert.assertTrue(!doCreate);
		LoginOkVO loginOkVO = login.getLoginOkVO();
		Assert.assertTrue(loginOkVO == null);
		Assert.assertTrue(login.getMessage().contains("Registration Error:"));
	}

}
