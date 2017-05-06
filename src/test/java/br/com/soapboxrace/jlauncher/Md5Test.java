package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.util.Md5Files;

public class Md5Test {

	@Test
	public void md5Test() {
		boolean checkExeFile = Md5Files.checkExeFile("/home/nils/jogos/nfsw/nfsw.exe");
		Assert.assertTrue(checkExeFile);
	}

	@Test
	public void md5TestModules() {
		boolean checkModules = Md5Files.checkModules("/home/nils/jogos/output-tests");
		Assert.assertTrue(checkModules);
	}
}
