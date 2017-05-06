package br.com.soapboxrace.jlauncher;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.util.CopyFiles;

public class CopyFilesTest {

	@Test
	public void copyTest() {
		CopyFiles.copyFile("config.properties", "/home/nils/jogos/output-tests/config.properties");
		Assert.assertFalse(new File("/home/nils/jogos/output-tests/config.properties") == null);
	}

	@Test
	public void copyAllTest() {
		String pathTo = "/home/nils/jogos/output-tests";
		CopyFiles.copyAllFiles(pathTo);
		String[] modules = new String[4];
		modules[0] = "udpcrc.soapbox.module";
		modules[1] = "udpcrypt1.soapbox.module";
		modules[2] = "udpcrypt2.soapbox.module";
		modules[3] = "xmppsubject.soapbox.module";
		for (String module : modules) {
			String fileDest = pathTo.concat("/modules/".concat(module));
			Assert.assertFalse(new File(fileDest) == null);
		}
	}

}
