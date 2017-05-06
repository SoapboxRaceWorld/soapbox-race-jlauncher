package br.com.soapboxrace.jlauncher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void propertiesTest() {
		Properties prop = new Properties();
		try (InputStream arquivoConfig = new FileInputStream(new File("config.properties"))) {
			prop.load(arquivoConfig);
			for (Entry<Object, Object> e : prop.entrySet()) {
				String key = (String) e.getKey();
				String etapaStr = (String) e.getValue();
				System.out.print(key + "->");
				System.out.println(etapaStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
