package br.com.soapboxrace.jlauncher.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JaxbJson {

	public static Object loadObject(String jsonStr, Class<?> classz) throws IOException, JAXBException, XMLStreamException, FactoryConfigurationError, ClassNotFoundException {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		StringReader reader = new StringReader(jsonStr);
		JAXBContext jaxbContext = JAXBContext.newInstance(classz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		return jaxbUnmarshaller.unmarshal(new StreamSource(reader), classz).getValue();
	}

	public static String marshal(Object object) throws JAXBException {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(object, stringWriter);
		return stringWriter.toString();
	}

}
