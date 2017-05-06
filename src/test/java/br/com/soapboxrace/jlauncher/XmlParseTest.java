package br.com.soapboxrace.jlauncher;

import org.junit.Assert;
import org.junit.Test;

import br.com.soapboxrace.jlauncher.util.ParseXml;
import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.ServerErrorVO;

public class XmlParseTest {

	@Test
	public void parseXmlOk() {
		String xml = "<LoginData><UserId>1</UserId><LoginToken>9094036189460</LoginToken></LoginData>";
		ParseXml parseXml = new ParseXml(xml);
		LoginOkVO parseOk = parseXml.parseOk();
		Assert.assertTrue(parseOk.getUserId().equals("1"));
		Assert.assertTrue(parseOk.getLoginToken().equals("9094036189460"));
	}

	@Test
	public void parseXmlError() {
		String xml = "<EngineExceptionTrans><Description>Login Error: Email wasn't found!</Description></EngineExceptionTrans>";
		ParseXml parseXml = new ParseXml(xml);
		ServerErrorVO parseError = parseXml.parseError();
		Assert.assertTrue(parseError.getDescription().equals("Login Error: Email wasn't found!"));
	}
}
