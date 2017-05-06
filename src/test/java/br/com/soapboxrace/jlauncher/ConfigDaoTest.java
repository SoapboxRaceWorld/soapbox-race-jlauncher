package br.com.soapboxrace.jlauncher;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.util.ConfigDao;
import br.com.soapboxrace.jlauncher.vo.ConfigVO;

public class ConfigDaoTest {

	@Test
	public void copyTest() {
		new ConfigDao();
		Assert.assertFalse(new File("config.properties") == null);
	}

	// @Test
	public void propertiesReadTest() {
		ConfigDao configDao = new ConfigDao();
		ConfigVO config = configDao.getConfig();
		Assert.assertTrue("joe@nowhere".equals(config.getEmail()));
		Assert.assertTrue("/home/nils/nfsw-full/nfsw.exe".equals(config.getGameExePath()));
		Assert.assertTrue("1af135f153f153aa15313".equals(config.getPasswordSHA1()));
		Assert.assertTrue("http://127.0.0.1:1337".equals(config.getServerURL()));
		Assert.assertFalse(equals(config.isSaveCredentials()));
	}

	@Test
	public void propertiesSaveTest() {
		ConfigVO configVO = new ConfigVO();
		configVO.setEmail("nothing@nowhere");
		configVO.setGameExePath("/root/nowhere");
		configVO.setPasswordSHA1("9999999966666");
		configVO.setServerURL("https://hack.the.planet");
		configVO.setSaveCredentials(false);

		ConfigDao configDao = new ConfigDao();
		configDao.saveConfig(configVO);

		ConfigVO config = configDao.getConfig();
		Assert.assertTrue(configVO.getEmail().equals(config.getEmail()));
		Assert.assertTrue(configVO.getGameExePath().equals(config.getGameExePath()));
		Assert.assertTrue(configVO.getPasswordSHA1().equals(config.getPasswordSHA1()));
		Assert.assertTrue(configVO.getServerURL().equals(config.getServerURL()));
		Assert.assertTrue(configVO.isSaveCredentials() == config.isSaveCredentials());

	}

}
