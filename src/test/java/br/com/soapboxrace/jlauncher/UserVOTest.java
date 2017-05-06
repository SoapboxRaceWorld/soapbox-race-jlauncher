package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.vo.UserVO;

public class UserVOTest {

	@Test
	public void testSha1() {
		UserVO userVO = new UserVO("joe@email", "test");
		Assert.assertTrue(userVO.getShaPassword().equals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"));
	}
}
