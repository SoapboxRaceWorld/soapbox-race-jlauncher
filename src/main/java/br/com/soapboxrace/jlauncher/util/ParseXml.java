package br.com.soapboxrace.jlauncher.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import br.com.soapboxrace.jlauncher.vo.LoginOkVO;
import br.com.soapboxrace.jlauncher.vo.ServerErrorVO;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ParseXml {

    private DocumentBuilderFactory dcFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder dcBuilder;
    private Document doc;

    public ParseXml(String xml) {
        dcFactory = DocumentBuilderFactory.newInstance();
        try {
            dcBuilder = dcFactory.newDocumentBuilder();
            doc = dcBuilder.parse(new InputSource(new StringReader(xml)));
        } catch (IOException | ParserConfigurationException | SAXException e) {
        }
    }

    public LoginOkVO parseOk() {
        String userId = doc.getElementsByTagName("UserId").item(0).getTextContent();
        String loginToken = doc.getElementsByTagName("LoginToken").item(0).getTextContent();
        return new LoginOkVO(userId, loginToken);
    }

    public ServerErrorVO parseError() {
        String description = doc.getElementsByTagName("Description").item(0).getTextContent();
        return new ServerErrorVO(description);
    }

}
