package webservice.service;

import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.w3c.dom.Document;

public class ClientTest {
	private static final String WSDL_URL = "http://localhost:8888/ws?wsdl";

	@Test
	public void client(){
		try {
			URL url = new URL(WSDL_URL);
			Service service = Service.create(url, new QName("http://service.webservice/", "MyServiceImplService"));
			IMyService ms = service.getPort(IMyService.class);
			System.out.println(ms.add(1, 2));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSOAP() {
		try {
			URL url = new URL(WSDL_URL);
			QName name = new QName("http://service.webservice/","MyServiceImplService");
			Service service = Service.create(url,name);

			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName("http://service.webservice/","MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);

			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage();
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPBody body = envelope.getBody();
			QName qname = new QName("http://service.webservice/","add","ns");
//			body.addBodyElement(qname).setValue("12");
			SOAPBodyElement bodyElement = body.addBodyElement(qname);
			bodyElement.addChildElement("a").setValue("11");
			bodyElement.addChildElement("b").setValue("22");
			message.writeTo(System.out);

			System.out.println("\n invoking.........");
			SOAPMessage response = dispatch.invoke(message);
			response.writeTo(System.out);
			System.out.println();

			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			System.out.println(doc.getElementsByTagName("addResult").item(0).getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSOAPPLAYLOAD() {
		try {
			URL url = new URL(WSDL_URL);
			QName name = new QName("http://service.webservice/","MyServiceImplService");
			Service service = Service.create(url,name);
			Dispatch<Source> dispatch = service.createDispatch(new QName("http://service.webservice/","MyServiceImplPort"), Source.class, Service.Mode.PAYLOAD);
			User user = new User(2,"guest","访客","111");
			JAXBContext ctx = JAXBContext.newInstance(User.class);
			Marshaller mar = ctx.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter writer = new StringWriter();
			mar.marshal(user, writer);
			
			String payload = "<nn:addUser x";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
